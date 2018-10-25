package pmpshk.atlassian.customer_portal_client;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class MyAtlassianClient
{
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private int sleepIntervalInSeconds = 5;
	private ChromeDriver driver;
	
	public MyAtlassianClient() throws Exception
	{
		File tempChromeDriver = makeChromeDriverFile();
		log.info("создали во временном каталоге файл chromedriver");
		driver = createWebDriver(tempChromeDriver);
		log.info("создали экземпляр webdriver");
		
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				log.info("Running Shutdown Hook");
				if (driver != null)
				{
					driver.quit();
					log.info("рекурсивно удаляем данные из временного каталога содержащего chromedriver");
					deleteFolderRecursively(tempChromeDriver.getParentFile());
				}
				log.info("shutdown hook ended");
			}
		});
	}
	
	private boolean setFileAttributesForChromedriverFile(File chromedriverFile)
	{
		try
		{
			log.info("начали изменение прав доступа для файла chromedriver");
			
			Set<PosixFilePermission> perms = java.nio.file.Files.readAttributes(chromedriverFile.toPath(), PosixFileAttributes
					.class)
					.permissions();
			
			log.info("file permission before : {}", perms);
			
			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			perms.add(PosixFilePermission.GROUP_WRITE);
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			perms.add(PosixFilePermission.OTHERS_WRITE);
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_EXECUTE);
			
			java.nio.file.Files.setPosixFilePermissions(chromedriverFile.toPath(), perms);
			log.info("file permission after : {}", perms);
			perms = null;
			log.info("закончили изменение прав доступа для файла chromedriver");
		}
		catch (IOException ex)
		{
			log.error("ошибка изменения прав доступа", ex);
			return false;
		}
		
		return true;
	}
	
	private File makeChromeDriverFile() throws Exception
	{
		try
		{
			URL chromedriverUrl = null;
			String platform = Os.platform();
			String driveFileName;
			
			if (platform.equals("win32"))
			{
				driveFileName = "chromedriver.exe";
			}
			else if (platform.equals("linux"))
			{
				driveFileName = "chromedriver";
			}
			else
			{
				log.error("Unsupported OS");
				throw new Exception();
			}
			
			chromedriverUrl = Resources.getResource(driveFileName);
			
			File tempDir = Files.createTempDir();
			log.info("создали временный каталог {}", tempDir);
			
			File tempChromeDriver = new File(tempDir.getAbsolutePath() + File.separator + "chromedriver");
			FileUtils.copyURLToFile(chromedriverUrl, tempChromeDriver);
			log.info("скопировали файл {} во временный каталог {}", tempChromeDriver, tempDir);
			
			if (setFileAttributesForChromedriverFile(tempChromeDriver))
			{
				tempDir = null;
				return tempChromeDriver;
			}
		}
		catch (IOException ex)
		{
			log.error("Не удалось создать файл", ex);
		}
		return null;
	}
	
	private void deleteFolderRecursively(File tempDir)
	{
		log.info("удаляем все содержимое папки {}", tempDir);
		if (tempDir != null && tempDir.exists() && tempDir.isDirectory())
		{
			File[] files = tempDir.listFiles();
			for (File file : files)
			{
				if (file.isDirectory())
				{
					deleteFolderRecursively(file);
				}
				else
				{
					file.deleteOnExit();
				}
			}
			
			tempDir.deleteOnExit();
		}
	}
	
	private ChromeDriver createWebDriver(File tempChromeDriver)
	{
		System.setProperty("webdriver.chrome.driver", tempChromeDriver.toString());
		
		ChromeDriver driver = new ChromeDriver(
				new ChromeOptions()
						.setAcceptInsecureCerts(true)
						.addArguments("--headless") //Runs Chrome in headless mode.
						//HEADLESS CHROME DOES NOT SUPPORT EXTENSIONS.
						//see more: https://bugs.chromium.org/p/chromium/issues/detail?id=706008#c5
						.addArguments("disable-gpu") //Temporarily needed if running on Windows.
		);
		log.info("создали экземпляр webdriver");
		long timeout = 15;
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		log.info("установили таймаут для экземпляра веб-драйвера равным {}", timeout);
		return driver;
	}
	
	public boolean loginToGoogleServices(final String login, final String password)
	{
		try
		{
			driver.get("https://accounts.google.com/ServiceLogin");
			log.info("перешли на страницу авторизации Google");
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			driver.getKeyboard().sendKeys(login); //todo удалить учетные данные
			log.info("ввели логин");
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			log.info("нажали Enter");
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			driver.getKeyboard().sendKeys(password); //todo удалить учетные данные
			log.info("ввели пароль");
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			log.info("нажали Enter");
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			if (driver.getCurrentUrl().equals("https://myaccount.google.com/?pli=1"))
			{
				log.info("Успешно залогинились в сервисы Google");
				return true;
			}
			else
			{
				log.error("по всей видимости не удалось залогиниться в сервисы Google, не произошло перенаправления ожидаемый URL");
				return false;
			}
		}
		catch (InterruptedException ex)
		{
			return false;
		}
	}
	
	public boolean loginToMyAtlassianWithGoogleCredentials()
	{
		try
		{
			driver.get("http://my.atlassian.com");
			log.info("перешли в личный кабинет Atlassian: my.atlassian.com");
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			WebElement googleSigninButton = driver.findElementById("google-signin-button");
			log.info("нашли кнопку для входа через сервисы Google");
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			googleSigninButton.click();
			log.info("нажали кнопку входа через сервисы Google");
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			
			WebElement profileIdentifier = null;
			try
			{
				profileIdentifier = driver.findElementById("profileIdentifier");
			}
			catch (NoSuchElementException ex)
			{
				log.error("Не удалось найти нужный профиль, возможно ваши учетные данные ошибочны", ex);
				return false;
			}
			log.info("выбрали Google профиль для входа в my.atlassian.com");
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			
			if (profileIdentifier != null)
			{
				profileIdentifier.click();
				log.info("кликаем по профилю гугл, через который мы хотим войти на my.atlassian.com");
			}
			else
			{
				log.error("Не удалось выбрать профиль пользователя сервисов Google, по причине отсутствия");
				return false;
			}
			
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			log.info("вошли на сайт my.atlassian.com используя аккаунт Google");
			return true;
		}
		catch (InterruptedException ex)
		{
			log.error("ошибка входа в сервис Atlassian через учетные данные Google");
			return false;
		}
	}
	
	private String postFormAndExtractLicenseFromResponsePage() throws InterruptedException
	{
		TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
		WebElement organisationName = driver.findElementById("organisation_name");
		organisationName.sendKeys("My org");
		log.info("Ввели название организации в форме генерации лицензии");
		TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
		WebElement marketplaceTermsConfirm = driver.findElementById("marketplaceTermsConfirm");
		log.info("установили чекбокс согласия с правилами Atlassian Marketplace");
		TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
		marketplaceTermsConfirm.click();
		TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
		WebElement generateLicense = driver.findElementById("generate-license");
		log.info("нашли кнопку для генерации лицензии");
		TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
		generateLicense.click();
		log.info("нажали кнопку для генерации лицензии");
		TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
		WebElement licenseKey = driver.findElementById("license-key");
		log.info("нашли элемент содержащий лицензионный ключ");
		TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
		String licenseKeyText = licenseKey.getText();
		log.info("получили лицензионный ключ");
		System.out.println(licenseKeyText);
		TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
		if (licenseKeyText != null && licenseKeyText.length() > 0)
		{
			return licenseKeyText;
		}
		else
		{
			return "";
		}
	}
	
	public String generateNewEvaluationLicense(String pluginKey)
	{
		String licenseKeyText = null;
		
		try
		{
			String plugintTryUrl = "https://my.atlassian.com/addon/try/" + pluginKey;
			driver.get(plugintTryUrl);
			log.info("переходим на страницу генерации лицензии для плагина {}", plugintTryUrl);
			TimeUnit.SECONDS.sleep(sleepIntervalInSeconds);
			
			if (driver.getCurrentUrl().equals("https://my.atlassian.com/addon/error"))
			{
				log.error("не удалось сгенерировать лицензию для плагина, произошла ошибка");
				WebElement errorsElement = driver.findElementByClassName("errors");
				log.error("Текст ошибки >>{}", errorsElement.getText());
				log.error("вышли из метода");
			}
			else
			{
				licenseKeyText = postFormAndExtractLicenseFromResponsePage();
			}
		}
		catch (InterruptedException ex)
		{
			log.error("Произошла ошибка!", ex);
		}
		catch (Exception ex)
		{
			log.error("Произошла ошибка!", ex);
		}
		
		return licenseKeyText;
	}
}

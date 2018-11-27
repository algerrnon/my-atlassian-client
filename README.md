## Project overview
Подключается к порталу my.atlassian.com при помощи webdriver 

и позволяет сгенерировать evaluation license для заданного по уникальному ключу плагина.

Get JIRA license and JIRA plugins licenses from Atlassian Customer Portal (my.atlassian.com)
  
## To Do
  
## Code Example
  
```
public class MyAtlassianClientTest
{
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	public static void main(String[] args) throws IOException
	{
		String login = "testme@gmail.com";
		String password = "your-password";
		try
		{
			MyAtlassianClient client = new MyAtlassianClient();
			if (client.loginToGoogleServices(login, password))
			{
				if (client.loginToMyAtlassianWithGoogleCredentials())
				{
					for (int i = 0; i < 20; i++)
					{
						client.generateNewEvaluationLicense("com.thed.zephyr.je");
					}
				}
			}
			else
			{
				log.error("Не удалось залогиниться в Google");
			}
			client = null;
		}
		catch (Exception ex)
		{
			log.error("данная OS не поддерживается", ex);
			return;
		}
	}
}
```  
## Contributors

Alexander Pampushko
   
## License
  
Apache License 2.0

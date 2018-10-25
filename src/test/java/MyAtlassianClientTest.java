import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pmpshk.atlassian.customer_portal_client.MyAtlassianClient;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 *
 */
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
						client.generateNewEvaluationLicense("com.comalatech.workflow");
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

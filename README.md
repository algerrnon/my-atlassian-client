## Project overview
  Get JIRA license and JIRA plugins licenses from Atlassian Customer Portal (my.atlassian.com)
  
## To Do
  
## Code Example
  
```
	public static void main(String[] args) throws IOException
	{
		String login = "your-login@gmail.com";
		String password = "your-password";
		MyAtlassianClient client = new MyAtlassianClient();
		if (client.loginToGoogleServices(login, password) & client.loginToMyAtlassianWithGoogleCredentials())
		{
			for (int i = 0; i < 20; i++)
			{
				client.generateNewEvaluationLicense("com.thed.zephyr.je");
			}
		}
		else
		{
			log.error("Не удалось залогиниться в Google");
		}
		client = null;
	}
```  
## Contributors
   
## License
  
Apache License 2.0

package com.pampushko.atlassian.upm;

import com.pampushko.atlassian.upm.auth_models.AuthResult;
import com.pampushko.atlassian.upm.auth_models.SessionSubObj;
import com.pampushko.atlassian.upm.exceptions.GetPluginException;
import com.pampushko.atlassian.upm.exceptions.GetPluginListException;
import com.pampushko.atlassian.upm.exceptions.UpdatePluginException;
import com.pampushko.atlassian.upm.models.PluginListResult;
import com.pampushko.atlassian.upm.models.single_plugin.SinglePlugin;
import com.pampushko.atlassian.upm.models.update_plugin.UpdatePluginLicense;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * Класс клиента для Yandex Market API
 * <br />
 */
@Slf4j
public class UpmApiClient
{
	/**
	 * Логин
	 * <br />
	 */
	String login;
	
	/**
	 * Пароль
	 * <br />
	 */
	String password;
	
	/**
	 * базовый URL
	 * <br />
	 */
	String baseUrl;
	
	/**
	 * Ссылка на экземпляр интерфейса UpmApi
	 * <br />
	 */
	private UpmApi upmApi;
	
	/**
	 * Приватный конструктор,
	 * <br />
	 * т.к. мы будем создавать экземпляр клиента через Builder
	 * <br />
	 */
	private UpmApiClient()
	{
	
	}
	
	///////////////////////////////////////////////////
	public class Builder
	{
		private Builder()
		{
		
		}
		
		public Builder login(String login)
		{
			UpmApiClient.this.login = login;
			return this;
		}
		
		public Builder password(String password)
		{
			UpmApiClient.this.password = password;
			return this;
		}
		
		public Builder baseUrl(String baseUrl)
		{
			UpmApiClient.this.baseUrl = baseUrl;
			return this;
		}
		
		/**
		 * настраиваем REST-адаптер, который будет использоваться для работы с UPM plugin API
		 * <br />
		 * И создаем Retrofit-клиента, согласно описанию API в интерфейсе {@link com.pampushko.atlassian.upm.UpmApi}
		 * <br />
		 *
		 * @return
		 */
		public UpmApiClient build()
		{
			Retrofit retrofit = new RetrofitManager().getRetrofitForMarketApi(UpmApiClient.this);
			UpmApiClient.this.upmApi = retrofit.create(UpmApi.class);
			return UpmApiClient.this;
		}
	}
	///////////////////////////////////////////////////
	
	/**
	 * создаём новый builder для создания {@link com.pampushko.atlassian.upm.UpmApiClient}
	 *
	 * @return экземпляр Builder-ра
	 */
	public static Builder newBuilder()
	{
		return new UpmApiClient().new Builder();
	}
	
	//////////////////////////////////////////////////////////////
	//                      Методы клиента
	//////////////////////////////////////////////////////////////
	
	/**
	 * @return Возвращает объект содержащий список плагинов и доп. информацию - {@code PluginList}
	 * <br />
	 */
	public PluginListResult getPlugins() throws IOException
	{
		//String jiraCookie = getJiraCookie();
		PluginListResult result = null;
		try
		{
			Call<PluginListResult> pluginListCall = upmApi.getPlugins();
			Response<PluginListResult> response = pluginListCall.execute();
			result = response.body();
		}
		catch (IOException ex)
		{
			log.error("error", ex);
		}
		
		if (result != null)
		{
			return result;
		}
		else
		{
			throw new GetPluginListException();
		}
		
	}
	
	/**
	 * получаем информацию по одному плагину, имеющему указанный pluginKey
	 *
	 * @param pluginKey
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public SinglePlugin getPlugin(final String pluginKey) throws IOException
	{
		SinglePlugin result = null;
		try
		{
			Call<SinglePlugin> pluginListCall = upmApi.getPlugin(pluginKey);
			Response<SinglePlugin> response = pluginListCall.execute();
			result = response.body();
		}
		catch (IOException ex)
		{
			log.error("error", ex);
		}
		
		if (result != null)
		{
			return result;
		}
		else
		{
			throw new GetPluginException();
		}
	}
	
	/**
	 * обновим лицензию для плагина имеющего ключ pluginKey
	 *
	 * @param pluginKey
	 * @param plugin
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public SinglePlugin updatePlugin(final String pluginKey, final UpdatePluginLicense plugin) throws IOException
	{
		SinglePlugin result = null;
		try
		{
			Call<SinglePlugin> pluginListCall = upmApi.updatePlugin(pluginKey, plugin);
			Response<SinglePlugin> response = pluginListCall.execute();
			if (!response.isSuccessful())
			{
				log.error("Update plugin is not successful.");
				ResponseBody errorBody = response.errorBody();
				if (errorBody != null)
				{
					byte[] bytes = errorBody.bytes();
					String error = new String(bytes, "UTF-8");
					log.error(error);
				}
			}
			result = response.body();
		}
		catch (IOException ex)
		{
			log.error("error", ex);
		}
		
		if (result != null)
		{
			return result;
		}
		else
		{
			throw new UpdatePluginException();
		}
	}
	
	/**
	 * логинимся в жиру и получаем куку, которую будем использовать в следующих запросах
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public String getJiraCookie() throws IOException
	{
		final String username = "testroot";
		final String password = "712554";
		CredentialsObj credentialsObj = new CredentialsObj();
		credentialsObj.username = username;
		credentialsObj.password = password;
		
		Call<AuthResult> call = upmApi.getCookie(credentialsObj);
		Response<AuthResult> response = call.execute();
		System.out.println(response.isSuccessful());
		System.out.println(response.body());
		SessionSubObj session = response.body().getSession();
		String cookie = session.getName().concat("=").concat(session.getValue());
		return cookie;
	}
}

package com.pampushko.atlassian.upm;

import com.pampushko.atlassian.upm.auth_models.AuthResult;
import com.pampushko.atlassian.upm.models.PluginListResult;
import com.pampushko.atlassian.upm.models.single_plugin.SinglePlugin;
import com.pampushko.atlassian.upm.models.update_plugin.UpdatePluginLicense;
import retrofit2.Call;
import retrofit2.http.*;

/**
 *
 */
public interface UpmApi
{
	
	public static final String cookieHeader = null;
	
	/**
	 * @return Возвращает список плагинов установленных в системе - {@code PluginList}
	 * os_authType=basic
	 * <br />
	 */
	
	@Headers("Accept: application/vnd.atl.plugins.installed+json")
	@GET("/rest/plugins/1.0/")
	Call<PluginListResult> getPlugins();
	
	/**
	 * @param pluginKey
	 * 		ключ плагина, уникальный в системе
	 *
	 * @return возвращает информацию по одному плагину
	 */
	@Headers("Accept: application/vnd.atl.plugins.plugin+json")
	@GET("/rest/plugins/1.0/{pluginKey}-key")
	Call<SinglePlugin> getPlugin(@Path("pluginKey") String pluginKey);
	
	//update plugin
	
	/**
	 * Обновляет настройки плагина
	 *
	 * @param pluginKey
	 * 		ключ плагина, уникальный в системе
	 *
	 * @return возвращает информацию по одному плагину
	 */
	@Headers({"Accept: */*;",
			"Content-Type: application/vnd.atl.plugins+json"})
	@PUT("/rest/plugins/1.0/{pluginKey}-key/license")
	Call<SinglePlugin> updatePlugin(@Path("pluginKey") String pluginKey,
	                                @Body UpdatePluginLicense pluginDescriptionObject);
	
	@Headers({"Content-Type: application/json"})
	@POST("rest/auth/1/session")
	Call<AuthResult> getCookie(@Body CredentialsObj credentialsObj);
}

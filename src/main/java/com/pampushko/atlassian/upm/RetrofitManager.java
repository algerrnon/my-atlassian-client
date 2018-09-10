package com.pampushko.atlassian.upm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Base64;

/**
 *
 */
@Slf4j
public class RetrofitManager
{
	
	/**
	 * Создаем Retrofit экземпляр для создания клиента для работы
	 * <br />
	 * <p>
	 * <br />
	 *
	 * @param upmApiClient
	 * 		клиент (из клиента берем credetials, baseUrl)
	 *
	 * @return экземпляр Retrofit
	 */
	Retrofit getRetrofitForMarketApi(UpmApiClient upmApiClient)
	{
		final String login = upmApiClient.login;
		final String password = upmApiClient.password;
		
		//HTTP Basic authentication для REST API Confluence
		final String credentials = login + ":" + password;
		//кодируем в base64.
		final String encodedCredentials = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
		
		//создаем gson-билдер
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
				.disableHtmlEscaping()
				.create();
		
		//создаем интерсептор для логирования
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		//создаем http-клиента OkHttp и добавляем в него интерсептор (чтобы добавить нужные нам заголовки к
		// каждому из посылаемых нами запросов)
		OkHttpClient httpClient = new OkHttpClient.Builder()
				.addInterceptor(new Interceptor()
				{
					@Override
					public Response intercept(Chain chain) throws IOException
					{
						Request request = chain.request().newBuilder()
								//.addHeader("Accept", "application/vnd.atl.plugins.installed+json")
								//.addHeader("Accept", "application/vnd.atl.plugins.plugin+json")
								
								.addHeader("Authorization", "Basic dGVzdHJvb3Q6NzEyNTU0")
								.addHeader("User-Agent", "curl/7.47.0")
								.addHeader("X-Atlassian-Token", "nocheck")
								//								.addHeader("Content-Type", "application/json")
								.build();
						return chain.proceed(request);
					}
				})
				.addInterceptor(interceptor)
				.build();
		//создаем экземпляр Ретрофита - добавляем к ретрофиту созданный нами ранее Http-клиент
		Retrofit retrofit = new Retrofit.Builder()
				
				.baseUrl(upmApiClient.baseUrl)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(httpClient)
				.build();
		
		return retrofit;
	}
}

package com.pampushko.atlassian.upm;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.pampushko.atlassian.upm.models.PluginListResult;
import com.pampushko.atlassian.upm.models.plugins_list.plugin.ThePlugin;
import com.pampushko.atlassian.upm.models.single_plugin.SinglePlugin;
import com.pampushko.atlassian.upm.models.update_plugin.UpdatePluginLicense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 *
 */
public class Main
{
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	public static void main(String[] args) throws IOException
	{
		ColoredPrinter cp = new ColoredPrinter.Builder(1, false)
				.foreground(Ansi.FColor.WHITE)
				.background(Ansi.BColor.BLUE)
				.build();
		
		UpmApiClient upmApiClient = UpmApiClient.newBuilder().baseUrl("https://confluence.np.ua").login("testroot")
				.password("712554").build();
		
		PluginListResult plugins = upmApiClient.getPlugins();
		ThePlugin[] pluginsList = plugins.getPlugins();
		for (ThePlugin elem : pluginsList)
		{
			if (elem.isUsesLicensing())
			{
				System.out.println(elem.getKey());
			}
		}
		//System.out.println(plugins);
		
		final String pluginKey = "de.communardo.confluence.plugins.userprofile";
		
		UpdatePluginLicense obj = new UpdatePluginLicense();
		obj.setRawLicense("AAABLg0ODAoPeNqVkU1rAjEQhu/5FYGed0nSLa1CoGXdQ8FVqdZTL+k6awPZJORD6r9v4ipC8eIhM\n" +
				"JmZvO8zk4fWaLwGiynBpJpWbMoYrmcbzAh9QYs4fINb9p8enOcFRTPwnZM2SKN5TuKVM71U4HFvH\n" +
				"K6N7lUE3cHXFDcHoaLInah2cApmIgDPwgWZFJSg1B9EFxZiAC7s5Jk+kup1Pwipys4Ml3LTpsSt+\n" +
				"tWBBxcB7SCnh6iF25kUXmBKq+Jeal/GRGxH4BJ0yBfpYXysZAfawzYNmgUZSq6pRYsk0Pxa6Y5Xe\n" +
				"koy/V126yBcMuS9UP5O0rQCeThTLt1eaOnPQ4MPaN0seDrFnDI2eaJVhebjJLehz8XN0cJp7fWyb\n" +
				"ZuP+v1tjlbRdT/Cw/9f+gM6Mry7MC0CFQCI/qlkqj0DHpYE6ain7Q02d16lbAIUSfiqRgXoBp/Ev\n" +
				"cNJBidEtK9lqJ4=X02f7");
		SinglePlugin updatePlugin = upmApiClient.updatePlugin(pluginKey, obj);
		System.out.println(updatePlugin);
		
	}
}

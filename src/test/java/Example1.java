import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.pampushko.atlassian.upm.UpmApiClient;
import com.pampushko.atlassian.upm.models.PluginListResult;
import com.pampushko.atlassian.upm.models.plugins_list.plugin.ThePlugin;
import com.pampushko.atlassian.upm.models.single_plugin.SinglePlugin;
import com.pampushko.atlassian.upm.models.single_plugin.SinglePluginModule;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Example1
{
	public static void main(String[] args) throws IOException
	{
		ColoredPrinter cp = new ColoredPrinter.Builder(1, false)
				.foreground(Ansi.FColor.WHITE)
				.background(Ansi.BColor.BLUE)
				.build();
		
		UpmApiClient upmApiClient = UpmApiClient.newBuilder().baseUrl("https://confluence.np.ua").build();
		
		PluginListResult plugins = upmApiClient.getPlugins();
		List<ThePlugin> thePlugins = Arrays.asList(plugins.getPlugins());
		int counter = 0;
		for (ThePlugin plugin : thePlugins)
		{
			if (plugin.isUserInstalled())
			{
				counter++;
				String message = MessageFormat.format("{0}, {1}, {2}", counter, plugin.getKey(), plugin.isUsesLicensing());
				System.out.println(message);
			}
		}
		
		System.out.println("------------------------------------------------");
		SinglePlugin singlePlugin = upmApiClient.getPlugin("com.atlassian.confluence.plugins.confluence-questions");
		
		counter = 0;
		List<SinglePluginModule> pluginModules = Arrays.asList(singlePlugin.getModules());
		for (SinglePluginModule module : pluginModules)
		{
			counter++;
			cp.print(MessageFormat.format("{0}\t", counter), Ansi.Attribute.BOLD, Ansi.FColor.BLACK, Ansi.BColor.GREEN);
			cp.print(MessageFormat.format("{0}\t\n", module.getName()), Ansi.Attribute.BOLD, Ansi.FColor.BLUE, Ansi.BColor
					.WHITE);
			cp.println(module.getKey());
		}
	}
	
}

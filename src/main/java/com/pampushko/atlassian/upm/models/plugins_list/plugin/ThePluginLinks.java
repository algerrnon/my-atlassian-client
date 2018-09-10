package com.pampushko.atlassian.upm.models.plugins_list.plugin;

import com.google.gson.annotations.SerializedName;
import com.pampushko.atlassian.upm.models.BaseModel;

/**
 *
 */
public class ThePluginLinks extends BaseModel
{
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("self")
	String self;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("plugin-summary")
	String pluginSummary;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("plugin-logo")
	String pluginLogo;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("plugin-icon")
	String pluginIcon;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("modify")
	String modify;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("manage")
	String manage;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("delete")
	String delete;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("configure")
	String configure;
}

package com.pampushko.atlassian.upm.models.single_plugin;

import com.google.gson.annotations.SerializedName;
import com.pampushko.atlassian.upm.models.BaseModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class SinglePluginLinks extends BaseModel
{
	/**
	 * Пример: "/rest/plugins/1.0/com.atlassian.confluence.plugins.confluence-questions-key"
	 * <br>
	 */
	@SerializedName("self")
	String self;
	
	/**
	 * Пример: "/rest/plugins/1.0/com.atlassian.confluence.plugins.confluence-questions-key/summary"
	 * <br>
	 */
	@SerializedName("plugin-summary")
	String pluginSummary;
	
	/**
	 * Пример: "/rest/plugins/1.0/com.atlassian.confluence.plugins.confluence-questions-key"
	 * <br>
	 */
	@SerializedName("modify")
	String modify;
	
	/**
	 * Пример: "/rest/plugins/1.0/com.atlassian.confluence.plugins.confluence-questions-key/media/plugin-icon"
	 * <br>
	 */
	@SerializedName("plugin-icon")
	String pluginIcon;
	
	/**
	 * Пример: "/rest/plugins/1.0/com.atlassian.confluence.plugins.confluence-questions-key/media/plugin-logo"
	 * <br>
	 */
	@SerializedName("plugin-logo")
	String pluginLogo;
	
	/**
	 * Пример: "https://confluence.np.ua/plugins/servlet/upm?fragment=manage%2Fcom.atlassian.confluence.plugins.confluence-questions"
	 * <br>
	 */
	@SerializedName("manage")
	String manage;
	
	/**
	 * Пример: "/rest/plugins/1.0/com.atlassian.confluence.plugins.confluence-questions-key"
	 * <br>
	 */
	@SerializedName("delete")
	String delete;
	
}

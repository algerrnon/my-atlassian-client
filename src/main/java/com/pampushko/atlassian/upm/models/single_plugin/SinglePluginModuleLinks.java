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
public class SinglePluginModuleLinks extends BaseModel
{
	/**
	 * Пример: "com.atlassian.confluence.plugins.confluence-questions:question"
	 * <br>
	 */
	@SerializedName("self")
	String self;
	
	/**
	 * Пример: "/rest/plugins/1.0/com.atlassian.confluence.plugins.confluence-questions-key/modules/question-key"
	 * <br>
	 */
	@SerializedName("modify")
	String modify;
	
	/**
	 * Пример: "/rest/plugins/1.0/com.atlassian.confluence.plugins.confluence-questions-key"
	 * <br>
	 */
	@SerializedName("plugin")
	String plugin;
	
}

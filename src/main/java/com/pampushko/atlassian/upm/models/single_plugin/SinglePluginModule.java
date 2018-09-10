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
public class SinglePluginModule extends BaseModel
{
	/**
	 * Пример: "question"
	 * <br>
	 */
	@SerializedName("key")
	String key;
	
	/**
	 * Пример: "com.atlassian.confluence.plugins.confluence-questions:question"
	 * <br>
	 */
	@SerializedName("completeKey")
	String completeKey;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("links")
	SinglePluginModuleLinks links;
	
	/**
	 * Пример: true
	 * <br>
	 */
	@SerializedName("enabled")
	boolean enabled;
	
	/**
	 * Пример: true
	 * <br>
	 */
	@SerializedName("optional")
	boolean optional;
	
	/**
	 * Пример: "Question Content Type Support"
	 * <br>
	 */
	@SerializedName("name")
	String name;
	
	/**
	 * Пример: "Support for Question as a pluggable content type"
	 * <br>
	 */
	@SerializedName("description")
	String description;
	
	/**
	 * Пример: true
	 * <br>
	 */
	@SerializedName("recognisableType")
	boolean recognisableType;
	
	/**
	 * Пример: false
	 * <br>
	 */
	@SerializedName("broken")
	boolean broken;
}

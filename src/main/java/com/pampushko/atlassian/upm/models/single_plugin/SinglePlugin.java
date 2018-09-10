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
public class SinglePlugin extends BaseModel
{
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("links")
	SinglePluginLinks links;
	
	/**
	 * Пример: "com.atlassian.confluence.plugins.confluence-questions"
	 * <br>
	 */
	@SerializedName("key")
	String key;
	
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
	@SerializedName("enabledByDefault")
	boolean enabledByDefault;
	
	/**
	 * Пример: "2.4.33"
	 * <br>
	 */
	@SerializedName("version")
	String version;
	
	/**
	 * Пример: "Questions for Confluence"
	 * <br>
	 */
	@SerializedName("description")
	String description;
	
	/**
	 * Пример: "Questions for Confluence Plugin"
	 * <br>
	 */
	@SerializedName("name")
	String name;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("modules")
	SinglePluginModule[] modules;
	
	/**
	 * Пример: true
	 * <br>
	 */
	@SerializedName("userInstalled")
	boolean userInstalled;
	
	/**
	 * Пример: true
	 * <br>
	 */
	@SerializedName("optional")
	boolean optional;
	
	/**
	 * Пример: true
	 * <br>
	 */
	@SerializedName("unrecognisedModuleTypes")
	boolean unrecognisedModuleTypes;
	
	/**
	 * Пример: false
	 * <br>
	 */
	@SerializedName("unloadable")
	boolean unloadable;
	
	/**
	 * Пример: false
	 * <br>
	 */
	@SerializedName("static")
	boolean staticField;
	
	/**
	 * Пример: true
	 * <br>
	 */
	@SerializedName("usesLicensing")
	boolean usesLicensing;
	
	/**
	 * Пример: false
	 * <br>
	 */
	@SerializedName("remotable")
	boolean remotable;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("vendor")
	SinglePluginVendor vendor;
}

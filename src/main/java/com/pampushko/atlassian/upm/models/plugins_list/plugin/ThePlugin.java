package com.pampushko.atlassian.upm.models.plugins_list.plugin;

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
public class ThePlugin extends BaseModel
{
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("enabled")
	boolean enabled;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("links")
	ThePluginLinks links;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("name")
	String name;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("version")
	String version;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("userInstalled")
	boolean userInstalled;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("optional")
	boolean optional;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("static")
	boolean staticField;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("unloadable")
	boolean unloadable;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("description")
	String description;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("key")
	String key;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("usesLicensing")
	boolean usesLicensing;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("remotable")
	boolean remotable;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("vendor")
	TheVendor vendor;
}


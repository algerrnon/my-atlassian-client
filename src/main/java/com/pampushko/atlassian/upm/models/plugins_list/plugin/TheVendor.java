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
public class TheVendor extends BaseModel
{
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
	@SerializedName("marketplaceLink")
	String marketplaceLink;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("link")
	String link;
}

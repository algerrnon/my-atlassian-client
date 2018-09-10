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
public class SinglePluginVendor extends BaseModel
{
	/**
	 * Пример: "Atlassian"
	 * <br>
	 */
	@SerializedName("name")
	String name;
	
	/**
	 * Пример: "http://www.atlassian.com/"
	 * <br>
	 */
	@SerializedName("marketplaceLink")
	String marketplaceLink;
	
	/**
	 * Пример: "http://www.atlassian.com/"
	 * <br>
	 */
	@SerializedName("link")
	String link;
}

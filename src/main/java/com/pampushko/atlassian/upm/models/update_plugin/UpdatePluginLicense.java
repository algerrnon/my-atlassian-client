package com.pampushko.atlassian.upm.models.update_plugin;

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
public class UpdatePluginLicense extends BaseModel
{
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("rawLicense")
	String rawLicense;
}

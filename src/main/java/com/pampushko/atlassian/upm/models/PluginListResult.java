package com.pampushko.atlassian.upm.models;

import com.google.gson.annotations.SerializedName;
import com.pampushko.atlassian.upm.models.plugins_list.links.TheLinks;
import com.pampushko.atlassian.upm.models.plugins_list.plugin.ThePlugin;
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
public class PluginListResult extends BaseModel
{
	@SerializedName("plugins")
	ThePlugin[] plugins;
	
	@SerializedName("links")
	TheLinks link;
}

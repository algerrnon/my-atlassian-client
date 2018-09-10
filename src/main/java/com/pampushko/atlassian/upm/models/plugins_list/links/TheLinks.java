package com.pampushko.atlassian.upm.models.plugins_list.links;

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
public class TheLinks extends BaseModel
{
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("atlassian")
	String atlassian;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("available")
	String available;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("banners")
	String banners;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("categories")
	String categories;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("enter-safe-mode")
	String enterSafeMode;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("featured")
	String featured;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("highest-rated")
	String highestRated;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("marketplace")
	String marketplace;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("notifications")
	String notifications;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("osgi-bundles")
	String osgiBundles;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("osgi-packages")
	String osgiPackages;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("osgi-services")
	String osgiServices;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("pending-tasks")
	String pendingTasks;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("popular")
	String popular;
	
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
	@SerializedName("top-grossing")
	String topGrossing;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("trending")
	String trending;
}

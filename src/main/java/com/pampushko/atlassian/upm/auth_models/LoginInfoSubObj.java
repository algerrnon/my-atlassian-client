package com.pampushko.atlassian.upm.auth_models;

import com.google.gson.annotations.SerializedName;
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
public class LoginInfoSubObj
{
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("failedLoginCount")
	Integer failedLoginCount;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("loginCount")
	Integer loginCount;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("lastFailedLoginTime")
	String lastFailedLoginTime;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("previousLoginTime")
	String previousLoginTime;
}

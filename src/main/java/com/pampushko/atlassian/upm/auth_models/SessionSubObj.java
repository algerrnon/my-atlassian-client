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
public class SessionSubObj
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
	@SerializedName("value")
	String value;
}

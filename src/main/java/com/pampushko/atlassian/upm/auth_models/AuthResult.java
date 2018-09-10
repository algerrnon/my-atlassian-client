package com.pampushko.atlassian.upm.auth_models;

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
public class AuthResult extends BaseModel
{
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("session")
	SessionSubObj session;
	
	/**
	 * Пример:
	 * <br>
	 */
	@SerializedName("loginInfo")
	LoginInfoSubObj loginInfo;
}

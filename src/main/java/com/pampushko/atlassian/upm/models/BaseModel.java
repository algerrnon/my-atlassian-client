package com.pampushko.atlassian.upm.models;

import com.google.gson.GsonBuilder;

/**
 *
 */
public class BaseModel
{
	@Override
	public String toString()
	{
		return new GsonBuilder().disableHtmlEscaping().create().toJson(this);
	}
	
}

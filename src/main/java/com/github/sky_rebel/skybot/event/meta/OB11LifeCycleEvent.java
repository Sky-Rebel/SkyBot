package com.github.sky_rebel.skybot.event.meta;

import com.github.sky_rebel.skybot.event.OB11BaseMetaEvent;

public class OB11LifeCycleEvent extends OB11BaseMetaEvent
{
	public static final String META_EVENT_TYPE = "lifecycle";

	public LifeCycleSubType lifeCycleSubType;

	public enum LifeCycleSubType
	{
		ENABLE("enable"),

		DISABLE("disable"),

		CONNECT("connect");

		public String value;

		LifeCycleSubType(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}
	}
}
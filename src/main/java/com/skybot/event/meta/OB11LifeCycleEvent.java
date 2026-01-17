package com.skybot.event.meta;

import com.skybot.event.OB11BaseMetaEvent;

public class OB11LifeCycleEvent extends OB11BaseMetaEvent
{
	public static final String META_EVENT_TYPE = "lifecycle";

	public LifeCycleSubType lifeCycleSubType;

	public enum LifeCycleSubType
	{
		/**
		 * Bot启用
		 */
		ENABLE("enable"),

		/**
		 * Bot禁用
		 */
		DISABLE("disable"),

		/**
		 * Bot连接
		 */
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
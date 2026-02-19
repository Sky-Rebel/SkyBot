package com.github.sky_rebel.skybot.event.meta;

import com.github.sky_rebel.skybot.event.OB11BaseMetaEvent;

public class OB11LifeCycleEvent extends OB11BaseMetaEvent
{
	public static final String META_EVENT_TYPE = "lifecycle";

	public LifeCycleSubType lifeCycleSubType;

	public enum LifeCycleSubType
	{
		/**
		 * Bot启用事件（未实现）
		 */
		ENABLE("enable"),

		/**
		 * Bot禁用事件（未实现）
		 */
		DISABLE("disable"),

		/**
		 * Bot连接事件（未实现）
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
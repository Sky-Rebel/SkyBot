package com.skybot.event;

public class OB11BaseEvent
{
	public long time;

	public long self_id;

	public String post_type;

	public enum EventType
	{
		/**
		 * 元事件
		 */
		META("meta_event"),

		/**
		 * 请求事件
		 */
		REQUEST("request"),

		/**
		 * 通知事件
		 */
		NOTICE("notice"),

		/**
		 * 消息事件
		 */
		MESSAGE("message"),

		/**
		 * 自发消息事件
		 */
		MESSAGE_SENT("message_sent");

		public String value;

		EventType(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}
	}
}
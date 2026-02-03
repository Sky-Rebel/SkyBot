package com.skybot.event;

import com.skybot.bot.Bot;

public class OB11BaseEvent
{
	public Bot bot;

	public long time;

	public long selfId;

	public enum EventType
	{
		/**
		 * 元事件
		 */
		META,

		/**
		 * 请求事件
		 */
		REQUEST,

		/**
		 * 通知事件
		 */
		NOTICE,

		/**
		 * 消息事件
		 */
		MESSAGE,

		/**
		 * 自发消息事件
		 */
		MESSAGE_SENT;
	}

	@Override
	public String toString()
	{
		return "OB11BaseEvent{" +
			"time=" + time +
			", selfId=" + selfId +
			'}';
	}
}
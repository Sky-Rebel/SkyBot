package com.github.sky_rebel.event.handling.listener;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.event.message_sent.OB11GroupMessageSentEvent;
import com.github.sky_rebel.event.message_sent.OB11PrivateMessageSentEvent;

public interface OB11MessageSentEventListener
{
	/**
	 * 群聊自发消息事件
	 *
	 * @param ob11GroupMessageSentEvent 群组自发消息事件数据类
	 */
	void onGroupSentMessage(Bot bot, OB11GroupMessageSentEvent ob11GroupMessageSentEvent);

	/**
	 * 私聊自发消息事件
	 *
	 * @param ob11PrivateMessageSentEvent 私聊自发消息事件数据类
	 */
	void onPrivateSentMessage(Bot bot, OB11PrivateMessageSentEvent ob11PrivateMessageSentEvent);
}

package com.skybot.event.handling.listener;

import com.skybot.bot.Bot;
import com.skybot.event.message.OB11GroupMessageEvent;
import com.skybot.event.message.OB11PrivateMessageEvent;
import com.skybot.event.message_sent.OB11GroupMessageSentEvent;
import com.skybot.event.message_sent.OB11PrivateMessageSentEvent;

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

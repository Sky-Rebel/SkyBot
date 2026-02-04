package com.github.sky_rebel.event.handling.listener.message;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.event.message.OB11PrivateMessageEvent;

public interface OB11PrivateMessageEventListener
{
	/**
	 * 私聊消息事件
	 * @param ob11PrivateMessageEvent 私聊消息事件数据类
	 */
	void onPrivateMessage(Bot bot, OB11PrivateMessageEvent ob11PrivateMessageEvent);
}
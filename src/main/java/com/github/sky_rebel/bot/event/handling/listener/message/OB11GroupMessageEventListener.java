package com.github.sky_rebel.bot.event.handling.listener.message;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.event.message.OB11GroupMessageEvent;

public interface OB11GroupMessageEventListener
{
	/**
	 * 群组消息事件
	 * @param ob11GroupMessageEvent 群组消息事件数据类
	 */
	void onGroupMessage(Bot bot, OB11GroupMessageEvent ob11GroupMessageEvent);
}

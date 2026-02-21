package com.github.sky_rebel.skybot.event.handling.listener.message;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11PrivateMessageEvent;

public interface OB11MessageEventListener
{
	void onGroupMessage(Bot bot, OB11GroupMessageEvent ob11GroupMessageEvent);

	void onPrivateMessage(Bot bot, OB11PrivateMessageEvent ob11PrivateMessageEvent);
}
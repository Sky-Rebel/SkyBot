package com.github.sky_rebel.skybot.event.handling.listener.message;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;

public interface OB11GroupMessageEventListener
{
	void onGroupMessage(Bot bot, OB11GroupMessageEvent ob11GroupMessageEvent);
}

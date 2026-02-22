package com.github.sky_rebel.skybot.event.handling.listener;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.message.OB11PrivateMessageEvent;

public interface OB11PrivateMessageEventListener
{
	void onPrivateMessage(Bot bot, OB11PrivateMessageEvent ob11PrivateMessageEvent);
}
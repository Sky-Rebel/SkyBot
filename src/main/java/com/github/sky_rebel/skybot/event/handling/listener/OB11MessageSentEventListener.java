package com.github.sky_rebel.skybot.event.handling.listener;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.message_sent.OB11GroupMessageSentEvent;
import com.github.sky_rebel.skybot.event.message_sent.OB11PrivateMessageSentEvent;

public interface OB11MessageSentEventListener
{
	void onGroupSentMessage(Bot bot, OB11GroupMessageSentEvent ob11GroupMessageSentEvent);

	void onPrivateSentMessage(Bot bot, OB11PrivateMessageSentEvent ob11PrivateMessageSentEvent);
}
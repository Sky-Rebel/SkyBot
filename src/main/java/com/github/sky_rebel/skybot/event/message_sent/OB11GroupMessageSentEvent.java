package com.github.sky_rebel.skybot.event.message_sent;

import com.github.sky_rebel.skybot.event.OB11BaseMessageSentEvent;

import java.util.StringJoiner;

public class OB11GroupMessageSentEvent extends OB11BaseMessageSentEvent
{

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupMessageSentEvent.class.getSimpleName() + "[", "]")
			.add("userId=" + userId)
			.add("groupId=" + groupId)
			.add("sender=" + sender)
			.add("time=" + time)
			.add("targetId=" + targetId)
			.add("messageId=" + messageId)
			.add("rawMessage='" + rawMessage + "'")
			.add("messageArray=" + messageArray)
			.add("time=" + time)
			.add("selfId=" + selfId)
			.toString();
	}
}

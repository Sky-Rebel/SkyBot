package com.skybot.event.message_sent;

import com.skybot.event.OB11BaseMessageSentEvent;

import java.util.StringJoiner;

public class OB11PrivateMessageSentEvent extends OB11BaseMessageSentEvent
{
	public long userId;

	public Sender sender;

	public static class Sender
	{
		public long userId;

		public String nickname;

		public String card;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11PrivateMessageSentEvent.class.getSimpleName() + "[", "]")
			.add("userId=" + userId)
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

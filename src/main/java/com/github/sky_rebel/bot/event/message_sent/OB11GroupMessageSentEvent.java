package com.github.sky_rebel.bot.event.message_sent;

import com.github.sky_rebel.bot.event.OB11BaseMessageSentEvent;

import java.util.StringJoiner;

public class OB11GroupMessageSentEvent extends OB11BaseMessageSentEvent
{
	public long userId;

	public long groupId;

	public String groupName;

	public Sender sender;

	public static class Sender
	{
		public String role;

		public long userId;

		public String nickname;

		public String card;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupMessageSentEvent.class.getSimpleName() + "[", "]")
			.add("userId=" + userId)
			.add("groupId=" + groupId)
			.add("groupName='" + groupName + "'")
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

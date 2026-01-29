package com.skybot.event.message;

import com.skybot.event.OB11BaseMessageEvent;

import java.util.List;

public class OB11GroupMessageEvent extends OB11BaseMessageEvent
{
	public static final String MESSAGE_EVENT_TYPE = "group";

	public long groupId;

	public Sender sender;

	public List<Long> atList;

	public static class Sender
	{
		public long userId;

		public String nickname;

		public String card;

		public String role;

		@Override
		public String toString()
		{
			return "Sender{" +
				"userId=" + userId +
				", nickname='" + nickname + '\'' +
				", card='" + card + '\'' +
				", role='" + role + '\'' +
				'}';
		}
	}

	@Override
	public String toString()
	{
		return "OB11GroupMessageEvent{" +
			"groupId=" + groupId +
			", sender=" + sender +
			", messageId=" + messageId +
			", userId=" + userId +
			", message=" + messageArray +
			", rawMessage='" + rawMessage + '\'' +
			", time=" + time +
			", selfId=" + selfId +
			'}';
	}
}

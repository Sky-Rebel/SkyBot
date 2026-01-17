package com.skybot.event.message;

import com.skybot.event.OB11BaseMessageEvent;

public class OB11GroupMessageEvent extends OB11BaseMessageEvent
{
	public static final String MESSAGE_TYPE = "group";

	public long groupId;

	public Sender sender;

	public static class Sender
	{
		public long userId;

		public String nickname;

		public String card;

		public String title;

		public String level;

		public String role;

		@Override
		public String toString()
		{
			return "Sender{" +
				"userId=" + userId +
				", nickname='" + nickname + '\'' +
				", card='" + card + '\'' +
				", title='" + title + '\'' +
				", level='" + level + '\'' +
				", role='" + role + '\'' +
				'}';
		}
	}

	@Override
	public String toString()
	{
		return "OB11GroupMessageEvent{" +
			"message=" + message +
			", groupId=" + groupId +
			", sender=" + sender +
			", messageId=" + messageId +
			", userId=" + userId +
			", rawMessage='" + rawMessage + '\'' +
			", time=" + time +
			", self_id=" + self_id +
			", post_type='" + post_type + '\'' +
			'}';
	}
}

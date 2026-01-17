package com.skybot.event.message;

import com.skybot.event.OB11BaseMessageEvent;

public class OB11PrivateMessageEvent extends OB11BaseMessageEvent
{
	public static final String MESSAGE_EVENT_TYPE = "private";

	public String subType;

	public Sender sender;

	public static class Sender
	{
		public long userId;

		public String nickname;

		public String sex;

		public int age;

		@Override
		public String toString()
		{
			return "Sender{" +
				"userId=" + userId +
				", nickname='" + nickname + '\'' +
				", sex='" + sex + '\'' +
				", age=" + age +
				'}';
		}
	}

	@Override
	public String toString()
	{
		return "OB11PrivateMessageEvent{" +
			"subType='" + subType + '\'' +
			", sender=" + sender +
			", messageId=" + messageId +
			", userId=" + userId +
			", message=" + message +
			", rawMessage='" + rawMessage + '\'' +
			", time=" + time +
			", selfId=" + selfId +
			'}';
	}
}
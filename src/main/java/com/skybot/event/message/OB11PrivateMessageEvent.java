package com.skybot.event.message;

import com.skybot.event.OB11BaseMessageEvent;

public class OB11PrivateMessageEvent extends OB11BaseMessageEvent
{
	public static final String MESSAGE_TYPE = "private";

	public static final String[] SUB_TYPE = {"friend", "group", "other"};

	public Sender sender;

	public static class Sender
	{
		public long userId;

		public String nickname;

		public String sex;

		public int age;
	}
}
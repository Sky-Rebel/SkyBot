package com.github.sky_rebel.skybot.event.message;

import com.github.sky_rebel.skybot.event.OB11BaseMessageEvent;

public class OB11PrivateMessageEvent extends OB11BaseMessageEvent
{
	public Sender sender;

	public static class Sender
	{
		public long userId;

		public long groupId;

		public String nickname;

		@Override
		public String toString()
		{
			StringBuffer stringBuffer = new StringBuffer("Sender");
			stringBuffer.append("{");
			stringBuffer.append("userId").append("=").append(userId);
			stringBuffer.append(",").append("groupId").append("=").append(groupId);
			stringBuffer.append(",").append("nickname").append("=").append(nickname);
			stringBuffer.append('}');
			return stringBuffer.toString();
		}
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11PrivateMessageEvent");
		stringBuffer.append("{");
		stringBuffer.append("sender").append("=").append(sender);
		stringBuffer.append(",").append("userId").append("=").append(userId);
		stringBuffer.append(",").append("messageId").append("=").append(messageId);
		stringBuffer.append(",").append("rawMessage").append("=").append(rawMessage);
		stringBuffer.append(",").append("messageArray").append("=").append(messageArray);
		stringBuffer.append(",").append("messageElementArray").append("=").append(messageElementArray);
		stringBuffer.append(",").append("time").append("=").append(time);
		stringBuffer.append(",").append("selfId").append("=").append(selfId);
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
package com.github.sky_rebel.skybot.event.message_sent;

import com.github.sky_rebel.skybot.event.OB11BaseMessageSentEvent;

import java.util.StringJoiner;

public class OB11PrivateMessageSentEvent extends OB11BaseMessageSentEvent
{
	private Sender sender;

	public Sender getSender()
	{
		return sender;
	}

	public void setSender(Sender sender)
	{
		this.sender = sender;
	}

	public static class Sender
	{
		public long userId;

		public String nickname;

		public String card;
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11PrivateMessageSentEvent");
		stringBuffer.append("{");
		stringBuffer.append("sender").append("=").append(getSender());
		stringBuffer.append(",").append("targetId").append("=").append(getTargetId());
		stringBuffer.append(",").append("userId").append("=").append(getUserId());
		stringBuffer.append(",").append("messageId").append("=").append(getMessageId());
		stringBuffer.append(",").append("rawMessage").append("=").append(getRawMessage());
		stringBuffer.append(",").append("messageArray").append("=").append(getMessageArray());
		stringBuffer.append(",").append("messageElementArray").append("=").append(getMessageElementArray());
		stringBuffer.append(",").append("bot").append("=").append(getBot());
		stringBuffer.append(",").append("time").append("=").append(getTime());
		stringBuffer.append(",").append("selfId").append("=").append(getSelfId());
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
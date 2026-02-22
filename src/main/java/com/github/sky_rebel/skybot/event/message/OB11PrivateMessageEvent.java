package com.github.sky_rebel.skybot.event.message;

import com.github.sky_rebel.skybot.event.OB11BaseMessageEvent;

public class OB11PrivateMessageEvent extends OB11BaseMessageEvent
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
		private long userId;

		private long groupId;

		private String nickname;

		@Override
		public String toString()
		{
			StringBuffer stringBuffer = new StringBuffer("Sender");
			stringBuffer.append("{");
			stringBuffer.append("userId").append("=").append(getUserId());
			stringBuffer.append(",").append("groupId").append("=").append(getGroupId());
			stringBuffer.append(",").append("nickname").append("=").append(getNickname());
			stringBuffer.append('}');
			return stringBuffer.toString();
		}

		public long getUserId()
		{
			return userId;
		}

		public void setUserId(long userId)
		{
			this.userId = userId;
		}

		public long getGroupId()
		{
			return groupId;
		}

		public void setGroupId(long groupId)
		{
			this.groupId = groupId;
		}

		public String getNickname()
		{
			return nickname;
		}

		public void setNickname(String nickname)
		{
			this.nickname = nickname;
		}
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11PrivateMessageEvent");
		stringBuffer.append("{");
		stringBuffer.append("sender").append("=").append(getSender());
		stringBuffer.append(",").append("userId").append("=").append(getUserId());
		stringBuffer.append(",").append("messageId").append("=").append(getMessageId());
		stringBuffer.append(",").append("rawMessage").append("=").append(getRawMessage());
		stringBuffer.append(",").append("messageArray").append("=").append(getMessageArray());
		stringBuffer.append(",").append("messageElementArray").append("=").append(getMessageElementArray());
		stringBuffer.append(",").append("time").append("=").append(getTime());
		stringBuffer.append(",").append("selfId").append("=").append(getSelfId());
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
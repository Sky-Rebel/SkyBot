package com.github.sky_rebel.skybot.event.message_sent;

import com.github.sky_rebel.skybot.event.OB11BaseMessageSentEvent;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;

import java.util.List;
import java.util.StringJoiner;

public class OB11GroupMessageSentEvent extends OB11BaseMessageSentEvent
{
	private long groupId;

	private Sender sender;

	private boolean hasAt;

	private List<Long> atList;

	public long getGroupId()
	{
		return groupId;
	}

	public void setGroupId(long groupId)
	{
		this.groupId = groupId;
	}

	public Sender getSender()
	{
		return sender;
	}

	public void setSender(Sender sender)
	{
		this.sender = sender;
	}

	public boolean isHasAt()
	{
		return hasAt;
	}

	public void setHasAt(boolean hasAt)
	{
		this.hasAt = hasAt;
	}

	public List<Long> getAtList()
	{
		return atList;
	}

	public void setAtList(List<Long> atList)
	{
		this.atList = atList;
	}

	public static class Sender
	{
		private long userId;

		private String card;

		private String role;

		private String nickname;

		@Override
		public String toString()
		{
			StringBuffer stringBuffer = new StringBuffer("Sender");
			stringBuffer.append("{");
			stringBuffer.append("userId").append("=").append(getUserId());
			stringBuffer.append(",").append("card").append("=").append(getCard());
			stringBuffer.append(",").append("role").append("=").append(getRole());
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

		public String getCard()
		{
			return card;
		}

		public void setCard(String card)
		{
			this.card = card;
		}

		public String getRole()
		{
			return role;
		}

		public void setRole(String role)
		{
			this.role = role;
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
		return new StringJoiner(", ", OB11GroupMessageSentEvent.class.getSimpleName() + "[", "]")
			.add("userId=" + getUserId())
			.add("groupId=" + getGroupId())
			.add("sender=" + getSender())
			.add("time=" + getTime())
			.add("targetId=" + getTargetId())
			.add("messageId=" + getMessageId())
			.add("rawMessage='" + getRawMessage() + "'")
			.add("messageArray=" + getMessageArray())
			.add("time=" + getTime())
			.add("selfId=" + getSelfId())
			.toString();
	}
}

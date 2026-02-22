package com.github.sky_rebel.skybot.event.message;

import com.github.sky_rebel.skybot.BotApiService;
import com.github.sky_rebel.skybot.api.OB11GroupApiService;
import com.github.sky_rebel.skybot.api.OB11MessageApiService;
import com.github.sky_rebel.skybot.event.OB11BaseMessageEvent;
import com.github.sky_rebel.skybot.msg.element.OB11MsgElement;

import java.util.List;

public class OB11GroupMessageEvent extends OB11BaseMessageEvent
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
		StringBuffer stringBuffer = new StringBuffer("OB11GroupMessageEvent");
		stringBuffer.append("{");
		stringBuffer.append("groupId").append("=").append(getGroupId());
		stringBuffer.append(",").append("sender").append("=").append(getSender());
		stringBuffer.append(",").append("hasAt").append("=").append(isHasAt());
		stringBuffer.append(",").append("atList").append("=").append(getAtList());
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

	public long sendMessage(List<? extends OB11MsgElement> msgElementList)
	{
		return getOB11MessageApiService().sendGroupMessage(this.getGroupId(), msgElementList);
	}

	public long sendTextMessage(String text)
	{
		return getOB11MessageApiService().sendGroupTextMessage(this.getGroupId(), text);
	}

	public long sendReplyTextMessage(String text)
	{
		return getOB11MessageApiService().sendGroupReplyTextMessage(this.getGroupId(), this.getMessageId(), text);
	}

	public BotApiService.APIRequestResult setKick(long userId)
	{
		return getOB11GroupApiService().setGroupKick(this.getGroupId(), userId);
	}

	public BotApiService.APIRequestResult setBan(long userId, long duration)
	{
		return getOB11GroupApiService().setGroupBan(this.getGroupId(), userId, duration);
	}

	private OB11GroupApiService getOB11GroupApiService()
	{
		return getBot().getGroupApiService();
	}

	private OB11MessageApiService getOB11MessageApiService()
	{
		return getBot().getMessageApiService();
	}
}
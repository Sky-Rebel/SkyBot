package com.github.sky_rebel.skybot.event.message;

import com.github.sky_rebel.skybot.BotServer;
import com.github.sky_rebel.skybot.api.OB11GroupApiService;
import com.github.sky_rebel.skybot.api.OB11MessageApiService;
import com.github.sky_rebel.skybot.event.OB11BaseMessageEvent;
import com.github.sky_rebel.skybot.msg.element.OB11MsgElement;

import java.util.List;

public class OB11GroupMessageEvent extends OB11BaseMessageEvent
{
	public long groupId;

	public Sender sender;

	public boolean hasAt;

	public List<Long> atList;

	public static class Sender
	{
		public long userId;

		public String card;

		public String role;

		public String nickname;

		@Override
		public String toString()
		{
			StringBuffer stringBuffer = new StringBuffer("Sender");
			stringBuffer.append("{");
			stringBuffer.append("userId").append("=").append(userId);
			stringBuffer.append(",").append("card").append("=").append(card);
			stringBuffer.append(",").append("role").append("=").append(role);
			stringBuffer.append(",").append("nickname").append("=").append(nickname);
			stringBuffer.append('}');
			return stringBuffer.toString();
		}
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11GroupMessageEvent");
		stringBuffer.append("{");
		stringBuffer.append("groupId").append("=").append(groupId);
		stringBuffer.append(",").append("sender").append("=").append(sender);
		stringBuffer.append(",").append("hasAt").append("=").append(hasAt);
		stringBuffer.append(",").append("atList").append("=").append(atList);
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

	public long sendMessage(List<? extends OB11MsgElement> msgElementList)
	{
		return getOB11MessageApiService().sendGroupMessage(this.groupId, msgElementList);
	}

	public long sendTextMessage(String text)
	{
		return getOB11MessageApiService().sendGroupTextMessage(this.groupId, text);
	}

	public long sendReplyTextMessage(String text)
	{
		return getOB11MessageApiService().sendGroupReplyTextMessage(this.groupId, this.messageId, text);
	}

	public BotServer.APIRequestResult setKick(long userId)
	{
		return getOB11GroupApiService().setGroupKick(this.groupId, userId);
	}

	public BotServer.APIRequestResult setBan(long userId, long duration)
	{
		return getOB11GroupApiService().setGroupBan(this.groupId, userId, duration);
	}

	private OB11GroupApiService getOB11GroupApiService()
	{
		return bot.getOB11GroupApiService();
	}

	private OB11MessageApiService getOB11MessageApiService()
	{
		return bot.getOB11MessageApiService();
	}
}
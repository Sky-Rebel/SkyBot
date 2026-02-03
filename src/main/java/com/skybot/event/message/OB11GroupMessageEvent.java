package com.skybot.event.message;

import com.skybot.api.OB11GroupApiService;
import com.skybot.api.OB11MessageApiService;
import com.skybot.bot.BotServer;
import com.skybot.bot.msg.element.OB11AtMsgElement;
import com.skybot.bot.msg.element.OB11MsgElement;
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
		return (OB11GroupApiService) bot.getApiService(OB11GroupApiService.class);
	}

	private OB11MessageApiService getOB11MessageApiService()
	{
		return (OB11MessageApiService) bot.getApiService(OB11MessageApiService.class);
	}
}
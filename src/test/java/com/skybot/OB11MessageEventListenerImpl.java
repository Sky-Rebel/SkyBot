package com.skybot;

import com.skybot.api.OB11GroupApiService;
import com.skybot.api.OB11MessageApiService;
import com.skybot.api.data.*;
import com.skybot.bot.BotServer;
import com.skybot.event.handling.listener.message.OB11MessageEventListener;
import com.skybot.event.message.OB11GroupMessageEvent;
import com.skybot.event.message.OB11PrivateMessageEvent;

import java.util.List;

public class OB11MessageEventListenerImpl implements OB11MessageEventListener
{
	@Override
	public void onGroupMessage(OB11GroupMessageEvent ob11GroupMessageEvent)
	{
		if (ob11GroupMessageEvent.rawMessage.startsWith("设置群头衔"))
		{
			String[] commands = ob11GroupMessageEvent.rawMessage.split(" ");
			String a = commands[1];
			String b = commands[2];
			BotServer.APIRequestResult apiRequestResult = OB11GroupApiService.setGroupSpecialTitle(ob11GroupMessageEvent.groupId, Long.parseLong(a), b);
			OB11MessageApiService.sendGroupTextMessage(ob11GroupMessageEvent.groupId, apiRequestResult.toString());
		}

		if (ob11GroupMessageEvent.rawMessage.startsWith("全禁"))
		{
			BotServer.APIRequestResult apiRequestResult = OB11GroupApiService.setGroupWholeBan(ob11GroupMessageEvent.groupId, true);
			OB11MessageApiService.sendGroupTextMessage(ob11GroupMessageEvent.groupId, apiRequestResult.toString());
		}

		if (ob11GroupMessageEvent.rawMessage.startsWith("全解"))
		{
			BotServer.APIRequestResult apiRequestResult = OB11GroupApiService.setGroupWholeBan(ob11GroupMessageEvent.groupId, false);
			OB11MessageApiService.sendGroupTextMessage(ob11GroupMessageEvent.groupId, apiRequestResult.toString());
		}

		if (ob11GroupMessageEvent.rawMessage.startsWith("Test: At List ->"))
		{
			List<Long> atList = ob11GroupMessageEvent.atList;
			if (atList.isEmpty())
			{
				OB11MessageApiService.sendGroupTextMessage(ob11GroupMessageEvent.groupId, "Test Failed : At List is Empty");
			}
			else
			{
				OB11MessageApiService.sendGroupTextMessage(ob11GroupMessageEvent.groupId, "Test Successful : At List -> " + atList);
			}
		}
		if (ob11GroupMessageEvent.rawMessage.startsWith("Test: Group Ban ->"))
		{
			String[] commands = ob11GroupMessageEvent.rawMessage.split(" ");
			long groupId = ob11GroupMessageEvent.groupId;
			long duration = Long.parseLong(commands[5]);
			String message;
			long userId = 0;
			if (ob11GroupMessageEvent.atList.isEmpty())
			{
				message = "请提供有效禁言目标！";
			}
			else
			{
				message = "禁言对象为" + ob11GroupMessageEvent.atList.getFirst() +
					"，禁言事件为" + duration + "秒";
				userId = ob11GroupMessageEvent.atList.getFirst();
			}

			BotServer.APIRequestResult apiRequestResult = OB11GroupApiService.setGroupBan(groupId, userId, duration);
			OB11MessageApiService.sendGroupTextMessage(ob11GroupMessageEvent.groupId, message);
		}
	}

	@Override
	public void onPrivateMessage(OB11PrivateMessageEvent ob11PrivateMessageEvent)
	{
		if (ob11PrivateMessageEvent.rawMessage.equals("test"))
			OB11MessageApiService.sendPrivateTextMessage(ob11PrivateMessageEvent.userId, "Skybot Test Success");
	}
}
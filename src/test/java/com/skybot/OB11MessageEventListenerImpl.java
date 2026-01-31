package com.skybot;

import com.skybot.api.OB11GroupApiService;
import com.skybot.api.OB11MessageApiService;
import com.skybot.api.extend.google_book.GB1ApiService;
import com.skybot.api.extend.google_book.GB1ResponseInfo;
import com.skybot.bot.Bot;
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
		if (ob11GroupMessageEvent.rawMessage.startsWith("查找图书"))
		{
			String[] command = ob11GroupMessageEvent.rawMessage.split(" ");
			String volumeName = command[1];
			int macResults = Integer.parseInt(command[2]);
			GB1ApiService gb1ApiService = new GB1ApiService("APIKEY");
			GB1ResponseInfo gb1ResponseInfo = gb1ApiService.searchBook
			(
				command[1],
				false,
				null,
				false,
				null,
				false,
				null,
				false,
				null,
				false,
				null,
				false,
				null,
				false,
				null,
				false,
				-1,
				macResults,
				null,
				null,
				null
			);
			StringBuilder result = null;
			long totalItems = gb1ResponseInfo.totalItems;
			if (totalItems == 0) result = new StringBuilder("未查找到相关书籍 -> " + volumeName);
			else
			{
				result = new StringBuilder(String.format("查询到%d个结果 展示%d个结果", totalItems, macResults)).append("\n".repeat(2));
				if (gb1ResponseInfo.gb1ItemInfoList != null)
				{
					var ref = new Object()
					{
						int i = 0;
					};
					StringBuilder finalResult = result;
					gb1ResponseInfo.gb1ItemInfoList.forEach(gb1ItemInfo ->
					{
						ref.i++;
						finalResult
						.append("【序列】")
						.append(ref.i)
						.append("\n")
						.append("【书名】")
						.append(gb1ItemInfo.gb1VolumeInfo.title)
						.append("\n")
						.append("【作者】")
						.append(gb1ItemInfo.gb1VolumeInfo.authors  == null ? "未知作者" : gb1ItemInfo.gb1VolumeInfo.authors)
						.append("\n")
						.append("【页数】")
						.append(gb1ItemInfo.gb1VolumeInfo.pageCount).append("\n");
						if (gb1ItemInfo.gb1SearchInfo != null)
						{
							finalResult.append("【简介】");
							finalResult.append(gb1ItemInfo.gb1SearchInfo.textSnippet).append("\n");
						}
						finalResult.append("\n");
					});
					OB11MessageApiService.sendGroupTextMessage(ob11GroupMessageEvent.groupId, finalResult.toString());
				}
			}
		}
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
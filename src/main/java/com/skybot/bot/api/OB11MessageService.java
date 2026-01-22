package com.skybot.bot.api;

import com.skybot.bot.BotServer;
import com.skybot.bot.msg.OB11MsgElement;
import com.skybot.bot.msg.OB11TextMsgElement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OB11MessageService
{
	public static final String API_PATH_SEND_GROUP_MSG = "/send_group_msg";

	public static final String API_PATH_SEND_PRIVATE_MSG = "/send_private_msg";

	public static final Logger LOGGER = LoggerFactory.getLogger(OB11MessageService.class);

	public enum MessageSendType
	{
		GROUP_MESSAGE,
		PRIVATE_MESSAGE
	}

	public static long sendGroupTextMessage(long groupId, String text)
	{
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
		ob11TextMsgElement.setText(text);
		msgElementArray.add(ob11TextMsgElement);
		return sendGroupMessage(groupId, msgElementArray);
	}

	public static long sendPrivateTextMessage(long userId, String text)
	{
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
		ob11TextMsgElement.setText(text);
		msgElementArray.add(ob11TextMsgElement);
		return sendPrivateMessage(userId, msgElementArray);
	}

	public static long sendGroupMessage(long groupId, List<? extends OB11MsgElement> msgElementArray)
	{
		return sendMessage(MessageSendType.GROUP_MESSAGE, groupId, 0, msgElementArray);
	}

	public static long sendPrivateMessage(long userId, List<? extends OB11MsgElement> msgElementArray)
	{
		return sendMessage(MessageSendType.PRIVATE_MESSAGE, 0, userId, msgElementArray);
	}

	public static long sendMessage(MessageSendType messageSendType, long groupId, long userId, List<? extends OB11MsgElement> msgElementArray)
	{
		BotServer botServer = null;
		JSONObject rootObject = new JSONObject();
		switch (messageSendType)
		{
			case GROUP_MESSAGE ->
			{
				botServer = new BotServer(API_PATH_SEND_GROUP_MSG);
				rootObject.put("group_id", groupId);
			}
			case PRIVATE_MESSAGE ->
			{
				botServer = new BotServer(API_PATH_SEND_PRIVATE_MSG);
				rootObject.put("user_id", userId);
			}
		}
		JSONArray messageArray = new JSONArray();
		msgElementArray.forEach(ob11MsgElement -> messageArray.put(ob11MsgElement.toJSONObject()));
		rootObject.put("message", messageArray);
		System.out.println(rootObject.toString(4));
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		JSONObject dataObject = apiRequestResult.data;
		if (apiRequestResult.isSuccess) return dataObject.getLong("message_id");
		else
		{
			int retCode = apiRequestResult.retcode;
			String errorMsg = apiRequestResult.message;
			LOGGER.error("消息发送API调用失败 -> ".concat(String.valueOf(retCode)).concat(":").concat(errorMsg));
			return -1;
		}
	}
}
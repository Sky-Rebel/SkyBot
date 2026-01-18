package com.skybot.api;

import com.skybot.bot.BotServer;
import org.json.JSONArray;
import org.json.JSONObject;

public class MessageManage
{
	public static final String API_PATH = "/send_group_msg";

	public static void sendGroupTextMessage(long groupId, String text)
	{
		BotServer botServer = new BotServer(API_PATH);
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		JSONArray messageArray = new JSONArray();
		JSONObject messageObject = new JSONObject();
		messageObject.put("type", "text");
		JSONObject dateObject = new JSONObject();
		dateObject.put("text", text);
		messageObject.put("data", dateObject);
		messageArray.put(messageObject);
		rootObject.put("message", messageArray);
		BotServer.BotRequestResult botRequestResult = botServer.sendRequest(rootObject.toString());
		System.out.println("既已发送API请求！");
		System.out.println(botRequestResult.toString());
	}
}

package com.skybot.bot.event.handling.handler;

import com.skybot.bot.event.handling.dispatcher.OB11MessageEventDispatcher;
import com.skybot.bot.event.message.OB11GroupMessageEvent;
import com.skybot.bot.event.message.OB11PrivateMessageEvent;
import org.json.JSONObject;

import static com.skybot.bot.event.handling.handler.OB11EventHandler.LOGGER;

public class OB11MessageEventHandler
{
	/**
	 * 分发消息事件
	 * @param ob11EventPostData Napcat客户端上报的原始数据
	 */
	public static void dispatch(JSONObject ob11EventPostData)
	{
		final String messageEventType = ob11EventPostData.getString("message_type");
		if (messageEventType.equals(OB11GroupMessageEvent.MESSAGE_EVENT_TYPE))
		{
			OB11GroupMessageEvent ob11GroupMessageEvent = new OB11GroupMessageEvent();
			ob11GroupMessageEvent.groupId = ob11EventPostData.getLong("group_id");
			ob11GroupMessageEvent.messageId = ob11EventPostData.getLong("message_id");
			ob11GroupMessageEvent.rawMessage = ob11EventPostData.getString("raw_message");
			ob11GroupMessageEvent.selfId = ob11EventPostData.getLong("self_id");
			ob11GroupMessageEvent.time = ob11EventPostData.getLong("time");
			ob11GroupMessageEvent.userId = ob11EventPostData.getLong("user_id");
			ob11GroupMessageEvent.message = ob11EventPostData.getJSONArray("message");
			OB11GroupMessageEvent.Sender sender = new OB11GroupMessageEvent.Sender();
			JSONObject senderJson = ob11EventPostData.getJSONObject("sender");
			sender.card = senderJson.getString("card");
			sender.userId = senderJson.getLong("user_id");
			sender.nickname = senderJson.getString("nickname");
			sender.role = senderJson.getString("role");
			ob11GroupMessageEvent.sender = sender;
			OB11MessageEventDispatcher.onGroupMessage(ob11GroupMessageEvent);
		}
		else if (messageEventType.equals(OB11PrivateMessageEvent.MESSAGE_EVENT_TYPE))
		{
			OB11PrivateMessageEvent ob11PrivateMessageEvent = new OB11PrivateMessageEvent();
			ob11PrivateMessageEvent.messageId = ob11EventPostData.getLong("message_id");
			ob11PrivateMessageEvent.rawMessage = ob11EventPostData.getString("raw_message");
			ob11PrivateMessageEvent.selfId = ob11EventPostData.getLong("self_id");
			ob11PrivateMessageEvent.time = ob11EventPostData.getLong("time");
			ob11PrivateMessageEvent.userId = ob11EventPostData.getLong("user_id");
			ob11PrivateMessageEvent.message = ob11EventPostData.getJSONArray("message");
			OB11PrivateMessageEvent.Sender sender = new OB11PrivateMessageEvent.Sender();
			JSONObject senderJson = ob11EventPostData.getJSONObject("sender");
			sender.userId = senderJson.getLong("user_id");
			sender.nickname = senderJson.getString("nickname");
			ob11PrivateMessageEvent.sender = sender;
			OB11MessageEventDispatcher.onPrivateMessage(ob11PrivateMessageEvent);
		}
		else LOGGER.warn("未知消息事件类型！");
	}
}
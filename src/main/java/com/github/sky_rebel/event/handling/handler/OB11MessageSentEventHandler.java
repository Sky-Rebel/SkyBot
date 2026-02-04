package com.github.sky_rebel.event.handling.handler;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.event.handling.dispatcher.OB11MessageSentEventDispatcher;
import com.github.sky_rebel.event.message_sent.OB11GroupMessageSentEvent;
import com.github.sky_rebel.event.message_sent.OB11PrivateMessageSentEvent;
import org.json.JSONObject;

import static com.github.sky_rebel.event.handling.handler.OB11EventHandler.LOGGER;

public class OB11MessageSentEventHandler
{
	/**
	 * 分发自发消息事件
	 * @param ob11EventPostData Napcat客户端上报的原始数据
	 */
	public static void dispatch(Bot bot, JSONObject ob11EventPostData)
	{
		final String messageType = ob11EventPostData.getString("message_type");
		if (messageType.equals("group"))
		{
			OB11GroupMessageSentEvent ob11GroupMessageSentEvent = new OB11GroupMessageSentEvent();
			ob11GroupMessageSentEvent.bot = bot;
			ob11GroupMessageSentEvent.time = ob11EventPostData.getLong("time");
			ob11GroupMessageSentEvent.selfId = ob11EventPostData.getLong("self_id");
			ob11GroupMessageSentEvent.groupId = ob11EventPostData.getLong("group_id");
			ob11GroupMessageSentEvent.messageId = ob11EventPostData.getLong("message_id");
			ob11GroupMessageSentEvent.groupName = ob11EventPostData.getString("group_name");
			ob11GroupMessageSentEvent.userId = ob11EventPostData.getLong("user_id");
			ob11GroupMessageSentEvent.messageArray = ob11EventPostData.getJSONArray("message");
			ob11GroupMessageSentEvent.targetId = ob11EventPostData.getLong("target_id");
			ob11GroupMessageSentEvent.rawMessage = ob11EventPostData.getString("raw_message");
			JSONObject senderJson = ob11EventPostData.getJSONObject("sender");
			OB11GroupMessageSentEvent.Sender sender = new OB11GroupMessageSentEvent.Sender();
			sender.card = senderJson.getString("card");
			sender.role = senderJson.getString("role");
			sender.nickname = senderJson.getString("nickname");
			sender.userId = senderJson.getLong("user_id");
			ob11GroupMessageSentEvent.sender = sender;
			OB11MessageSentEventDispatcher.onGroupSentMessage(bot, ob11GroupMessageSentEvent);
		}
		else if (messageType.equals("private"))
		{
			OB11PrivateMessageSentEvent ob11PrivateMessageSentEvent = new OB11PrivateMessageSentEvent();
			ob11PrivateMessageSentEvent.bot = bot;
			ob11PrivateMessageSentEvent.time = ob11EventPostData.getLong("time");
			ob11PrivateMessageSentEvent.selfId = ob11EventPostData.getLong("self_id");
			ob11PrivateMessageSentEvent.messageId = ob11EventPostData.getLong("message_id");
			ob11PrivateMessageSentEvent.userId = ob11EventPostData.getLong("user_id");
			ob11PrivateMessageSentEvent.messageArray = ob11EventPostData.getJSONArray("message");
			ob11PrivateMessageSentEvent.targetId = ob11EventPostData.getLong("target_id");
			ob11PrivateMessageSentEvent.rawMessage = ob11EventPostData.getString("raw_message");
			JSONObject senderJson = ob11EventPostData.getJSONObject("sender");
			OB11PrivateMessageSentEvent.Sender sender = new OB11PrivateMessageSentEvent.Sender();
			sender.card = senderJson.getString("card");
			sender.nickname = senderJson.getString("nickname");
			sender.userId = senderJson.getLong("user_id");
			ob11PrivateMessageSentEvent.sender = sender;
			OB11MessageSentEventDispatcher.onPrivateSentMessage(bot, ob11PrivateMessageSentEvent);

		}
		else LOGGER.warn("未知自发消息事件类型 -> {}", messageType);
	}
}
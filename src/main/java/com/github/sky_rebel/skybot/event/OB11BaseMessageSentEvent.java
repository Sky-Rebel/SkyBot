package com.github.sky_rebel.skybot.event;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.event.message_sent.OB11GroupMessageSentEvent;
import com.github.sky_rebel.skybot.event.message_sent.OB11PrivateMessageSentEvent;
import com.github.sky_rebel.skybot.msg.OB11MessageSegment;
import org.json.JSONObject;

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;
import static com.github.sky_rebel.skybot.msg.OB11MessageSegment.getMessageElementArray;

public class OB11BaseMessageSentEvent extends OB11GroupMessageEvent
{
	public long targetId;

	public enum Type
	{
		GROUP,
		PRIVATE
	}

	public static OB11BaseMessageSentEvent getInstance(Bot bot, JSONObject json)
	{
		final String messageType = json.optString("message_type");
		try
		{
			Type type = Type.valueOf(messageType.toUpperCase());
			return switch (type)
			{
				case GROUP -> createGroupMessageSentEvent(bot, json);
				case PRIVATE -> createPrivateMessageSentEvent(bot, json);
			};
		}
		catch (IllegalArgumentException e)
		{
			LOGGER.error("未知消息自发事件类型 -> {}", messageType);
			return null;
		}
	}

	private static OB11GroupMessageSentEvent createGroupMessageSentEvent(Bot bot, JSONObject json)
	{
		OB11GroupMessageSentEvent ob11GroupMessageSentEvent = new OB11GroupMessageSentEvent();
		ob11GroupMessageSentEvent.bot = bot;
		ob11GroupMessageSentEvent.time = json.optLong("time");
		ob11GroupMessageSentEvent.selfId = json.optLong("self_id");
		ob11GroupMessageSentEvent.userId = json.optLong("user_id");
		ob11GroupMessageSentEvent.groupId = json.optLong("group_id");
		ob11GroupMessageSentEvent.targetId = json.optLong("taropt_id");
		ob11GroupMessageSentEvent.messageId = json.optLong("message_id");
		ob11GroupMessageSentEvent.rawMessage = json.optString("raw_message");
		ob11GroupMessageSentEvent.messageArray = json.optJSONArray("message");
		ob11GroupMessageSentEvent.messageElementArray = getMessageElementArray(ob11GroupMessageSentEvent.messageArray);
		if (!ob11GroupMessageSentEvent.messageElementArray.isEmpty())
		{
			ob11GroupMessageSentEvent.atList = OB11MessageSegment.getAtList(ob11GroupMessageSentEvent.messageElementArray);
			if (!ob11GroupMessageSentEvent.atList.isEmpty())
			{
				ob11GroupMessageSentEvent.hasAt = true;
			}
		}
		JSONObject senderJson = json.optJSONObject("sender");
		OB11GroupMessageSentEvent.Sender sender = new OB11GroupMessageSentEvent.Sender();
		sender.card = senderJson.optString("card");
		sender.role = senderJson.optString("role");
		sender.userId = senderJson.optLong("user_id");
		sender.nickname = senderJson.optString("nickname");
		ob11GroupMessageSentEvent.sender = sender;
		return ob11GroupMessageSentEvent;
	}

	private static OB11PrivateMessageSentEvent createPrivateMessageSentEvent(Bot bot, JSONObject json)
	{
		OB11PrivateMessageSentEvent ob11PrivateMessageSentEvent = new OB11PrivateMessageSentEvent();
		ob11PrivateMessageSentEvent.bot = bot;
		ob11PrivateMessageSentEvent.time = json.optLong("time");
		ob11PrivateMessageSentEvent.selfId = json.optLong("self_id");
		ob11PrivateMessageSentEvent.userId = json.optLong("user_id");
		ob11PrivateMessageSentEvent.targetId = json.optLong("target_id");
		ob11PrivateMessageSentEvent.messageId = json.optLong("message_id");
		ob11PrivateMessageSentEvent.rawMessage = json.optString("raw_message");
		ob11PrivateMessageSentEvent.messageArray = json.optJSONArray("message");
		JSONObject senderJson = json.optJSONObject("sender");
		OB11PrivateMessageSentEvent.Sender sender = new OB11PrivateMessageSentEvent.Sender();
		sender.card = senderJson.optString("card");
		sender.userId = senderJson.optLong("user_id");
		sender.nickname = senderJson.optString("nickname");
		ob11PrivateMessageSentEvent.sender = sender;
		return ob11PrivateMessageSentEvent;
	}
}
package com.github.sky_rebel.skybot.event;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.handling.handler.OB11MessageEventHandler;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11PrivateMessageEvent;
import com.github.sky_rebel.skybot.msg.OB11MessageSegment;
import com.github.sky_rebel.skybot.msg.element.OB11MsgElement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static com.github.sky_rebel.skybot.msg.OB11MessageSegment.getMessageElementArray;

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;

public class OB11BaseMessageEvent extends OB11BaseEvent
{
	public long userId;

	public long messageId;

	public String rawMessage;

	public JSONArray messageArray;

	public List<OB11MsgElement> messageElementArray;

	public enum Type
	{
		GROUP,
		PRIVATE
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11BaseMessageEvent");
		stringBuffer.append("{");
		stringBuffer.append(",").append("time").append("=").append(time);
		stringBuffer.append(",").append("selfId").append("=").append(selfId);
		stringBuffer.append(",").append("userId").append("=").append(userId);
		stringBuffer.append(",").append("messageId").append("=").append(messageId);
		stringBuffer.append(",").append("rawMessage").append("=").append(rawMessage);
		stringBuffer.append(",").append("messageArray").append("=").append(messageArray);
		stringBuffer.append('}');
		return stringBuffer.toString();
	}

	public static OB11BaseMessageEvent getInstance(Bot bot, JSONObject json)
	{
		final String messageType = json.optString("message_type");
		try
		{
			Type type = Type.valueOf(messageType.toUpperCase());
			return switch (type)
			{
				case GROUP -> createGroupMessageEvent(bot, json);

				case PRIVATE -> createPrivateMessageEvent(bot, json);
			};
		}
		catch (IllegalArgumentException e)
		{
			LOGGER.warn("未知消息事件类型 -> {}", messageType);
			return null;
		}
	}

	private static OB11GroupMessageEvent createGroupMessageEvent(Bot bot, JSONObject json)
	{
		OB11GroupMessageEvent ob11GroupMessageEvent = new OB11GroupMessageEvent();
		ob11GroupMessageEvent.bot = bot;
		ob11GroupMessageEvent.time = json.optLong("time");
		ob11GroupMessageEvent.selfId = json.optLong("self_id");
		ob11GroupMessageEvent.userId = json.optLong("user_id");
		ob11GroupMessageEvent.groupId = json.optLong("group_id");
		ob11GroupMessageEvent.messageId = json.optLong("message_id");
		ob11GroupMessageEvent.rawMessage = json.optString("raw_message");
		ob11GroupMessageEvent.messageArray = json.optJSONArray("message");
		ob11GroupMessageEvent.messageElementArray = getMessageElementArray(ob11GroupMessageEvent.messageArray);
		if (!ob11GroupMessageEvent.messageElementArray.isEmpty())
		{
			ob11GroupMessageEvent.atList = OB11MessageSegment.getAtList(ob11GroupMessageEvent.messageElementArray);
			if (!ob11GroupMessageEvent.atList.isEmpty())
			{
				ob11GroupMessageEvent.hasAt = true;
			}
		}
		JSONObject senderJson = json.optJSONObject("sender");
		OB11GroupMessageEvent.Sender sender = new OB11GroupMessageEvent.Sender();
		sender.card = senderJson.optString("card");
		sender.role = senderJson.optString("role");
		sender.userId = senderJson.optLong("user_id");
		sender.nickname = senderJson.optString("nickname");
		ob11GroupMessageEvent.sender = sender;
		return ob11GroupMessageEvent;
	}

	private static OB11PrivateMessageEvent createPrivateMessageEvent(Bot bot, JSONObject json)
	{
		OB11PrivateMessageEvent ob11PrivateMessageEvent = new OB11PrivateMessageEvent();
		ob11PrivateMessageEvent.bot = bot;
		ob11PrivateMessageEvent.time = json.optLong("time");
		ob11PrivateMessageEvent.selfId = json.optLong("self_id");
		ob11PrivateMessageEvent.userId = json.optLong("user_id");
		ob11PrivateMessageEvent.messageId = json.optLong("message_id");
		ob11PrivateMessageEvent.rawMessage = json.optString("raw_message");
		ob11PrivateMessageEvent.messageArray = json.optJSONArray("message");
		ob11PrivateMessageEvent.messageElementArray = getMessageElementArray(ob11PrivateMessageEvent.messageArray);
		JSONObject senderJson = json.optJSONObject("sender");
		OB11PrivateMessageEvent.Sender sender = new OB11PrivateMessageEvent.Sender();
		sender.userId = senderJson.optLong("user_id");
		sender.groupId = senderJson.optLong("group_id");
		sender.nickname = senderJson.optString("nickname");
		ob11PrivateMessageEvent.sender = sender;
		OB11MessageEventHandler.onPrivateMessage(bot, ob11PrivateMessageEvent);
		return ob11PrivateMessageEvent;
	}
}
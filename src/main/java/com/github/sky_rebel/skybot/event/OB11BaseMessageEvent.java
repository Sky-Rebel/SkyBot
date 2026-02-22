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

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;

/**
 * Represents a base event for handling messages in the OneBot v11 protocol.
 * This class extends {@link OB11BaseEvent} and provides additional functionality
 * specific to message events, such as user ID, message ID, and raw message content.
 * It also includes methods to create specific message event instances based on the
 * type of message (group or private).
 *
 * @author Sky_Rebel
 * @lastUpdate  2026-02-23
 */
public class OB11BaseMessageEvent extends OB11BaseEvent
{

	/**
	 * Enumerates the types of messages that can be processed within the OneBot v11 protocol.
	 * Each constant represents a category of message, distinguishing between group and private
	 * messages. This enumeration is used to classify the context in which a message was sent,
	 * allowing for appropriate handling and response generation based on the message type.
	 */
	public enum Type
	{
		GROUP,
		PRIVATE
	}

	/**
	 * Represents the unique identifier of the user associated with this event.
	 * This field is used to store the ID of the user who triggered the event or
	 * is involved in the interaction. It is crucial for identifying and tracking
	 * specific users within the application, especially when handling events such
	 * as messages, requests, or notices.
	 */
	private long userId;

	/**
	 * Represents the unique identifier for a message. This field is used to store the ID of the message
	 * associated with the event, which can be used to reference or manage the message within the OneBot v11 protocol.
	 * The message ID is crucial for tracking and managing messages, such as recalling or responding to them.
	 */
	private long messageId;

	/**
	 * Represents the raw, unprocessed message content as received from the event.
	 * This field holds the original message string before any parsing or processing is applied.
	 * It can be used to access the exact text of the message as it was sent by the user.
	 */
	private String rawMessage;

	/**
	 * Represents an array of messages in JSON format. This JSONArray is used to store and manage
	 * a collection of message objects, each potentially containing various pieces of information
	 * such as text, images, or other media. The array can be manipulated to add, remove, or
	 * retrieve individual message objects, facilitating the handling of multiple messages within
	 * a single event.
	 */
	private JSONArray messageArray;

	/**
	 * Represents a list of message elements that are part of the message associated with this event.
	 * Each element in the list is an instance of OB11MsgElement, which can represent various types
	 * of message content such as text, images, or mentions. This list provides a structured way to
	 * access and manipulate the individual components of a message, allowing for more granular
	 * processing and handling of message data.
	 */
	private List<OB11MsgElement> messageElementArray;

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public long getMessageId()
	{
		return messageId;
	}

	public void setMessageId(long messageId)
	{
		this.messageId = messageId;
	}

	public String getRawMessage()
	{
		return rawMessage;
	}

	public void setRawMessage(String rawMessage)
	{
		this.rawMessage = rawMessage;
	}

	public JSONArray getMessageArray()
	{
		return messageArray;
	}

	public void setMessageArray(JSONArray messageArray)
	{
		this.messageArray = messageArray;
	}

	public List<OB11MsgElement> getMessageElementArray()
	{
		return messageElementArray;
	}

	public void setMessageElementArray(List<OB11MsgElement> messageElementArray)
	{
		this.messageElementArray = messageElementArray;
	}

	/**
	 * Retrieves an instance of a message event based on the provided Bot and JSON object.
	 *
	 * @param bot The Bot instance to associate with the event.
	 * @param json The JSON object containing the event data, including the "message_type" field.
	 * @return An initialized OB11BaseMessageEvent (or its subclass) instance, populated with the data from the JSON object,
	 * or null if the message type is unrecognized.
	 */
	public static OB11BaseMessageEvent getInstance(Bot bot, JSONObject json)
	{
		final String messageType = json.optString("message_type");
		try
		{
			Type type = Type.valueOf(messageType.toUpperCase());
			return switch (type)
			{
				case GROUP ->
				createGroupMessageEvent(bot, json);

				case PRIVATE ->
				createPrivateMessageEvent(bot, json);
			};
		}
		catch (IllegalArgumentException e)
		{
			LOGGER.warn("未知消息事件类型 -> {}", messageType);
			return null;
		}
	}

	/**
	 * Creates an instance of OB11GroupMessageEvent based on the provided Bot and JSON object.
	 *
	 * @param bot The Bot instance to associate with the event.
	 * @param json The JSON object containing the event data.
	 * @return An initialized OB11GroupMessageEvent instance, populated with the data from the JSON object.
	 */
	private static OB11GroupMessageEvent createGroupMessageEvent(Bot bot, JSONObject json)
	{
		OB11GroupMessageEvent ob11GroupMessageEvent = new OB11GroupMessageEvent();
		ob11GroupMessageEvent.setBot(bot);
		ob11GroupMessageEvent.setTime(json.optLong("time"));
		ob11GroupMessageEvent.setSelfId(json.optLong("self_id"));
		ob11GroupMessageEvent.setUserId(json.optLong("user_id"));
		ob11GroupMessageEvent.setGroupId(json.optLong("group_id"));
		ob11GroupMessageEvent.setMessageId(json.optLong("message_id"));
		ob11GroupMessageEvent.setRawMessage(json.optString("raw_message"));
		ob11GroupMessageEvent.setMessageArray(json.optJSONArray("message"));
		ob11GroupMessageEvent.setMessageElementArray(OB11MessageSegment.getMessageElementArray(ob11GroupMessageEvent.getMessageArray()));
		if (!ob11GroupMessageEvent.getMessageElementArray().isEmpty())
		{
			ob11GroupMessageEvent.setAtList(OB11MessageSegment.getAtList(ob11GroupMessageEvent.getMessageElementArray()));
			if (!ob11GroupMessageEvent.getAtList().isEmpty())
			{
				ob11GroupMessageEvent.setHasAt(true);
			}
		}
		JSONObject senderJson = json.optJSONObject("sender");
		OB11GroupMessageEvent.Sender sender = new OB11GroupMessageEvent.Sender();
		sender.setCard(senderJson.optString("card"));
		sender.setRole(senderJson.optString("role"));
		sender.setUserId(senderJson.optLong("user_id"));
		sender.setNickname(senderJson.optString("nickname"));
		ob11GroupMessageEvent.setSender(sender);
		return ob11GroupMessageEvent;
	}

	/**
	 * Creates an instance of OB11PrivateMessageEvent based on the provided Bot and JSON object.
	 *
	 * @param bot The Bot instance to associate with the event.
	 * @param json The JSON object containing the event data.
	 * @return An initialized OB11PrivateMessageEvent instance, populated with the data from the JSON object.
	 */
	private static OB11PrivateMessageEvent createPrivateMessageEvent(Bot bot, JSONObject json)
	{
		OB11PrivateMessageEvent ob11PrivateMessageEvent = new OB11PrivateMessageEvent();
		ob11PrivateMessageEvent.setBot(bot);
		ob11PrivateMessageEvent.setTime(json.optLong("time"));
		ob11PrivateMessageEvent.setSelfId(json.optLong("self_id"));
		ob11PrivateMessageEvent.setUserId(json.optLong("user_id"));
		ob11PrivateMessageEvent.setMessageId(json.optLong("message_id"));
		ob11PrivateMessageEvent.setRawMessage(json.optString("raw_message"));
		ob11PrivateMessageEvent.setMessageArray(json.optJSONArray("message"));
		ob11PrivateMessageEvent.setMessageElementArray(OB11MessageSegment.getMessageElementArray(ob11PrivateMessageEvent.getMessageArray()));
		JSONObject senderJson = json.optJSONObject("sender");
		OB11PrivateMessageEvent.Sender sender = new OB11PrivateMessageEvent.Sender();
		sender.setUserId(senderJson.optLong("user_id"));
		sender.setGroupId(senderJson.optLong("group_id"));
		sender.setNickname(senderJson.optString("nickname"));
		ob11PrivateMessageEvent.setSender(sender);
		OB11MessageEventHandler.onPrivateMessage(bot, ob11PrivateMessageEvent);
		return ob11PrivateMessageEvent;
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11BaseMessageEvent");
		stringBuffer.append("{");
		stringBuffer.append(",").append("time").append("=").append(getTime());
		stringBuffer.append(",").append("selfId").append("=").append(getSelfId());
		stringBuffer.append(",").append("userId").append("=").append(getUserId());
		stringBuffer.append(",").append("messageId").append("=").append(getMessageId());
		stringBuffer.append(",").append("rawMessage").append("=").append(getRawMessage());
		stringBuffer.append(",").append("messageArray").append("=").append(getMessageArray());
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
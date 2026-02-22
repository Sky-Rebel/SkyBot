package com.github.sky_rebel.skybot.event;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.message_sent.OB11GroupMessageSentEvent;
import com.github.sky_rebel.skybot.event.message_sent.OB11PrivateMessageSentEvent;
import com.github.sky_rebel.skybot.msg.OB11MessageSegment;
import com.github.sky_rebel.skybot.msg.element.OB11MsgElement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;

/**
 * Represents a base event for when a message is sent. This class extends OB11BaseEvent and provides
 * additional fields and methods specific to the handling of message sending events.
 * It supports both group and private message types, with factory methods to create the appropriate
 * event instance based on the incoming JSON data.
 *
 * @author Sky_Rebel
 * @lastUpdate  2026-02-23
 */
public class OB11BaseMessageSentEvent extends OB11BaseEvent
{
	/**
	 * Represents the ID of the target entity (e.g., user, group) to which the message was sent.
	 * This field is used to store the unique identifier of the recipient, allowing for tracking
	 * and further interaction with the specific target. The target ID is essential for identifying
	 * the context of the message and for any subsequent actions that may need to reference the
	 * original recipient.
	 */
	private long targetId;

	/**
	 * Represents the unique identifier of the user who sent the message or triggered the event.
	 * This field is used to identify the specific user within the context of the event, allowing
	 * for targeted responses or actions based on the user's identity. The userId is a crucial
	 * piece of information for tracking and managing interactions with individual users.
	 */
	private long userId;

	/**
	 * Represents the unique identifier for a sent message. This ID is used to uniquely identify
	 * the message within the OneBot v11 protocol, allowing for tracking and referencing of
	 * specific messages in various operations and interactions.
	 */
	private long messageId;

	/**
	 * Represents the raw, unprocessed message content as received from the OneBot v11 protocol.
	 * This field stores the original message string before any parsing or transformation is applied.
	 * It can be used to access the exact message text that was sent, which may include plain text,
	 * special characters, and potentially encoded or formatted content depending on the source.
	 */
	private String rawMessage;

	/**
	 * Represents an array of messages, typically used to store a collection of message objects.
	 * Each element in the array is expected to be a JSON object that represents a single message,
	 * which can include various properties such as the message content, sender information, and
	 * other metadata. This array is primarily used for handling and processing multiple messages
	 * within a single event, such as*/
	private JSONArray messageArray;

	/**
	 * Represents a list of {@link OB11MsgElement} objects that make up the message content.
	 * Each element in the list corresponds to a specific part of the message, such as text,
	 * images, or other media types. The elements are used to construct and parse the message
	 * in a structured format, allowing for complex message compositions.
	 *
	 * @see OB11MsgElement
	 */
	private List<OB11MsgElement> messageElementArray;

	public long getTargetId()
	{
		return targetId;
	}

	public void setTargetId(long targetId)
	{
		this.targetId = targetId;
	}

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
	 * Represents the type of message sent, distinguishing between group and private messages.
	 * This enum is used to categorize the context in which a message was sent, allowing for
	 * different handling or processing based on whether the message was sent to a group or
	 * a single user in a private conversation.
	 */
	public enum Type
	{
		GROUP,
		PRIVATE
	}

	/**
	 * Retrieves an instance of OB11BaseMessageSentEvent based on the provided JSON object and bot.
	 *
	 * @param bot  the Bot instance to associate with the event
	 * @param json the JSONObject containing the message details
	 * @return an OB11BaseMessageSentEvent instance corresponding to the type of message, or null if the message type is unknown
	 */
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

	/**
	 * Creates and initializes an OB11GroupMessageSentEvent from the given bot and JSON object.
	 *
	 * @param bot  the Bot instance to associate with the event
	 * @param json the JSONObject containing the message details
	 * @return an initialized OB11GroupMessageSentEvent instance
	 */
	private static OB11GroupMessageSentEvent createGroupMessageSentEvent(Bot bot, JSONObject json)
	{
		OB11GroupMessageSentEvent ob11GroupMessageSentEvent = new OB11GroupMessageSentEvent();
		ob11GroupMessageSentEvent.setBot(bot);
		ob11GroupMessageSentEvent.setTime(json.optLong("time"));
		ob11GroupMessageSentEvent.setSelfId(json.optLong("self_id"));
		ob11GroupMessageSentEvent.setUserId(json.optLong("user_id"));
		ob11GroupMessageSentEvent.setGroupId(json.optLong("group_id"));
		ob11GroupMessageSentEvent.setTargetId(json.optLong("taropt_id"));
		ob11GroupMessageSentEvent.setMessageId(json.optLong("message_id"));
		ob11GroupMessageSentEvent.setRawMessage(json.optString("raw_message"));
		ob11GroupMessageSentEvent.setMessageArray(json.optJSONArray("message"));
		ob11GroupMessageSentEvent.setMessageElementArray(OB11MessageSegment.getMessageElementArray(ob11GroupMessageSentEvent.getMessageArray()));
		if (!ob11GroupMessageSentEvent.getMessageElementArray().isEmpty())
		{
			ob11GroupMessageSentEvent.setAtList(OB11MessageSegment.getAtList(ob11GroupMessageSentEvent.getMessageElementArray()));
			if (!ob11GroupMessageSentEvent.getAtList().isEmpty())
			{
				ob11GroupMessageSentEvent.setHasAt(true);
			}
		}
		JSONObject senderJson = json.optJSONObject("sender");
		OB11GroupMessageSentEvent.Sender sender = new OB11GroupMessageSentEvent.Sender();
		sender.setCard(senderJson.optString("card"));
		sender.setRole(senderJson.optString("role"));
		sender.setUserId(senderJson.optLong("user_id"));
		sender.setNickname(senderJson.optString("nickname"));
		ob11GroupMessageSentEvent.setSender(sender);
		return ob11GroupMessageSentEvent;
	}

	/**
	 * Creates an instance of OB11PrivateMessageSentEvent based on the provided JSON object and bot.
	 *
	 * @param bot  the Bot instance to associate with the event
	 * @param json the JSONObject containing the message details including time, self_id, user_id, target_id, message_id, raw_message, message, and sender information
	 * @return an OB11PrivateMessageSentEvent instance populated with the data from the JSON object
	 */
	private static OB11PrivateMessageSentEvent createPrivateMessageSentEvent(Bot bot, JSONObject json)
	{
		OB11PrivateMessageSentEvent ob11PrivateMessageSentEvent = new OB11PrivateMessageSentEvent();
		ob11PrivateMessageSentEvent.setBot(bot);
		ob11PrivateMessageSentEvent.setTime(json.optLong("time"));
		ob11PrivateMessageSentEvent.setSelfId(json.optLong("self_id"));
		ob11PrivateMessageSentEvent.setUserId(json.optLong("user_id"));
		ob11PrivateMessageSentEvent.setTargetId(json.optLong("target_id"));
		ob11PrivateMessageSentEvent.setMessageId(json.optLong("message_id"));
		ob11PrivateMessageSentEvent.setRawMessage(json.optString("raw_message"));
		ob11PrivateMessageSentEvent.setMessageArray(json.optJSONArray("message"));
		JSONObject senderJson = json.optJSONObject("sender");
		OB11PrivateMessageSentEvent.Sender sender = new OB11PrivateMessageSentEvent.Sender();
		sender.card = senderJson.optString("card");
		sender.userId = senderJson.optLong("user_id");
		sender.nickname = senderJson.optString("nickname");
		ob11PrivateMessageSentEvent.setSender(sender);
		return ob11PrivateMessageSentEvent;
	}
}
package com.github.sky_rebel.skybot.event;

/**
 * Represents the base class for request events in the OneBot v11 protocol. This class extends
 * {@link OB11BaseEvent} and is designed to encapsulate common properties and methods specific to
 * request events, such as friend or group requests. It provides a foundation upon which more
 * specific request event classes can be built, each tailored to handle a particular kind of
 * request. The class includes fields for storing the user ID, a unique flag for the request,
 * and an optional comment.
 *
 * @author Sky_Rebel
 * @lastUpdate  2026-02-23
 */
public class OB11BaseRequestEvent extends OB11BaseEvent
{

	/**
	 * Represents the types of request events that can be processed within the OneBot v11 protocol.
	 * This enumeration is used to categorize and identify different kinds of request events, such as
	 * friend or group requests. Each constant in this enum corresponds to a specific type of request,
	 * allowing for more specific handling and processing by the bot.
	 */
	public enum Type
	{
		GROUP,
		FRIEND
	}

	/**
	 * Represents the unique identifier of the user associated with this request event.
	 * This field is used to store the ID of the user who initiated the request, such as
	 * a friend or group request. The user ID is essential for identifying and processing
	 * the request, as well as for any subsequent interactions with the user.
	 */
	private long userId;

	/**
	 * Represents a unique identifier used to track and manage the request. This flag is essential for
	 * identifying and processing specific requests, such as friend or group requests, within the OneBot v11 protocol.
	 * It allows the bot to reference and handle each request uniquely, ensuring that the appropriate actions can be taken
	 * based on the context and details of the request.
	 */
	private String flag;

	/**
	 * Represents an optional comment associated with the request event. This field can be used to store
	 * additional information or context about the request, such as a reason for the request or any
	 * relevant notes. The comment is not required but can provide valuable details for processing and
	 * handling the request.
	 */
	private String comment;

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11BaseRequestEvent");
		stringBuffer.append("{");
		stringBuffer.append("userId").append("=").append(getUserId());
		stringBuffer.append(",").append("flag").append("=").append(getFlag());
		stringBuffer.append(",").append("comment").append("=").append(getComment());
		stringBuffer.append(",").append("time").append("=").append(getTime());
		stringBuffer.append(",").append("selfId").append("=").append(getSelfId());
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
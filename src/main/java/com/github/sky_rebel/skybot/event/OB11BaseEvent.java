package com.github.sky_rebel.skybot.event;

import com.github.sky_rebel.skybot.Bot;

/**
 * Represents the base class for all events in the OneBot v11 protocol. This class is designed to
 * encapsulate common properties and methods that are shared across different types of events,
 * such as messages, notices, and requests. It provides a foundation upon which more specific event
 * classes can be built, each tailored to handle a particular kind of interaction or notification.
 * The class includes fields for storing the bot instance, the time of the event, and the ID of the
 * bot involved in the event. Additionally, it defines an enumeration of event types that categorizes
 * the possible kinds of events that can occur within the operational scope of the bot.
 *
 * @author Sky_Rebel
 * @lastUpdate 2026-02-23
 */
public class OB11BaseEvent
{
	/**
	 * Enumerates the types of events that can be processed within the OneBot v11 protocol.
	 * Each constant represents a category of event, which can be further specified by
	 * subclasses of OB11BaseEvent. The types include notices, requests, messages,
	 * meta-events, and message-sent events, each designed to handle different kinds of
	 * interactions and notifications within the bot's operational environment.
	 */
	public enum Type
	{
		NOTICE,

		REQUEST,

		MESSAGE,

		META_EVENT,

		MESSAGE_SENT;
	}

	/**
	 * Represents the bot instance associated with this event. This field is used to store a reference
	 * to the Bot object, which contains information about the bot's configuration, ID, and operational
	 * status. The bot instance can be used to interact with the OneBot v11 protocol, such as sending
	 * messages or performing actions.
	 */
	private Bot bot;

	/**
	 * Represents the timestamp of when the event occurred, in seconds since the epoch (00:00:00 UTC, January 1, 1970).
	 * This field is used to store the time at which the event was generated or received. It is useful for
	 * tracking the chronological order of events and for implementing time-based logic within the application.
	 */
	private long time;

	/**
	 * Represents the ID of the bot itself within the context of the event. This field is used to
	 * uniquely identify the bot that is associated with or has triggered this event. It is particularly
	 * useful for distinguishing between multiple bots in a scenario where more than one bot is operating,
	 * and for correctly attributing actions or messages to the specific bot.
	 */
	private long selfId;

	public Bot getBot()
	{
		return bot;
	}

	public void setBot(Bot bot)
	{
		this.bot = bot;
	}

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public long getSelfId()
	{
		return selfId;
	}

	public void setSelfId(long selfId)
	{
		this.selfId = selfId;
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11BaseEvent");
		stringBuffer.append("{");
		stringBuffer.append("bot").append("=").append(getBot());
		stringBuffer.append(",").append("time").append("=").append(getTime());
		stringBuffer.append(",").append("selfId").append("=").append(getSelfId());
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
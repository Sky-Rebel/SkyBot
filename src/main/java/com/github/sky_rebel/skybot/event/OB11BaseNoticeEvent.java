package com.github.sky_rebel.skybot.event;

/**
 * Represents the base class for notice events in the OneBot v11 protocol. This class extends
 * {@link OB11BaseEvent} and is designed to encapsulate common properties and methods that are
 * shared across different types of notice events. Notice events are typically used to notify
 * the bot about changes or occurrences within the chat platform, such as when a user adds a friend,
 * recalls a message, or when there are changes in group settings.
 * <p>
 * The class defines an enumeration of notice event types, which categorizes the possible kinds of
 * notice events that can occur. Each type corresponds to a specific kind of interaction or
 * notification, such as friend-related, group-related, or bot status changes.
 * <p>
 * Subclasses of this class should provide more specific implementations for each type of notice
 * event, adding additional fields and methods as needed to handle the particular details of the
 * event.
 *
 * @author Sky_Rebel
 * @lastUpdate  2026-02-23
 */
public class OB11BaseNoticeEvent extends OB11BaseEvent
{
	/**
	 * Enumerates the types of notice events that can be processed within the OneBot v11 protocol.
	 * Each constant represents a specific kind of notice event, which can be related to friend
	 * interactions, group changes, or bot status updates. These constants are used to categorize
	 * and identify different kinds of notice events, allowing for more specific handling and
	 * processing by the bot.
	 */
	public enum Type
	{
		FRIEND_ADD,

		FRIEND_RECALL,

		OFFLINE_FILE,

		CLIENT_STATUS,

		GROUP_ADMIN,

		GROUP_BAN,

		GROUP_CARD,

		GROUP_DECREASE,

		GROUP_INCREASE,

		GROUP_RECALL,

		GROUP_UPLOAD,

		ESSENCE,

		BOT_OFFLINE,

		NOTIFY
	}
}
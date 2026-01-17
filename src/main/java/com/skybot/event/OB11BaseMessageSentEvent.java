package com.skybot.event;

public class OB11BaseMessageSentEvent extends OB11BaseEvent
{
	public static final String POST_TYPE = OB11BaseEvent.EventType.MESSAGE_SENT.getValue();

	public static final String[] MESSAGE_TYPE = {"private", "group"};

	public long targetId;
}
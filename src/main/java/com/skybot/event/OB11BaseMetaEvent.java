package com.skybot.event;

public class OB11BaseMetaEvent extends OB11BaseEvent
{
	public static final String POST_TYPE = OB11BaseEvent.EventType.META.getValue();

	public String metaEventType;
}
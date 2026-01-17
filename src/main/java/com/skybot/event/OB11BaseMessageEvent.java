package com.skybot.event;

import org.json.JSONArray;

public class OB11BaseMessageEvent extends OB11BaseEvent
{
	public static final String POST_TYPE = OB11BaseEvent.EventType.MESSAGE.getValue();

	public long messageId;

	public long userId;

	public JSONArray message;

	public String rawMessage;
}

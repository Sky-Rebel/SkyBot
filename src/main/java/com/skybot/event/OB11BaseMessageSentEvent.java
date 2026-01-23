package com.skybot.event;

import org.json.JSONArray;

public class OB11BaseMessageSentEvent extends OB11BaseEvent
{
	public long time;

	public long targetId;

	public long messageId;

	public String rawMessage;

	public JSONArray messageArray;

	@Override
	public String toString()
	{
		return "OB11BaseMessageSentEvent{" +
			"targetId=" + targetId +
			", time=" + time +
			", selfId=" + selfId +
			'}';
	}
}
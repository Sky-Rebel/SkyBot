package com.github.sky_rebel.bot.event;

import org.json.JSONArray;

public class OB11BaseMessageEvent extends OB11BaseEvent
{
	public long messageId;

	public long userId;

	public JSONArray messageArray;

	public String rawMessage;

	@Override
	public String toString()
	{
		return "OB11BaseMessageEvent{" +
			"messageId=" + messageId +
			", userId=" + userId +
			", message=" + messageArray +
			", rawMessage='" + rawMessage + '\'' +
			", time=" + time +
			", selfId=" + selfId +
			'}';
	}

}

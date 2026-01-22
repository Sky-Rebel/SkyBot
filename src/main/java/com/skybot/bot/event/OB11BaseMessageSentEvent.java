package com.skybot.bot.event;

public class OB11BaseMessageSentEvent extends OB11BaseEvent
{
	public long targetId;

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
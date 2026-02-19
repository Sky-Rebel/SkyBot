package com.github.sky_rebel.skybot.event;

import com.github.sky_rebel.skybot.Bot;

public class OB11BaseEvent
{
	public Bot bot;

	public long time;

	public long selfId;

	public enum Type
	{
		NOTICE,

		REQUEST,

		MESSAGE,

		META_EVENT,

		MESSAGE_SENT;
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11BaseEvent");
		stringBuffer.append("{");
		stringBuffer.append("bot").append("=").append(bot);
		stringBuffer.append(",").append("time").append("=").append(time);
		stringBuffer.append(",").append("selfId").append("=").append(selfId);
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
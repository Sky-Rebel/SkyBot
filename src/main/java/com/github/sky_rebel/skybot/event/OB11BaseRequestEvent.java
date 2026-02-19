package com.github.sky_rebel.skybot.event;

public class OB11BaseRequestEvent extends OB11BaseEvent
{
	public long userId;

	public String flag;

	public String comment;

	public enum Type
	{
		GROUP,
		FRIEND
	}

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11BaseRequestEvent");
		stringBuffer.append("{");
		stringBuffer.append("userId").append("=").append(userId);
		stringBuffer.append(",").append("flag").append("=").append(flag);
		stringBuffer.append(",").append("comment").append("=").append(comment);
		stringBuffer.append(",").append("time").append("=").append(time);
		stringBuffer.append(",").append("selfId").append("=").append(selfId);
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
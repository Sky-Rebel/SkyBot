package com.github.sky_rebel.skybot.event;

public class OB11BaseMetaEvent extends OB11BaseEvent
{
	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11BaseMetaEvent");
		stringBuffer.append("{");
		stringBuffer.append("time").append("=").append(time);
		stringBuffer.append(",").append("selfId").append("=").append(selfId);
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
package com.github.sky_rebel.event;

public class OB11BaseRequestEvent extends OB11BaseEvent
{
	public long userId;

	public String comment;

	public String flag;

	@Override
	public String toString()
	{
		return "OB11BaseRequestEvent{" +
			"userId=" + userId +
			", comment='" + comment + '\'' +
			", flag='" + flag + '\'' +
			", time=" + time +
			", selfId=" + selfId +
			'}';
	}
}
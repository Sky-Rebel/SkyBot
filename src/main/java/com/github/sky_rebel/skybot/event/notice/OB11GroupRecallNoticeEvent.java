package com.github.sky_rebel.skybot.event.notice;

import com.github.sky_rebel.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupRecallNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public long groupId;

	public long messageId;

	public long operatorId;

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("OB11GroupRecallNoticeEvent");
		stringBuffer.append("{");
		stringBuffer.append("userId").append("=").append(userId);
		stringBuffer.append(",").append("groupId").append("=").append(groupId);
		stringBuffer.append(",").append("messageId").append("=").append(messageId);
		stringBuffer.append(",").append("operatorId").append("=").append(operatorId);
		stringBuffer.append(",").append("time").append("=").append(time);
		stringBuffer.append(",").append("selfId").append("=").append(selfId);
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
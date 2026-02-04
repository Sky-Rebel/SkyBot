package com.github.sky_rebel.bot.api.data.group;

import com.github.sky_rebel.bot.msg.OB11MessageSegment;
import com.github.sky_rebel.bot.msg.element.OB11MsgElement;
import org.json.JSONObject;

import java.util.List;
import java.util.StringJoiner;

public class OB11EssenceMsgInfo
{
	public int msgSeq;

	public int msgRandom;

	public long senderId;

	public String senderNick;

	public long operatorId;

	public String operatorNick;

	public long messageId;

	public long operatorTime;

	public List<OB11MsgElement> content;

	public static OB11EssenceMsgInfo getInstance(JSONObject data)
	{
		OB11EssenceMsgInfo ob11EssenceMsgInfo = new OB11EssenceMsgInfo();
		ob11EssenceMsgInfo.msgSeq = data.getInt("msg_seq");
		ob11EssenceMsgInfo.msgRandom = data.getInt("msg_random");
		ob11EssenceMsgInfo.senderId = data.getLong("sender_id");
		ob11EssenceMsgInfo.senderNick = data.getString("sender_nick");
		ob11EssenceMsgInfo.operatorId = data.getLong("operator_id");
		ob11EssenceMsgInfo.operatorNick = data.getString("operator_nick");
		ob11EssenceMsgInfo.messageId = data.getLong("message_id");
		ob11EssenceMsgInfo.operatorTime = data.getLong("operator_time");
		ob11EssenceMsgInfo.content = OB11MessageSegment.getMessageElementArray(data.getJSONArray("content"));
		return ob11EssenceMsgInfo;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11EssenceMsgInfo.class.getSimpleName() + "[", "]")
			.add("msgSeq=" + msgSeq)
			.add("msgRandom=" + msgRandom)
			.add("senderId=" + senderId)
			.add("senderNick='" + senderNick + "'")
			.add("operatorId=" + operatorId)
			.add("operatorNick='" + operatorNick + "'")
			.add("messageId=" + messageId)
			.add("operatorTime=" + operatorTime)
			.add("content=" + content)
			.toString();
	}
}
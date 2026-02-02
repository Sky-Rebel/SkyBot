package com.skybot.bot.msg.element;

import org.json.JSONObject;

public class OB11AtMsgElement extends OB11MsgElement
{
	public static final OB11MsgElement.ElementType ELEMENT_TYPE = OB11MsgElement.ElementType.AT;

	private String qq;

	public String getQQ()
	{
		return qq;
	}

	public void setQQ(long qq)
	{
		this.qq = String.valueOf(qq);
	}

	public void setQQ(String qq)
	{
		this.qq = qq;
	}

	@Override
	public String toString()
	{
		return "OB11ATElement{" +
			"qq='" + qq + '\'' +
			'}';
	}

	public ElementType getElementType()
	{
		return ELEMENT_TYPE;
	}

	@Override
	public JSONObject toJSONObject()
	{
		JSONObject rootObject = new JSONObject();
		rootObject.put("type", ELEMENT_TYPE.name().toLowerCase());
		JSONObject dataObject = new JSONObject();
		dataObject.put("qq", qq);
		rootObject.put("data", dataObject);
		return rootObject;
	}
}
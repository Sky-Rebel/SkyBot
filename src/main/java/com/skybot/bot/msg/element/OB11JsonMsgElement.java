package com.skybot.bot.msg.element;

import org.json.JSONObject;

public class OB11JsonMsgElement extends OB11MsgElement
{
	private static final OB11MsgElement.ElementType ELEMENT_TYPE = OB11MsgElement.ElementType.JSON;

	private JSONObject json;

	public JSONObject getJson()
	{
		return json;
	}

	public void setJson(JSONObject json)
	{
		this.json = json;
	}

	@Override
	public ElementType getElementType()
	{
		return ELEMENT_TYPE;
	}

	@Override
	public JSONObject toJSONObject()
	{
		JSONObject rootObject = new JSONObject();
		rootObject.put("type", ELEMENT_TYPE.name().toLowerCase());
		JSONObject dataObject = json;
		rootObject.put("data", dataObject);
		return rootObject;
	}
}
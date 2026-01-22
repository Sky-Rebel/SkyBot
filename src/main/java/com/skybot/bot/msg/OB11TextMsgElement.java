package com.skybot.bot.msg;

import org.json.JSONObject;

public class OB11TextMsgElement extends OB11MsgElement
{
	private static final OB11MsgElement.ElementType ELEMENT_TYPE = OB11MsgElement.ElementType.TEXT;

	private String text;

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public String toString()
	{
		return "OB11TextElement{" +
			"text='" + text + '\'' +
			'}';
	}

	@Override
	public String getElementName()
	{
		return ELEMENT_TYPE.name().toLowerCase();
	}

	@Override
	public JSONObject toJSONObject()
	{
		JSONObject rootObject = new JSONObject();
		rootObject.put("type", ELEMENT_TYPE.name().toLowerCase());
		JSONObject dataObject = new JSONObject();
		dataObject.put("text", text);
		rootObject.put("data", dataObject);
		return rootObject;
	}
}
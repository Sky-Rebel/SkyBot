package com.github.sky_rebel.skybot.msg.element;

import org.json.JSONObject;

import java.util.StringJoiner;

public class OB11ReplyMsgElement extends OB11MsgElement
{
	public static final OB11MsgElement.ElementType ELEMENT_TYPE = OB11MsgElement.ElementType.REPLY;

	private long id;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11ReplyMsgElement.class.getSimpleName() + "[", "]").add("id='" + id + "'")
		.toString();
	}

	public OB11MsgElement.ElementType getElementType()
	{
		return ELEMENT_TYPE;
	}

	@Override
	public JSONObject toJSONObject()
	{
		JSONObject rootObject = new JSONObject();
		rootObject.put("type", ELEMENT_TYPE.name()
		.toLowerCase());
		JSONObject dataObject = new JSONObject();
		dataObject.put("id", id);
		rootObject.put("data", dataObject);
		return rootObject;
	}

}
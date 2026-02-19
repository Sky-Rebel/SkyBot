package com.github.sky_rebel.skybot.msg.element;

import org.json.JSONObject;

public abstract class OB11MsgElement
{
	public enum ElementType
	{
		TEXT,
		FACE,
		IMAGE,
		RECORD,
		VIDEO,
		AT,
		RPS,
		DICE,
		SHAKE,
		POKE,
		SHARE,
		CONTACT,
		LOCATION,
		MUSIC,
		REPLY,
		FORWARD,
		NODE,
		JSON,
		MFACE,
		FILE,
		MARKDOWN,
		LIGHTAPP
	}
	public abstract ElementType getElementType();

	public abstract JSONObject toJSONObject();
}
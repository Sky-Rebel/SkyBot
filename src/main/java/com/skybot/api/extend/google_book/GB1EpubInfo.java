package com.skybot.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1EpubInfo
{
	public boolean isAvailable;

	public String acsTokenLink;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1EpubInfo.class.getSimpleName() + "[", "]")
			.add("isAvailable=" + isAvailable)
			.add("acsTokenLink='" + acsTokenLink + "'")
			.toString();
	}

	public static GB1EpubInfo getInstance(JSONObject data)
	{
		GB1EpubInfo gb1EpubInfo = new GB1EpubInfo();
		gb1EpubInfo.isAvailable = data.optBoolean("isAvailable");
		gb1EpubInfo.acsTokenLink = data.optString("acsTokenLink");
		return gb1EpubInfo;
	}
}

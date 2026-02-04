package com.github.sky_rebel.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1PdfInfo
{
	public boolean isAvailable;

	public String acsTokenLink;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1PdfInfo.class.getSimpleName() + "[", "]")
			.add("isAvailable=" + isAvailable)
			.add("acsTokenLink='" + acsTokenLink + "'")
			.toString();
	}

	public static GB1PdfInfo getInstance(JSONObject data)
	{
		GB1PdfInfo gb1PdfInfo = new GB1PdfInfo();
		gb1PdfInfo.isAvailable = data.optBoolean("isAvailable");
		gb1PdfInfo.acsTokenLink = data.optString("acsTokenLink");
		return gb1PdfInfo;
	}
}
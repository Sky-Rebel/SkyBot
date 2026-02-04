package com.github.sky_rebel.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1SearchInfo
{
	/**
	 * 图书文本摘要
	 */
	public String textSnippet;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1SearchInfo.class.getSimpleName() + "[", "]").add("textSnippet='" + textSnippet + "'").toString();
	}

	public static GB1SearchInfo getInstance(JSONObject data)
	{
		GB1SearchInfo gb1SearchInfo = new GB1SearchInfo();
		gb1SearchInfo.textSnippet = data.optString("textSnippet");
		return gb1SearchInfo;
	}
}

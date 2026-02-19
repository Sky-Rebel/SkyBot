package com.github.sky_rebel.skybot.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1ReadingModeInfo
{
	/**
	 * 是否支持文本阅读
	 */
	public boolean text;

	/**
	 * 是否支持图片阅读
	 */
	public boolean image;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1ReadingModeInfo.class.getSimpleName() + "[", "]")
			.add("text=" + text)
			.add("image=" + image)
			.toString();
	}

	public static GB1ReadingModeInfo getInstance(JSONObject data)
	{
		GB1ReadingModeInfo gb1ReadingModeInfo = new GB1ReadingModeInfo();
		gb1ReadingModeInfo.text = data.optBoolean("text");
		gb1ReadingModeInfo.image = data.getBoolean("image");
		return gb1ReadingModeInfo;
	}
}

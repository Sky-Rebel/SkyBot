package com.skybot.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1ImageLinkInfo
{
	/**
	 * 图书封面常规尺寸的缩略图链接
	 */
	public String thumbnail;

	/**
	 * 图书封面较小尺寸的缩略图链接
	 */
	public String smallThumbnail;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1ImageLinkInfo.class.getSimpleName() + "[", "]")
			.add("thumbnail='" + thumbnail + "'")
			.add("smallThumbnail='" + smallThumbnail + "'")
			.toString();
	}

	public static GB1ImageLinkInfo getInstance(JSONObject data)
	{
		GB1ImageLinkInfo gb1ImageLinkInfo = new GB1ImageLinkInfo();
		gb1ImageLinkInfo.thumbnail = data.optString("thumbnail");
		gb1ImageLinkInfo.smallThumbnail = data.optString("smallThumbnail");
		return gb1ImageLinkInfo;
	}
}
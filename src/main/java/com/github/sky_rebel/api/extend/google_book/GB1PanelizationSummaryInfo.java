package com.github.sky_rebel.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1PanelizationSummaryInfo
{
	/**
	 * 图书是否包含EPUB格式的Bubbles内容
	 */
	public boolean containsEpubBubbles;

	/**
	 * 图书是否包含Image格式的Bubbles内容
	 */
	public boolean containsImageBubbles;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1PanelizationSummaryInfo.class.getSimpleName() + "[", "]")
			.add("containsEpubBubbles=" + containsEpubBubbles)
			.add("containsImageBubbles=" + containsImageBubbles)
			.toString();
	}

	public static GB1PanelizationSummaryInfo getInstance(JSONObject data)
	{
		GB1PanelizationSummaryInfo gb1PanelizationSummaryInfo = new GB1PanelizationSummaryInfo();
		gb1PanelizationSummaryInfo.containsEpubBubbles = data.optBoolean("containsEpubBubbles");
		gb1PanelizationSummaryInfo.containsImageBubbles = data.optBoolean("containsImageBubbles");
		return gb1PanelizationSummaryInfo;
	}
}

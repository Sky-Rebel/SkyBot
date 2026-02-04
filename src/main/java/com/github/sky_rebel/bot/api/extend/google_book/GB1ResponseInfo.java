package com.github.sky_rebel.bot.api.extend.google_book;

import java.util.List;
import java.util.StringJoiner;

public class GB1ResponseInfo
{
	public String kind;

	public long totalItems;

	public List<GB1ItemInfo> gb1ItemInfoList;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1ResponseInfo.class.getSimpleName() + "[", "]")
			.add("kind='" + kind + "'")
			.add("totalItems=" + totalItems)
			.add("itemInfoList=" + gb1ItemInfoList)
			.toString();
	}
}

package com.skybot.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1SaleInfo
{
	/**
	 * 图书销售地区
	 */
	public String country;

	/**
	 * 图书销售状态
	 */
	public String saleability;

	/**
	 * 图书是否为电子版
	 */
	public boolean isEbook;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1SaleInfo.class.getSimpleName() + "[", "]")
			.add("country='" + country + "'")
			.add("saleability='" + saleability + "'")
			.add("isEbook=" + isEbook)
			.toString();
	}

	public static GB1SaleInfo getInstance(JSONObject data)
	{
		GB1SaleInfo gb1SaleInfo = new GB1SaleInfo();
		gb1SaleInfo.country = data.optString("country");
		gb1SaleInfo.saleability = data.optString("saleability");
		gb1SaleInfo.isEbook = data.optBoolean("isEbook");
		return gb1SaleInfo;
	}
}

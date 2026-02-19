package com.github.sky_rebel.skybot.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1ItemInfo
{
	/**
	 * 图书唯一标志
	 */
	public String id;

	/**
	 * 资源类型标识
	 */
	public String kind;


	/**
	 * 资源版本标志
	 */
	public String etag;

	/**
	 * 资源专属链接
	 */
	public String selfLink;

	/**
	 * 图书核心信息
	 */
	public GB1VolumeInfo gb1VolumeInfo;

	/**
	 * 图书售卖信息
	 */
	public GB1SaleInfo gb1SaleInfo;

	/**
	 * 图书权限信息
	 */
	public GB1AccessInfo gb1AccessInfo;

	/**
	 * 图书搜索信息
	 */
	public GB1SearchInfo gb1SearchInfo;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1ItemInfo.class.getSimpleName() + "[", "]")
		.add("id='" + id + "'")
		.add("kind='" + kind + "'")
		.add("etag='" + etag + "'")
		.add("selfLink='" + selfLink + "'")
		.add("gb1VolumeInfo=" + gb1VolumeInfo)
		.add("gb1SaleInfo=" + gb1SaleInfo)
		.add("gb1AccessInfo=" + gb1AccessInfo)
		.add("gb1SearchInfo=" + gb1SearchInfo)
		.toString();
	}

	public static GB1ItemInfo getInstance(JSONObject data)
	{
		GB1ItemInfo gb1ItemInfo = new GB1ItemInfo();
		gb1ItemInfo.id = data.optString("id");
		gb1ItemInfo.kind = data.optString("kind");
		gb1ItemInfo.etag = data.optString("etag");
		gb1ItemInfo.selfLink = data.optString("selfLink");
		JSONObject volumeInfo = data.optJSONObject("volumeInfo");
		if (volumeInfo != null)
		{
			gb1ItemInfo.gb1VolumeInfo = GB1VolumeInfo.getInstance(volumeInfo);
		}
		JSONObject saleInfo = data.optJSONObject("saleInfo");
		if (saleInfo != null)
		{
			gb1ItemInfo.gb1SaleInfo = GB1SaleInfo.getInstance(saleInfo);
		}
		JSONObject accessInfo = data.optJSONObject("accessInfo");
		if (accessInfo != null)
		{
			gb1ItemInfo.gb1AccessInfo = GB1AccessInfo.getInstance(accessInfo);
		}
		JSONObject searchInfo = data.optJSONObject("searchInfo");
		if (searchInfo != null)
		{
			gb1ItemInfo.gb1SearchInfo = GB1SearchInfo.getInstance(searchInfo);
		}
		return gb1ItemInfo;
	}
}
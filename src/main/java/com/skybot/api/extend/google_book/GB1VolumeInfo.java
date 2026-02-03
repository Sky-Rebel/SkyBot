package com.skybot.api.extend.google_book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class GB1VolumeInfo
{
	/**
	 * 图书名字
	 */
	public String title;

	/**
	 * 图书作者
	 */
	public List<String> authors;

	/**
	 * 图书出版社
	 */
	public String publisher;

	/**
	 * 图书出版时间
	 */
	public String publishedDate;

	/**
	 * 图书简介/内容摘要
	 */
	public String description;

	/**
	 * 图书行业标准标识符
	 */
	Map<String, String> industryIdentifiers;

	/**
	 * 图书阅读模式支持信息
	 */
	public GB1ReadingModeInfo gb1ReadingModeInfo;

	/**
	 * 图书页数
	 */
	public int pageCount;

	/**
	 * 图书出版物类型
	 */
	public String printType;

	/**
	 * 图书分类标签
	 */
	public List<String> categories;

	/**
	 * 图书内容成熟度评级
	 */
	public String maturityRating;

	/**
	 * 图书是否允许匿名摘录
	 */
	public boolean allowAnonLogging;

	/**
	 * 图书内容版本号
	 */
	public String contentVersion;

	/**
	 * 图书面板化摘要信息
	 */
	public GB1PanelizationSummaryInfo panelizationSummaryInfo;

	/**
	 * 图书封面图片链接信息
	 */
	public GB1ImageLinkInfo imageLinkInfo;

	/**
	 * 图书语言
	 */
	public String language;

	/**
	 * 图书预览页面链接
	 */
	public String previewLink;

	/**
	 * 图书详情页面链接
	 */
	public String infoLink;

	/**
	 * 图书标准链接
	 */
	public String canonicalVolumeLink;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1VolumeInfo.class.getSimpleName() + "[", "]").add("title='" + title + "'")
		.add("authors=" + authors)
		.add("publisher='" + publisher + "'")
		.add("publishedDate='" + publishedDate + "'")
		.add("description='" + description + "'")
		.add("industryIdentifiers=" + industryIdentifiers)
		.add("gb1ReadingModeInfo=" + gb1ReadingModeInfo)
		.add("pageCount=" + pageCount)
		.add("printType='" + printType + "'")
		.add("categories=" + categories)
		.add("maturityRating='" + maturityRating + "'")
		.add("allowAnonLogging=" + allowAnonLogging)
		.add("contentVersion='" + contentVersion + "'")
		.add("panelizationSummaryInfo=" + panelizationSummaryInfo)
		.add("imageLinkInfo=" + imageLinkInfo)
		.add("language='" + language + "'")
		.add("previewLink='" + previewLink + "'")
		.add("infoLink='" + infoLink + "'")
		.add("canonicalVolumeLink='" + canonicalVolumeLink + "'")
		.toString();
	}

	public static GB1VolumeInfo getInstance(JSONObject data)
	{
		GB1VolumeInfo gb1VolumeInfo = new GB1VolumeInfo();
		gb1VolumeInfo.title = data.optString("title");
		JSONArray authorArray = data.optJSONArray("authors");
		if (authorArray != null)
		{
			if (!authorArray.isEmpty())
			{
				List<String> authorList = new ArrayList<>();
				authorArray.forEach(obj ->
				{
					if (obj instanceof String author)
					{
						authorList.add(author);
					}
				});
				gb1VolumeInfo.authors = authorList;
			}
		}
		gb1VolumeInfo.publisher = data.optString("publisher");
		gb1VolumeInfo.publishedDate = data.optString("publishedDate");
		gb1VolumeInfo.description = data.optString("description");
		JSONArray industryIdentifierArray = data.optJSONArray("industryIdentifiers");
		Map<String, String> industryIdentifierMap = new HashMap<>();
		if (industryIdentifierArray != null)
		{
			if (!industryIdentifierArray.isEmpty())
			{
				industryIdentifierArray.forEach(obj ->
				{
					if (obj instanceof JSONObject industryIdentifier)
					{
						String type = industryIdentifier.optString("type");
						String identifier = industryIdentifier.optString("identifier");
						industryIdentifierMap.put(type, identifier);
					}
				});
			}
			gb1VolumeInfo.industryIdentifiers = industryIdentifierMap;
		}
		JSONObject readingModeObject = data.optJSONObject("readingModes");
		gb1VolumeInfo.gb1ReadingModeInfo = GB1ReadingModeInfo.getInstance(readingModeObject);
		gb1VolumeInfo.pageCount = data.optInt("pageCount");;
		gb1VolumeInfo.printType = data.optString("printType");
		JSONArray categoryArray = data.optJSONArray("categories");
		if (categoryArray != null)
		{
			if (!categoryArray.isEmpty())
			{
				List<String> categoryList = new ArrayList<>();
				categoryArray.forEach(obj ->
				{
					if (obj instanceof String category)
					{
						categoryList.add(category);
					}
				});
				gb1VolumeInfo.categories = categoryList;
			}
		}
		gb1VolumeInfo.maturityRating = data.optString("maturityRating");
		gb1VolumeInfo.allowAnonLogging = data.optBoolean("allowAnonLogging");
		gb1VolumeInfo.contentVersion = data.optString("contentVersion");
		JSONObject panelizationSummaryObject = data.optJSONObject("panelizationSummary");
		if (panelizationSummaryObject != null)
		{
			gb1VolumeInfo.panelizationSummaryInfo = GB1PanelizationSummaryInfo.getInstance(panelizationSummaryObject);
		}
		JSONObject imageLinkObject = data.optJSONObject("imageLinks");
		if (imageLinkObject != null)
		{
			gb1VolumeInfo.imageLinkInfo = GB1ImageLinkInfo.getInstance(imageLinkObject);
		}
		gb1VolumeInfo.language = data.optString("language");
		gb1VolumeInfo.previewLink = data.optString("previewLink");
		gb1VolumeInfo.infoLink = data.optString("infoLink");
		gb1VolumeInfo.canonicalVolumeLink = data.optString("canonicalVolumeLink");
		return gb1VolumeInfo;
	}
}
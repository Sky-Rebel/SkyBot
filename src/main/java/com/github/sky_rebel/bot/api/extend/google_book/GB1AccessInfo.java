package com.github.sky_rebel.bot.api.extend.google_book;

import org.json.JSONObject;

import java.util.StringJoiner;

public class GB1AccessInfo
{
	/**
	 * 图书权限适用地区
	 */
	public String country;

	/**
	 * 图书试看范围
	 */
	public String viewability;

	/**
	 * 图书是否可嵌入到其他网页
	 */
	public boolean embeddable;

	/**
	 * 图书是否为公有领域作品
	 */
	public boolean publicDomain;

	/**
	 * 图书是否具备文本转语音权限
	 */
	public String textToSpeechPermission;

	/**
	 * 图书EPUB格式电子书权限
	 */
	public GB1EpubInfo epubInfo;

	/**
	 * 图书PDF格式电子书权限
	 */
	public GB1PdfInfo pdfInfo;

	/**
	 * 图书网页阅读器链接
	 */
	public String webReaderLink;

	/**
	 * 图书访问状态
	 */
	public String accessViewStatus;

	/**
	 * 图书是否允许分享引用
	 */
	public boolean quoteSharingAllowed;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", GB1AccessInfo.class.getSimpleName() + "[", "]")
			.add("country='" + country + "'")
			.add("viewability='" + viewability + "'")
			.add("embeddable=" + embeddable)
			.add("publicDomain=" + publicDomain)
			.add("textToSpeechPermission='" + textToSpeechPermission + "'")
			.add("epubInfo=" + epubInfo)
			.add("pdfInfo=" + pdfInfo)
			.add("webReaderLink='" + webReaderLink + "'")
			.add("accessViewStatus='" + accessViewStatus + "'")
			.add("quoteSharingAllowed=" + quoteSharingAllowed)
			.toString();
	}

	public static GB1AccessInfo getInstance(JSONObject data)
	{
		GB1AccessInfo gb1AccessInfo = new GB1AccessInfo();
		gb1AccessInfo.country = data.optString("country");
		gb1AccessInfo.viewability = data.optString("viewability");
		gb1AccessInfo.embeddable = data.optBoolean("embeddable");
		gb1AccessInfo.publicDomain = data.optBoolean("publicDomain");
		gb1AccessInfo.textToSpeechPermission = data.optString("textToSpeechPermission");
		gb1AccessInfo.epubInfo = GB1EpubInfo.getInstance(data.optJSONObject("epub"));
		gb1AccessInfo.pdfInfo = GB1PdfInfo.getInstance(data.optJSONObject("pdf"));
		gb1AccessInfo.webReaderLink = data.optString("webReaderLink");
		gb1AccessInfo.accessViewStatus = data.optString("accessViewStatus");
		gb1AccessInfo.quoteSharingAllowed = data.optBoolean("quoteSharingAllowed");
		return gb1AccessInfo;
	}
}

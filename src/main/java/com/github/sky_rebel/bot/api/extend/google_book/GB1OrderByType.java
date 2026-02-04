package com.github.sky_rebel.bot.api.extend.google_book;

public enum GB1OrderByType
{
	/**
	 * Returns results in order of most recently to least recently published.
	 */
	newest("newest"),

	/**
	 * (DEFAULT)
	 * Returns results in order of the relevance of search terms.
	 */
	relevance("relevance");

	private final String value;

	GB1OrderByType(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
}

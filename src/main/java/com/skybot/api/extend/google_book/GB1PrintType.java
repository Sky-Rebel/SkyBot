package com.skybot.api.extend.google_book;

public enum GB1PrintType
{
	/**
	 * (DEFAULT)
	 * Does not restrict by print type.
	 */
	ALL("all"),

	/**
	 * Returns only results that are books.
	 */
	BOOKS("books"),

	/**
	 * Returns results that are magazines.
	 */
	MAGAZINES("magazines");

	private final String value;

	GB1PrintType(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
}

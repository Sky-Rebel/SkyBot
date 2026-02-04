package com.github.sky_rebel.api.extend.google_book;

public enum GB1FilterType
{
	/**
	 * Only returns results where all of the text is viewable.
	 */
	FULL("full"),

	/**
	 * Only returns results that are Google eBooks, paid or free.
	 * Examples of non-eBooks would be publisher content that is available in limited preview and not for sale, or magazines.
	 */
	EBOOKS("ebooks"),

	/**
	 * Returns results where at least parts of the text are previewable.
	 */
	PARTIAL("partial"),

	/**
	 * Only returns results that are Google eBooks with a price.
	 */
	PAID_EBOOKS("paid-ebooks"),

	/**
	 * Only returns results that are free Google eBooks.
	 */
	FREE_EBOOKS("free-ebooks");

	private final String value;

	GB1FilterType(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return this.value;
	}
}

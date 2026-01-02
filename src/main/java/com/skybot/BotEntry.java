package com.skybot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BotEntry
{
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");

	public static final String FORMAT_TIME = SIMPLE_DATE_FORMAT.format(new Date());

	public static final Logger LOGGER = LoggerFactory.getLogger(BotEntry.class);

	public static void main(String[] args)
	{
		LOGGER.debug("SkyBot Started");
		NapcatInstall.checkInstall();
	}
}
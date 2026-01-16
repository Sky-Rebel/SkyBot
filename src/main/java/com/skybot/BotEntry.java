package com.skybot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotEntry
{
	public static final Logger LOGGER = LoggerFactory.getLogger(BotEntry.class);

	public static void main(String[] args)
	{
		BotConfig botConfig = new BotConfig();
		botConfig.botId = 1234567;
		Bot bot = new Bot(botConfig);
		bot.start();
	}
}
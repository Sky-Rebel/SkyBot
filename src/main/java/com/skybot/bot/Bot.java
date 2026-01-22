package com.skybot.bot;

import com.skybot.bot.event.handling.listener.OB11EventListener;
import com.skybot.bot.util.CMDExecutor;
import com.skybot.bot.util.NapcatInstall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot
{
	private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

	public static final String NAPCAT_WORK_DIR = "napcat";

	public static final String LAUNCHER_USER_BAT = "launcher-user.bat";

	public static BotConfig config;

	public static boolean isStart;

	public Bot()
	{
		BotConfig botConfig = BotConfig.getBotConfig();
		this(botConfig);
	}

	public Bot(BotConfig botConfig)
	{
		NapcatInstall.checkInstall();
		BotConfig.saveConfig(botConfig);
		config = botConfig;
	}

	public void start()
	{
		OB11EventListener OB11EventListener = new OB11EventListener();
		OB11EventListener.start();
		if (config.botId == BotConfig.DEF_BOT_ID)
		{
			LOGGER.warn("BotId未配置，无法启动Bot!");
			return;
		}
		System.out.println(config.botId);
		CMDExecutor.startBat(LAUNCHER_USER_BAT, true, NAPCAT_WORK_DIR, LAUNCHER_USER_BAT, String.valueOf(config.botId));
		isStart = true;
	}
}
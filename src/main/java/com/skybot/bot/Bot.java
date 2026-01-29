package com.skybot.bot;

import com.skybot.event.handling.listener.OB11EventListener;
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
		if (!NapcatInstall.checkAndInstallNapcat()) return;
		if (botConfig.skybotConfig.botId == -1)
		{
			LOGGER.error("BotId未配置，无法获取配置！");
			return;
		}
		BotConfig.saveBotConfig(botConfig);
		config = botConfig;
	}

	public void start()
	{
		if (config == null)
		{
			LOGGER.error("Bot配置为空，无法启动Bot！");
			return;
		}
		CMDExecutor.startBat(LAUNCHER_USER_BAT, true, NAPCAT_WORK_DIR, LAUNCHER_USER_BAT, String.valueOf(config.skybotConfig.botId));
		new OB11EventListener().start();
		isStart = true;
	}
}
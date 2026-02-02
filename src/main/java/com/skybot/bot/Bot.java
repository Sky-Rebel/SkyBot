package com.skybot.bot;

import com.skybot.event.handling.listener.OB11EventListener;
import com.skybot.bot.util.CMDExecutor;
import com.skybot.bot.util.NapcatInstall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public class Bot
{
	private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

	public static final String NAPCAT_WORK_DIR = "napcat";

	public static final String LAUNCHER_USER_BAT = "launcher-user.bat";

	public static BotConfig config;

	public static boolean isStart;

	private static final Path NAPCAT_PATH = Path.of("napcat");

	public Bot()
	{
		BotConfig botConfig = BotConfig.getBotConfig();
		this(botConfig);
	}

	public Bot(long botId)
	{
		BotConfig botConfig = new BotConfig();
		botConfig.skybotConfig.botId = botId;
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
		CMDExecutor.startBatProcess("napcat", LAUNCHER_USER_BAT, String.valueOf(config.skybotConfig.botId));
		// CMDExecutor.startBat(LAUNCHER_USER_BAT, true, NAPCAT_WORK_DIR, LAUNCHER_USER_BAT, String.valueOf(config.skybotConfig.botId);
		Runtime.getRuntime().addShutdownHook(new Thread(() ->
		{
			CMDExecutor.shutdownExecutor();
			CMDExecutor.forceCleanInvalidProcess("napcat", LAUNCHER_USER_BAT, String.valueOf(config.skybotConfig.botId));
		}));
		new OB11EventListener().start();
		isStart = true;
	}
}
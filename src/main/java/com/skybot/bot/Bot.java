package com.skybot.bot;

import com.skybot.api.OB11MessageApiService;
import com.skybot.event.handling.listener.OB11EventListener;
import com.skybot.bot.util.CMDExecutor;
import com.skybot.bot.util.NapcatInstall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Bot
{
	private BotConfig botConfig;

	private boolean isStart;

	private long botId;

	private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

	public static final String LAUNCHER_USER_BAT = "launcher-user.bat";

	private static final Map<Long, Bot> botMap = new HashMap<>();

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
		if (botMap.containsKey(botConfig.skybotConfig.botId))
		{
			LOGGER.error("Bot({})既已启动，请勿重复启动！", botConfig.skybotConfig.botId);
		}
		BotConfig.saveBotConfig(botConfig);
		this.botConfig = botConfig;
		botMap.put(botConfig.skybotConfig.botId, this);
	}

	public void start()
	{
		if (botConfig == null)
		{
			LOGGER.error("Bot配置为空，无法启动Bot！");
			return;
		}
		CMDExecutor.startBatProcess("napcat", LAUNCHER_USER_BAT, String.valueOf(botConfig.skybotConfig.botId));
		// CMDExecutor.startBat(LAUNCHER_USER_BAT, true, NAPCAT_WORK_DIR, LAUNCHER_USER_BAT, String.valueOf(config.skybotConfig.botId);
		Runtime.getRuntime().addShutdownHook(new Thread(() ->
		{
			CMDExecutor.shutdownExecutor();
			CMDExecutor.forceCleanInvalidProcess("napcat", LAUNCHER_USER_BAT, String.valueOf(botConfig.skybotConfig.botId));
		}));
		new OB11EventListener(this).start();
		isStart = true;
	}

	public static Bot getBot(long botId)
	{
		if (botMap.containsKey(botId))
		{
			Bot bot = botMap.get(botId);
			return bot;
		}
		return null;
	}

	public Object getApiService(Class<?> apiServiceClass)
	{
		if (apiServiceClass == OB11MessageApiService.class)
		{
			return new OB11MessageApiService(this);
		}
		return null;
	}

	public BotConfig getBotConfig()
	{
		return botConfig;
	}

	public boolean isStart()
	{
		return isStart;
	}

	public long getBotId()
	{
		return botId;
	}
}
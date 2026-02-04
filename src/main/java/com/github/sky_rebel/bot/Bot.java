package com.github.sky_rebel.bot;

import com.github.sky_rebel.bot.api.OB11AccountApiService;
import com.github.sky_rebel.bot.api.OB11GroupApiService;
import com.github.sky_rebel.bot.api.OB11MessageApiService;
import com.github.sky_rebel.bot.event.handling.listener.OB11EventListener;
import com.github.sky_rebel.bot.util.CMDExecutor;
import com.github.sky_rebel.bot.util.NapcatInstall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Bot
{
	private long botId;

	private boolean isStart;

	private BotConfig botConfig;

	private static boolean isUseDefConfig = false;

	private static final Map<Long, Bot> botMap = new HashMap<>();

	public static final String LAUNCHER_USER_BAT = "launcher-user.bat";

	private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

	private Bot() {}

	public static Bot createBot(long botId)
	{
		if (isUseDefConfig)
		{
			LOGGER.error("默认配置既已使用，请通过BotConfig创建Bot实例！");
			return null;
		}
		BotConfig botConfig = new BotConfig();
		botConfig.skybotConfig.botId = botId;
		return createBot(botConfig);
	}

	public static Bot createBot(BotConfig botConfig)
	{
		if (!NapcatInstall.checkAndInstallNapcat())
		{
			LOGGER.error("Napcat未下载，无法创建实例！");
			return null;
		}
		if (botConfig.skybotConfig.botId == -1)
		{
			LOGGER.error("BotId未配置，无法获取配置！");
			return null;
		}
		if (botMap.containsKey(botConfig.skybotConfig.botId))
		{
			LOGGER.error("Bot实例({})既已创建，请勿重复创建！", botConfig.skybotConfig.botId);
			return null;
		}
		BotConfig.saveBotConfig(botConfig);
		Bot bot = new Bot();
		bot.botConfig = botConfig;
		bot.botId = botConfig.skybotConfig.botId;
		botMap.put(botConfig.skybotConfig.botId, bot);
		return bot;
	}

	public void start()
	{
		if (botConfig == null)
		{
			LOGGER.error("Bot配置为空，无法启动Bot！");
			return;
		}
		CMDExecutor.startBatProcess("napcat", LAUNCHER_USER_BAT, String.valueOf(botConfig.skybotConfig.botId));
		Runtime.getRuntime().addShutdownHook(new Thread(() ->
		{
			CMDExecutor.shutdownExecutor();
			CMDExecutor.forceCleanInvalidProcess("napcat", LAUNCHER_USER_BAT, String.valueOf(botConfig.skybotConfig.botId));
		}));
		new OB11EventListener(this).start();
		isStart = true;
	}

	public long getBotId()
	{
		return botId;
	}

	public boolean isStart()
	{
		return isStart;
	}

	public BotConfig getBotConfig()
	{
		return botConfig;
	}

	public static Bot getBot(long botId)
	{
		if (botMap.containsKey(botId))
		{
			return botMap.get(botId);
		}
		return null;
	}

	public OB11GroupApiService getOB11GroupApiService()
	{
		return (OB11GroupApiService) getApiService(OB11GroupApiService.class);
	}

	public OB11MessageApiService getOB11MessageApiService()
	{
		return (OB11MessageApiService) getApiService(OB11MessageApiService.class);
	}

	public OB11AccountApiService getOB11AccountApiService()
	{
		return (OB11AccountApiService) getApiService(OB11AccountApiService.class);
	}

	public Object getApiService(Class<?> apiServiceClass)
	{
		if (apiServiceClass == OB11GroupApiService.class)
		{
			return new OB11GroupApiService(this);
		}
		else if (apiServiceClass == OB11MessageApiService.class)
		{
			return new OB11MessageApiService(this);
		}
		else if (apiServiceClass == OB11AccountApiService.class)
		{
			return new OB11AccountApiService(this);
		}
		return null;
	}
}
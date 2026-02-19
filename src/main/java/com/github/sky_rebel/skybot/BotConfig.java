package com.github.sky_rebel.skybot;

import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

public class BotConfig
{
	private static final Logger LOGGER = SkybotLogger.getLogger(BotConfig.class);

	public SkybotConfig skybotConfig;

	public NapcatConfig napcatConfig;

	public BotConfig()
	{
		skybotConfig = new SkybotConfig();
		napcatConfig = new NapcatConfig();
	}

	public static class SkybotConfig
	{
		public long botId = -1;

		public long master = -1;

		public long mainGroup = -1;
	}

	public static class NapcatConfig
	{
		public NapcatConfig()
		{
			wsClientConfig = new WsClientConfig();
			httpServerConfig = new HttpServerConfig();
		}

		public WsClientConfig wsClientConfig;

		public HttpServerConfig httpServerConfig;

		public static class WsClientConfig
		{
			public String token = "";

			public boolean debug = true;

			public boolean enable = true;

			public String name = "WSClient";

			public int heartInterval = 30000;

			public int reconnectInterval = 5000;

			public boolean reportSelfMessage = true;

			public String url = "ws://127.0.0.1:5210";

			public String messagePostFormat = "array";

			public JSONObject toJSONObject()
			{
				return new JSONObject()
					.put("token", token)
					.put("debug", debug)
					.put("enable", enable)
					.put("name", name)
					.put("heartInterval", heartInterval)
					.put("reconnectInterval", reconnectInterval)
					.put("reportSelfMessage", reportSelfMessage)
					.put("url", url)
					.put("messagePostFormat", messagePostFormat);
			}

			@Override
			public String toString()
			{
				return new StringJoiner(", ", WsClientConfig.class.getSimpleName() + "[", "]")
					.add("token='" + token + "'")
					.add("debug=" + debug)
					.add("enable=" + enable)
					.add("name='" + name + "'")
					.add("heartInterval=" + heartInterval)
					.add("reconnectInterval=" + reconnectInterval)
					.add("reportSelfMessage=" + reportSelfMessage)
					.add("url='" + url + "'")
					.add("messagePostFormat='" + messagePostFormat + "'")
					.toString();
			}
		}

		public static class HttpServerConfig
		{
			public int port = 5200;

			public String token = "";

			public boolean debug = false;

			public boolean enable = true;

			public String host = "127.0.0.1";

			public String name = "HttpServer";

			public boolean enableCors = false;

			public boolean enableWebsocket = false;

			public String messagePostFormat = "array";

			public JSONObject toJSONObject()
			{
				return new JSONObject()
					.put("port", port)
					.put("token", token)
					.put("debug", debug)
					.put("enable", enable)
					.put("host", host)
					.put("name", name)
					.put("enableCors", enableCors)
					.put("enableWebsocket", enableWebsocket)
					.put("messagePostFormat", messagePostFormat);
			}

			@Override
			public String toString()
			{
				return new StringJoiner(", ", HttpServerConfig.class.getSimpleName() + "[", "]")
					.add("port=" + port)
					.add("token='" + token + "'")
					.add("debug=" + debug)
					.add("enable=" + enable)
					.add("host='" + host + "'")
					.add("name='" + name + "'")
					.add("enableCors=" + enableCors)
					.add("enableWebsocket=" + enableWebsocket)
					.add("messagePostFormat='" + messagePostFormat + "'")
					.toString();
			}
		}
	}

	public static BotConfig getBotConfig(Bot bot)
	{
		if (bot.getBotId() == -1) LOGGER.warn("BotId未配置，将返回默认配置！");
		SkybotConfig skybotConfig = getSkybotConfig(bot.getBotId());
		NapcatConfig napcatConfig = getNapcatConfig(skybotConfig.botId);
		BotConfig botConfig = new BotConfig();
		botConfig.skybotConfig = skybotConfig;
		botConfig.napcatConfig = napcatConfig;
		return botConfig;
	}

	public static SkybotConfig getSkybotConfig(long botId)
	{
		try
		{
			Path skybotConfigPath = Path.of("data", "bot_config_" + botId + ".json");
			if (!Files.exists(skybotConfigPath)) return new SkybotConfig();
			String fileContent = Files.readString(skybotConfigPath);
			JSONObject skybotConfigJson = new JSONObject(fileContent);
			SkybotConfig skybotConfig = new SkybotConfig();
			skybotConfig.botId = skybotConfigJson.getLong("bot_id");
			skybotConfig.master = skybotConfigJson.getLong("master");
			skybotConfig.mainGroup = skybotConfigJson.getLong("main_master");
			return skybotConfig;
		}
		catch (IOException e)
		{
			LOGGER.error("Skybot配置文件读取失败！");
			return new SkybotConfig();
		}
	}

	public static NapcatConfig getNapcatConfig(long botId)
	{
		Path napcatConfigPath = Path.of("napcat", "config", "onebot11_" + botId + ".json");
		NapcatConfig.WsClientConfig wsClientConfig = getNapcatWsClientConfig(napcatConfigPath, botId);
		NapcatConfig.HttpServerConfig httpServerConfig = getNapcatHttpServerConfig(napcatConfigPath, botId);
		NapcatConfig napcatConfig = new NapcatConfig();
		napcatConfig.wsClientConfig = wsClientConfig;
		napcatConfig.httpServerConfig = httpServerConfig;
		return napcatConfig;
	}

	public static void saveBotConfig(BotConfig botConfig)
	{
		saveSkybotConfig(botConfig.skybotConfig, botConfig.skybotConfig.botId);
		saveNapcatConfig(botConfig.napcatConfig, botConfig.skybotConfig.botId);
	}

	public static void saveSkybotConfig(SkybotConfig skybotConfig, long botId)
	{
		Path skybotConfigPath = Path.of("data", "bot_config_" + botId + ".json");
		try
		{
			if (skybotConfig.botId == -1)
			{
				LOGGER.warn("无法保存Skybot配置文件, BotId未配置！");
				return;
			}
			if (!Files.exists(skybotConfigPath))
			{
				Path parentPath = skybotConfigPath.getParent();
				if (!Files.exists(parentPath)) Files.createDirectories(parentPath);
				Files.createFile(skybotConfigPath);
				JSONObject rootObject = new JSONObject();
				rootObject.put("bot_id", skybotConfig.botId);
				rootObject.put("master", skybotConfig.master);
				rootObject.put("main_group", skybotConfig.mainGroup);
				Files.writeString(skybotConfigPath, rootObject.toString(4));
			}
		}
		catch (IOException e)
		{
			LOGGER.error("无法创建/写入SkyBot配置文件");
		}
	}

	public static void saveNapcatConfig(NapcatConfig napcatConfig, long botId)
	{
		Path napcatConfigPath = Path.of("napcat",  "config", "onebot11_" + botId + ".json");
		if (!Files.exists(napcatConfigPath))
		{
			try
			{
				Files.createFile(napcatConfigPath);
				JSONObject rootObject = new JSONObject();
				JSONObject network = new JSONObject();
				rootObject.put("network", network);
				JSONArray httpServers = new JSONArray();
				network.put("httpServers", httpServers);
				JSONArray wsClients = new JSONArray();
				network.put("websocketClients", wsClients);
				if (napcatConfig.wsClientConfig != null)
				{
					JSONObject wsClient = napcatConfig.wsClientConfig.toJSONObject();
					wsClients.put(wsClient);
				}
				if (napcatConfig.httpServerConfig != null)
				{
					JSONObject httpServer = napcatConfig.httpServerConfig.toJSONObject();
					httpServers.put(httpServer);
				}
				Files.writeString(napcatConfigPath, rootObject.toString(4));
			}
			catch (IOException e)
			{
				LOGGER.error("创建/写入Napcat配置文件异常！", e);
			}
		}
	}


	public static boolean deleteBotConfig(long botId)
	{
		return deleteSkybotConfig(botId) && deleteNapcatConfig(botId);
	}

	public static boolean deleteSkybotConfig(long botId)
	{
		try
		{
			Path skybotConfigPath = Path.of("data", "bot_config_" + botId + ".json");
			return Files.deleteIfExists(skybotConfigPath);
		}
		catch (IOException e)
		{
			LOGGER.error("Skybot配置文件删除异常！", e);
			return false;
		}
	}

	public static boolean deleteNapcatConfig(long botId)
	{
		try
		{
			Path napcatConfigPath = Path.of("napcat", "config", "onebot11_" + botId + ".json");
			return Files.deleteIfExists(napcatConfigPath);
		}
		catch (IOException e)
		{
			LOGGER.error("Napcat配置文件删除异常！", e);
			return false;
		}
	}

	private static NapcatConfig.HttpServerConfig getNapcatHttpServerConfig(Path napcatConfigPath, long botId)
	{
		try
		{
			if (napcatConfigPath == null) napcatConfigPath = Path.of("napcat", "config", "onebot11_" + botId + ".json");
			if (!Files.exists(napcatConfigPath)) return new NapcatConfig.HttpServerConfig();
			String fileContent = Files.readString(napcatConfigPath);
			JSONObject napcatConfigJson = new JSONObject(fileContent);
			JSONObject napcatNetworkConfigJson = napcatConfigJson.getJSONObject("network");
			JSONObject napcatHttpServerConfigJson = napcatNetworkConfigJson.getJSONArray("httpServers").getJSONObject(0);
			NapcatConfig.HttpServerConfig httpServerConfig = new NapcatConfig.HttpServerConfig();
			httpServerConfig.port = napcatHttpServerConfigJson.getInt("port");
			httpServerConfig.name = napcatHttpServerConfigJson.getString("name");
			httpServerConfig.host = napcatHttpServerConfigJson.getString("host");
			httpServerConfig.token = napcatHttpServerConfigJson.getString("token");
			httpServerConfig.debug = napcatHttpServerConfigJson.getBoolean("debug");
			httpServerConfig.enable = napcatHttpServerConfigJson.getBoolean("enable");
			httpServerConfig.enableCors = napcatHttpServerConfigJson.getBoolean("enableCors");
			httpServerConfig.enableWebsocket = napcatHttpServerConfigJson.getBoolean("enableWebsocket");
			httpServerConfig.messagePostFormat = napcatHttpServerConfigJson.getString("messagePostFormat");
			return httpServerConfig;
		}
		catch (Exception e)
		{
			LOGGER.error("Napcat之Http服务端配置读取失败！");
			return new NapcatConfig.HttpServerConfig();
		}
	}

	private static NapcatConfig.WsClientConfig getNapcatWsClientConfig(Path napcatConfigPath, long botId)
	{
		try
		{
			if (napcatConfigPath == null) napcatConfigPath = Path.of("napcat", "config", "onebot11_" + botId + ".json");
			if (!Files.exists(napcatConfigPath)) return new NapcatConfig.WsClientConfig();
			String fileContent = Files.readString(napcatConfigPath);
			JSONObject napcatConfigJson = new JSONObject(fileContent);
			JSONObject napcatNetworkConfigJson = napcatConfigJson.getJSONObject("network");
			JSONObject napcatWsClientConfigJson = napcatNetworkConfigJson.getJSONArray("websocketClients").getJSONObject(0);
			NapcatConfig.WsClientConfig wsClientConfig = new NapcatConfig.WsClientConfig();
			wsClientConfig.url = napcatWsClientConfigJson.getString("url");
			wsClientConfig.name = napcatWsClientConfigJson.getString("name");
			wsClientConfig.token = napcatWsClientConfigJson.getString("token");
			wsClientConfig.debug = napcatWsClientConfigJson.getBoolean("debug");
			wsClientConfig.enable = napcatWsClientConfigJson.getBoolean("enable");
			wsClientConfig.heartInterval = napcatWsClientConfigJson.getInt("heartInterval");
			wsClientConfig.reconnectInterval = napcatWsClientConfigJson.getInt("reconnectInterval");
			wsClientConfig.messagePostFormat = napcatWsClientConfigJson.getString("messagePostFormat");
			wsClientConfig.reportSelfMessage = napcatWsClientConfigJson.getBoolean("reportSelfMessage");
			return wsClientConfig;
		}
		catch (Exception e)
		{
			LOGGER.error("Napcat之Ws客户端配置读取失败！");
			return new NapcatConfig.WsClientConfig();
		}
	}
}
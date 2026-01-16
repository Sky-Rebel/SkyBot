package com.skybot;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class BotConfig
{
	public BotConfig()
	{
		wsClientConfig = new WSClientConfig();
		httpServerConfig = new HttpServerConfig();
	}

	public static final Path SKYBOT_BOT_CONFIG_PATH = Path.of("bot_config.json");

	public static Path napcatBotConfigPath;

	public static final Logger LOGGER = LoggerFactory.getLogger(BotConfig.class);

	/**
	 * 机器人ID默认值
	 */
	public static final long DEF_BOT_ID = -12345;

	/**
	 * 机器人ID
	 */
	public long botId = DEF_BOT_ID;

	public HttpServerConfig httpServerConfig;

	public WSClientConfig wsClientConfig;

	public static class HttpServerConfig
	{
		/**
		 * HTTP服务器端口位置
		 */
		public int port = 5200;

		/**
		 * HTTP服务器上报原始(raw)数据与否
		 */
		public boolean debug = false;

		/**
		 * HTTP服务器启动状态
		 */
		public boolean enable = true;

		/**
		 * HTTP服务器鉴权密钥
		 */
		public String token = "skybot";

		/**
		 * HTTP服务器主机位置
		 */
		public String host = "127.0.0.1";

		/**
		 * 尚未赋予意义之字段
		 */
		public boolean enableCors = false;

		/**
		 * HTTP服务器唯一标识
		 */
		public String name = "HttpServer";

		/**
		 * 尚未赋予意义之字段
		 */
		public boolean enableWebsocket = false;

		/**
		 * HTTP服务器消息上报格式
		 * 可选 -> (string/array)
		 */
		public String messagePostFormat = "array";

		public JSONObject toJSONObject()
		{
			return new JSONObject()
				.put("name", name)
				.put("port", port)
				.put("host", host)
				.put("debug", debug)
				.put("token", token)
				.put("enable", enable)
				.put("enableCors", enableCors)
				.put("enableWebsocket", enableWebsocket)
				.put("messagePostFormat", messagePostFormat);
		}

		@Override
		public String toString()
		{
			return "HttpServerConfig{" +
				"port=" + port +
				", debug=" + debug +
				", enable=" + enable +
				", token='" + token + '\'' +
				", host='" + host + '\'' +
				", enableCors=" + enableCors +
				", name='" + name + '\'' +
				", enableWebsocket=" + enableWebsocket +
				", messagePostFormat='" + messagePostFormat + '\'' +
				'}';
		}
	}

	public static class WSClientConfig
	{
		/**
		 * WS客户端上报原始(raw)数据与否
		 */
		public boolean debug = true;

		/**
		 * WS客户端启用状态
		 */
		public boolean enable = true;

		/**
		 * WS客户端鉴权密钥
		 */
		public String token = "SkyBot";

		/**
		 * WS客户端唯一标识
		 */
		public String name = "WSClient";

		/**
		 * WS客户端心跳间隔
		 */
		public int heartInterval = 30000;

		/**
		 * WS客户端重连间隔
		 */
		public int reconnectInterval = 5000;

		/**
		 * WS客户端上报自身消息与否
		 */
		public boolean reportSelfMessage = false;

		/**
		 * WS客户端消息上报格式
		 */
		public String messagePostFormat = "array";

		/**
		 * WS客户端上报地址
		 */
		public String url = "ws://127.0.0.1:5210";

		public JSONObject toJSONObject()
		{
			return new JSONObject()
				.put("name", name)
				.put("enable", enable)
				.put("url", url)
				.put("messagePostFormat", messagePostFormat)
				.put("reportSelfMessage", reportSelfMessage)
				.put("reconnectInterval", reconnectInterval)
				.put("token", token)
				.put("debug", debug)
				.put("heartInterval", heartInterval);
		}

		@Override
		public String toString()
		{
			return "WSClientConfig{" +
				"debug=" + debug +
				", enable=" + enable +
				", token='" + token + '\'' +
				", name='" + name + '\'' +
				", heartInterval=" + heartInterval +
				", reconnectInterval=" + reconnectInterval +
				", reportSelfMessage=" + reportSelfMessage +
				", messagePostFormat='" + messagePostFormat + '\'' +
				", url='" + url + '\'' +
				'}';
		}
	}

	public static BotConfig getBotConfig()
	{
		if (!Files.exists(SKYBOT_BOT_CONFIG_PATH)) return new BotConfig();
		BotConfig botConfig = new BotConfig();
		JSONObject napcatConfig = new JSONObject();
		try
		{
			JSONObject skybotConfig = new JSONObject(Files.readString(SKYBOT_BOT_CONFIG_PATH));
			botConfig.botId = skybotConfig.getLong("bot_id");
			Path napcatConfigPath = Path.of("napcat", "config", "onebot11_" + botConfig.botId + ".json");
			if (!Files.exists(napcatConfigPath)) return botConfig;
			napcatConfig = new JSONObject(Files.readString(napcatConfigPath));
		}
		catch (IOException e)
		{
			LOGGER.error("Bot配置文件读取异常！", e);
		}
		JSONObject napcatNetworkConfig = napcatConfig.getJSONObject("network");
		JSONObject napcatHttpServersConfig = napcatNetworkConfig.getJSONArray("httpServers").getJSONObject(0);
		BotConfig.HttpServerConfig httpServerConfig = new HttpServerConfig();
		httpServerConfig.debug = napcatHttpServersConfig.getBoolean("debug");
		httpServerConfig.enable = napcatHttpServersConfig.getBoolean("enable");
		httpServerConfig.enableCors = napcatHttpServersConfig.getBoolean("enableCors");
		httpServerConfig.host = napcatHttpServersConfig.getString("host");
		httpServerConfig.name = napcatHttpServersConfig.getString("name");
		httpServerConfig.enableWebsocket = napcatHttpServersConfig.getBoolean("enableWebsocket");
		httpServerConfig.messagePostFormat = napcatHttpServersConfig.getString("messagePostFormat");
		httpServerConfig.port = napcatHttpServersConfig.getInt("port");
		httpServerConfig.token = napcatHttpServersConfig.getString("token");
		botConfig.httpServerConfig = httpServerConfig;
		JSONObject napcatWSClientsConfig = napcatNetworkConfig.getJSONArray("websocketClients").getJSONObject(0);
		BotConfig.WSClientConfig wsClientConfig = new WSClientConfig();
		wsClientConfig.debug = napcatWSClientsConfig.getBoolean("debug");
		wsClientConfig.enable = napcatWSClientsConfig.getBoolean("enable");
		wsClientConfig.name = napcatWSClientsConfig.getString("name");
		wsClientConfig.messagePostFormat = napcatWSClientsConfig.getString("messagePostFormat");
		wsClientConfig.url = napcatWSClientsConfig.getString("url");
		wsClientConfig.heartInterval = napcatWSClientsConfig.getInt("heartInterval");
		wsClientConfig.reconnectInterval = napcatWSClientsConfig.getInt("reconnectInterval");
		wsClientConfig.reportSelfMessage = napcatWSClientsConfig.getBoolean("reportSelfMessage");
		wsClientConfig.token = napcatWSClientsConfig.getString("token");
		botConfig.wsClientConfig = wsClientConfig;
		return botConfig;
	}

	public static void saveConfig(BotConfig botConfig)
	{
		if (botConfig.botId == DEF_BOT_ID)
		{
			LOGGER.warn("无法保存配置文件, robotId字段未赋新值");
			return;
		}
		if (!Files.exists(SKYBOT_BOT_CONFIG_PATH))
		{
			try
			{
				Files.createFile(SKYBOT_BOT_CONFIG_PATH);
				JSONObject rootObject = new JSONObject();
				rootObject.put("bot_id", botConfig.botId);
				Files.writeString(SKYBOT_BOT_CONFIG_PATH, rootObject.toString());
			}
			catch (IOException e)
			{
				LOGGER.error("无法创建/写入SkyBot配置文件");
			}
		}
		napcatBotConfigPath = Path.of("napcat",  "config", "onebot11_" + botConfig.botId + ".json");
		if (!Files.exists(BotConfig.napcatBotConfigPath))
		{
			try
			{
				Files.createFile(napcatBotConfigPath);
				JSONObject rootObject = new JSONObject();
				JSONObject network = new JSONObject();
				rootObject.put("network", network);
				JSONArray httpServers = new JSONArray();
				network.put("httpServers", httpServers);
				JSONArray wsClients = new JSONArray();
				network.put("websocketClients", wsClients);
				if (botConfig.httpServerConfig != null)
				{
					JSONObject httpServer = botConfig.httpServerConfig.toJSONObject();
					httpServers.put(httpServer);
				}
				if (botConfig.wsClientConfig != null)
				{
					JSONObject wsClient = botConfig.wsClientConfig.toJSONObject();
					wsClients.put(wsClient);
				}
				Gson gson = new GsonBuilder()
					.setPrettyPrinting()
					.registerTypeAdapter(Number.class, (JsonDeserializer<Number>)(json, typeOfT, context) ->
					{

						JsonPrimitive element = json.getAsJsonPrimitive();
						if (element.isNumber())
						{
							Number num = element.getAsNumber();
							if (num.doubleValue() == num.longValue()) return num.longValue();
							return num;
						}
						return null;
					})
					.create();
				String uglyJson = rootObject.toString();
				JsonElement jsonElement = gson.fromJson(uglyJson, JsonElement.class);
				StringWriter writer = new StringWriter();
				JsonWriter jsonWriter = new JsonWriter(writer);
				jsonWriter.setIndent(" ".repeat(4));
				jsonWriter.setLenient(true);
				gson.toJson(jsonElement, jsonWriter);
				jsonWriter.close();
				String prettyJson = writer.toString();
				Files.writeString(napcatBotConfigPath, prettyJson);
			}
			catch (IOException e)
			{
				LOGGER.error("创建/写入/格式化Napcat配置文件异常！", e);
			}
		}
	}
}
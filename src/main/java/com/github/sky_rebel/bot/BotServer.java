package com.github.sky_rebel.bot;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.StringJoiner;

public class BotServer
{
	public static final Logger LOGGER = LoggerFactory.getLogger(BotServer.class);

	public URI uri;

	public BotConfig botConfig;

	public static final String scheme = "http";

	public int port;

	public String host;

	public BotServer(Bot bot, String path)
	{
		try
		{
			if (!bot.isStart())
			{
				LOGGER.warn("Bot未启动，无法调用Api功能！");
				return;
			}
			this.botConfig = bot.getBotConfig();
			this.port = botConfig.napcatConfig.httpServerConfig.port;
			this.host = botConfig.napcatConfig.httpServerConfig.host;
			uri = new URI(scheme, null, host, port, path, null, null);
			System.out.println(botConfig.napcatConfig.httpServerConfig.toString());
		}
		catch (URISyntaxException e)
		{
			LOGGER.warn("URI创建异常！非法URl！");
		}
	}

	public static class APIRequestResult
	{
		public boolean isSuccess = false;

		public int retcode;

		public String message;

		public String wording;

		public Object data;

		public static APIRequestResult getInstance(JSONObject requestResult)
		{
			APIRequestResult apiRequestResult = new APIRequestResult();
			apiRequestResult.isSuccess = requestResult.optString("status").equals("ok");
			apiRequestResult.retcode = requestResult.optInt("retcode");
			apiRequestResult.message = requestResult.optString("message");
			apiRequestResult.wording = requestResult.optString("wording");
			apiRequestResult.data = requestResult.opt("data");
			return apiRequestResult;
		}

		@Override
		public String toString()
		{
			return new StringJoiner(", ", APIRequestResult.class.getSimpleName() + "[", "]")
				.add("isSuccess=" + isSuccess)
				.add("retcode=" + retcode)
				.add("message='" + message + "'")
				.add("wording='" + wording + "'")
				.add("data=" + data)
				.toString();
		}
	}

	public APIRequestResult sendRequest(String requestBody)
	{
		if (uri == null)
		{
			LOGGER.error("URI未创建，无法发送API请求！");
			return null;
		}
		try (HttpClient httpClient = HttpClient.newBuilder().build())
		{
			HttpRequest httpRequest = HttpRequest
				.newBuilder()
				.uri(uri)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			System.out.println(httpResponse.body());
			JSONObject requestResult = new JSONObject(httpResponse.body());
			return APIRequestResult.getInstance(requestResult);
		}
		catch (InterruptedException | IOException e) {
			LOGGER.error("请求Api发生异常", e);
			return null;
		}
	}
}

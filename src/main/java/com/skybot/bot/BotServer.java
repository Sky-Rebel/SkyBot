package com.skybot.bot;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BotServer
{
	public static final Logger LOGGER = LoggerFactory.getLogger(BotServer.class);

	public URI uri;

	public BotConfig botConfig;

	public static final String scheme = "http";

	public int port;

	public String host;

	public BotServer(String path)
	{
		try
		{
			if (!Bot.isStart)
			{
				LOGGER.warn("Bot未启动，无法调用Api功能！");
				return;
			}
			this.botConfig = Bot.config;
			this.port = botConfig.httpServerConfig.port;
			this.host = botConfig.httpServerConfig.host;
			uri = new URI(scheme, null, host, port, path, null, null);
		}
		catch (URISyntaxException e)
		{
			LOGGER.warn("URI创建异常！非法URl！");
		}
	}

	public static class APIRequestResult
	{
		public boolean isSuccess;

		public int retcode;

		public String message;

		public String wording;

		public JSONObject data;

		public static APIRequestResult getInstance(JSONObject requestResult)
		{
			APIRequestResult APIRequestResult = new APIRequestResult();
			APIRequestResult.isSuccess = requestResult.optString("status").equals("ok");
			APIRequestResult.retcode = requestResult.optInt("retcode");
			APIRequestResult.message = requestResult.optString("message");
			APIRequestResult.wording = requestResult.optString("wording");
			APIRequestResult.data = requestResult.optJSONObject("data", new JSONObject());
			return APIRequestResult;
		}

		@Override
		public String toString()
		{
			return "BotRequestResult{" +
				"isSuccess=" + isSuccess +
				", returnCode=" + retcode +
				", message='" + message + '\'' +
				", wording='" + wording + '\'' +
				", data=" + data +
				'}';
		}
	}

	public APIRequestResult sendRequest(String requestBody)
	{
		try (HttpClient httpClient = HttpClient.newBuilder().build())
		{
			HttpRequest httpRequest = HttpRequest
				.newBuilder()
				.uri(uri)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			JSONObject requestResult = new JSONObject(httpResponse.body());
			return APIRequestResult.getInstance(requestResult);
		}
		catch (InterruptedException | IOException e) {
			LOGGER.error("请求Api发生异常 -> {}", e.getMessage());
			return null;
		}
	}
}

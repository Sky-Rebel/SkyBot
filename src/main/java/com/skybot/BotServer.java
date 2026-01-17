package com.skybot;

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
			throw new RuntimeException(e);
		}
	}

	public static class BotRequestResult
	{
		public boolean isSuccess;

		public int returnCode;

		public String message;

		public String wording;

		public JSONObject data;

		public static BotRequestResult getInstance(JSONObject requestResult)
		{
			BotRequestResult botRequestResult = new BotRequestResult();
			botRequestResult.isSuccess = requestResult.optString("status").equals("ok");
			botRequestResult.returnCode = requestResult.optInt("retcode");
			botRequestResult.message = requestResult.optString("message");
			botRequestResult.wording = requestResult.optString("wording");
			botRequestResult.data = (JSONObject) requestResult.opt("data");
			return botRequestResult;
		}

		@Override
		public String toString()
		{
			return "BotRequestResult{" +
				"isSuccess=" + isSuccess +
				", returnCode=" + returnCode +
				", message='" + message + '\'' +
				", wording='" + wording + '\'' +
				", data=" + data +
				'}';
		}
	}

	public BotRequestResult sendRequest(String requestBody)
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
			return BotRequestResult.getInstance(requestResult);
		}
		catch (InterruptedException | IOException e) {
			LOGGER.error("请求Api发生异常 -> {}", e.getMessage());
			return null;
		}
	}
}

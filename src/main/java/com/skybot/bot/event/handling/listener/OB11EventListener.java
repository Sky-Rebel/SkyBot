package com.skybot.bot.event.handling.listener;

import com.skybot.bot.Bot;
import com.skybot.bot.BotConfig;
import com.skybot.bot.event.handling.handler.OB11EventHandler;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

public class OB11EventListener extends WebSocketServer
{
	public static final Logger LOGGER = LoggerFactory.getLogger(OB11EventListener.class);

	public OB11EventListener()
	{
		InetSocketAddress inetSocketAddress = null;
		try
		{
			BotConfig botConfig = Bot.config;
			BotConfig.WSClientConfig wsClientConfig = botConfig.wsClientConfig;
			String url = wsClientConfig.url;
			URI uri = new URI(url);
			inetSocketAddress = new InetSocketAddress(uri.getHost(), uri.getPort());
		}
		catch (URISyntaxException e)
		{
			LOGGER.warn("URI创建异常！非法URl！");
		}
		super(inetSocketAddress);
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {}

	@Override
	public void onClose(WebSocket webSocket, int i, String s, boolean b)
	{
		LOGGER.info("Bot WS客户端既已关闭！");
	}

	@Override
	public void onMessage(WebSocket webSocket, String s)
	{
		JSONObject jsonObject = new JSONObject(s);
		System.out.println(jsonObject.toString(4) + "\n".repeat(5));
		OB11EventHandler.dispatch(jsonObject);
	}

	@Override
	public void onError(WebSocket webSocket, Exception e)
	{
		e.printStackTrace();
	}

	@Override
	public void onStart()
	{
		LOGGER.info("Bot WS客户端启动成功！");
	}
}

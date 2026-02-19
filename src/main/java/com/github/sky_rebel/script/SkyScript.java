package com.github.sky_rebel.script;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;
import com.github.sky_rebel.script.compile.ScriptCompiling;
import com.github.sky_rebel.skybot.util.logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SkyScript
{
	private static final String SCRIPT_DIR = "SkybotScript";

	private static final String GROUP_MESSAGE_EVENT_SCRIPT = "群聊消息.ql";

	private static final String PRIVATE_MESSAGE_EVENT_SCRIPT = "私聊消息.ql";

	private static final Path SCRIPT_DIR_PATH = Path.of(SCRIPT_DIR);

	private static final Path GROUP_MESSAGE_EVENT_SCRIPT_PATH = Path.of(SCRIPT_DIR, GROUP_MESSAGE_EVENT_SCRIPT);

	private static final Path PRIVATE_MESSAGE_EVENT_SCRIPT_PATH = Path.of(SCRIPT_DIR, PRIVATE_MESSAGE_EVENT_SCRIPT);

	private static final Logger LOGGER = SkybotLogger.getLogger(SkyScript.class);

	public static void main(String[] args)
	{
		if (args[0].equals("init"))
		{
			if (!Files.exists(SCRIPT_DIR_PATH))
			{
				try
				{
					Files.createDirectories(SCRIPT_DIR_PATH);
				}
				catch (IOException e)
				{
					LOGGER.error("脚本目录创建失败！");
				}
			}
			if (!Files.exists(GROUP_MESSAGE_EVENT_SCRIPT_PATH))
			{
				try
				{
					Files.createFile(GROUP_MESSAGE_EVENT_SCRIPT_PATH);
				}
				catch (IOException e)
				{
					LOGGER.error("群聊消息.ql文件创建失败！");
				}
			}
			if (!Files.exists(PRIVATE_MESSAGE_EVENT_SCRIPT_PATH))
			{
				try
				{
					Files.createFile(PRIVATE_MESSAGE_EVENT_SCRIPT_PATH);
				}
				catch (IOException e)
				{
					LOGGER.error("私聊消息.ql文件创建失败！");
				}
			}
		}
		else
		{
			try
			{
				Path scriptDirPath = Path.of(args[1]);
				if (!Files.exists(scriptDirPath))
				{
					LOGGER.error("非法目录提交");
					return;
				}
				if (!scriptDirPath.getFileName().toString().endsWith(".ql"))
				{           
					LOGGER.error("非法文件后辍");
					return;
				}
				long botId = Long.parseLong(args[0]);
				Objects.requireNonNull(Bot.createBot(botId)).start();
				CompileResult compileResult = ScriptCompiling.compile(scriptDirPath.toFile());
				LOGGER.info("编译" + (compileResult.isSuccess() ? "成功" : "失败") + " -> Code: " + compileResult.getCode() + " Msg: " + compileResult.getMsg());
			}
			catch (NumberFormatException e)
			{
				LOGGER.error("非法账号格式");
			}
			catch (NullPointerException e)
			{
				LOGGER.error("Bot启动异常");
			}
		}
	}
}
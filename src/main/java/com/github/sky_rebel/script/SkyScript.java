package com.github.sky_rebel.script;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.util.logger.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SkyScript
{
	private static final Logger LOGGER = new Logger(SkyScript.class);

	public static void main(String[] args)
	{
		try
		{
			if (args.length < 2)
			{
				LOGGER.error("非法参数数量");
				return;
			}
			Path scriptPath = Path.of(args[1]);
			if (!Files.exists(scriptPath))
			{
				LOGGER.error("非法文件提交");
				return;
			}
			if (!scriptPath.getFileName().toString().endsWith(".sky"))
			{
				LOGGER.error("非法文件后辍");
				return;
			}
			long botId = Long.parseLong(args[0]);
			Objects.requireNonNull(Bot.createBot(botId)).start();
			CompileResult compileResult = ScriptCompiling.compile(scriptPath.toFile());
			LOGGER.info("编译" + (compileResult.isSuccess() ? "成功" : "失败") + " -> Code: " + compileResult.getCode() + " Msg: " + compileResult.getMsg());
		}
		catch (NumberFormatException e)
		{
			LOGGER.error("非法账号格式");
			return;
		}
		catch (NullPointerException e)
		{
			LOGGER.error("Bot启动异常");
			return;
		}
	}
}
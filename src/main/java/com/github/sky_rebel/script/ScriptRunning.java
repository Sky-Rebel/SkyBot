package com.github.sky_rebel.script;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.api.OB11MessageApiService;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.script.compile.ScriptCompiling;

import java.util.List;
import java.util.Map;

public class ScriptRunning
{
	public static void run(Bot bot, OB11GroupMessageEvent ob11GroupMessageEvent)
	{
		OB11MessageApiService ob11MessageApiService = bot.getMessageApiService();
		List<CodeBlock> groupCodeBlock = ScriptCompiling.getGroupCodeBlockList();
		if (!groupCodeBlock.isEmpty())
		{
			groupCodeBlock.forEach(codeBlock ->
			{
				if (ob11GroupMessageEvent.getRawMessage().equals(codeBlock.getCommand().trim()))
				{
					Map<Integer, List<String>> operation = codeBlock.getOperation();
					operation.forEach((operationCode, params) ->
					{
						switch (operationCode)
						{
							case 0x0 ->
							{
								ob11MessageApiService.sendGroupTextMessage(ob11GroupMessageEvent.getGroupId(), params.getFirst());
							}
						}
					});
				}
			});
		}
	}
}
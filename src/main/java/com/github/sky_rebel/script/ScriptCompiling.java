package com.github.sky_rebel.script;

import com.github.sky_rebel.bot.util.logger.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ScriptCompiling
{
	private static final Logger LOGGER = new Logger(ScriptCompiling.class);

	private static final String PRIVATE_TAG = "#私聊 ";

	private static final String GROUP_TAG = "#群聊 ";

	private static final String OPERATION_SEND_TEXT_MSG = "$发送文本";

	private static List<CodeBlock> groupCodeBlockList = new ArrayList<>();

	private static List<CodeBlock> privateCodeBlockList = new ArrayList<>();

	public static CompileResult compile(File scriptFile)
	{
		CompileResult compileResult = new CompileResult();
		try
		{
			FileInputStream fileInputStream = new FileInputStream(scriptFile);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			String scriptContent = new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8);
			if (scriptContent.isEmpty())
			{
				compileResult.setCode(0x1);
				compileResult.setSuccess(false);
				compileResult.setMsg("脚本内容为空");
				return compileResult;
			}
			List<String> codeBlockList = Arrays.stream(scriptContent.split("\n".repeat(2))).toList();
			codeBlockList.forEach(codeBlock ->
			{
				List<String> commandBlock = Arrays.stream(codeBlock.split("\n")).toList();
				CodeBlock groupCodeBlock = new CodeBlock();
				CodeBlock privateCodeBlock = new CodeBlock();
				if (commandBlock.size() > 1)
				{
					String firstLine = commandBlock.getFirst();
					String command = firstLine.substring(4);
					groupCodeBlock.setCommand(command);
					privateCodeBlock.setCommand(command);
					boolean isGroup;
					boolean isPrivate;
					if (firstLine.startsWith(GROUP_TAG))
					{
						isGroup = true;
						isPrivate = false;
					}
					else if (firstLine.startsWith(PRIVATE_TAG))
					{
						isGroup = false;
						isPrivate = true;
					}
					else
					{
						isGroup = false;
						isPrivate = false;
					}
					commandBlock.stream().skip(1).forEach(operation ->
					{
						if (operation.startsWith("$") && operation.endsWith("$"))
						{
							String[] functionParts = operation.substring(1, operation.length() -1).trim().split(" ");
							List<String> commandParms = Arrays.stream(Arrays.copyOfRange(functionParts, 1, functionParts.length)).toList();
							if (isGroup)
							{
								Map<Integer, List<String>> opertionList = new LinkedHashMap<>();
								if (operation.startsWith(OPERATION_SEND_TEXT_MSG))
								{
									opertionList.put(0x0, commandParms);
								}
								else
								{
									compileResult.setCode(0x5);
									compileResult.setSuccess(false);
									compileResult.setMsg("未知群聊函数");
								}
								groupCodeBlock.setOperation(opertionList);
							}
							else if (isPrivate)
							{
								Map<Integer, List<String>> opertionList = new LinkedHashMap<>();
								if (operation.startsWith(OPERATION_SEND_TEXT_MSG))
								{
									opertionList.put(0x0, commandParms);
								}
								else
								{
									compileResult.setCode(0x6);
									compileResult.setSuccess(false);
									compileResult.setMsg("未知私聊函数");
								}
								privateCodeBlock.setOperation(opertionList);
							}
						}
						else
						{
							compileResult.setCode(0x7);
							compileResult.setSuccess(false);
							compileResult.setMsg("非法函数格式");
						}
						groupCodeBlockList.add(groupCodeBlock);
						privateCodeBlockList.add(privateCodeBlock);
					});
				}
			});
		}
		catch (FileNotFoundException e)
		{
			compileResult.setCode(0x2);
			compileResult.setSuccess(false);
			compileResult.setMsg("脚本文件无存");
		}
		catch (IOException e)
		{
			compileResult.setCode(0x3);
			compileResult.setSuccess(false);
			compileResult.setMsg("脚本读取错误");
		}
		return compileResult;
	}

	public static List<CodeBlock> getGroupCodeBlockList()
	{
		return groupCodeBlockList;
	}

	public static List<CodeBlock> getPrivateCodeBlockList()
	{
		return privateCodeBlockList;
	}
}
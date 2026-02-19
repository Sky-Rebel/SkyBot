package com.github.sky_rebel.script.compile;

import com.github.sky_rebel.skybot.util.logger.SkybotLogger;
import com.github.sky_rebel.script.CodeBlock;
import com.github.sky_rebel.script.CompileResult;
import com.github.sky_rebel.skybot.util.logger.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptCompiling
{
	private static final Logger LOGGER = SkybotLogger.getLogger(ScriptCompiling.class);

	private static final String PRIVATE_TAG_START = "#私聊";

	private static final String PRIVATE_TAG_END = "私聊#";

	private static final String GROUP_TAG_START = "#群聊";

	private static final String GROUP_TAG_END = "#群聊";

	private static final List<CodeBlock> GROUP_CODE_BLOCK_LIST = new ArrayList<>();

	private static final List<CodeBlock> PRIVATE_CODE_BLOCK_LIST = new ArrayList<>();

	public static CompileResult compile(File scriptFile)
	{
		CompileResult compileResult = new CompileResult();
		compileResult.setCode(0x0);
		compileResult.setSuccess(true);
		compileResult.setMsg("脚本编译成功");
		compileResult.setErrorLine(-1);
		try
		{
			FileInputStream fileInputStream = new FileInputStream(scriptFile);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			String scriptContent = new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8).replaceAll("\\r\\n|\\r", "\n");
			if (scriptContent.isEmpty())
			{
				compileResult.setCode(0x1);
				compileResult.setSuccess(false);
				compileResult.setMsg("脚本内容为空");
				compileResult.setErrorLine(0);
				return compileResult;
			}
			System.out.println(scriptContent);
			List<String> scriptLineList = Arrays.stream(scriptContent.split("\n")).toList();
			ModuleTagFindInfo groupModuleTagFindInfo = ModuleTagFindInfo.getInstance(scriptLineList, GROUP_TAG_START, GROUP_TAG_END);
			System.out.println(groupModuleTagFindInfo);
			if (!groupModuleTagFindInfo.isSuccess())
			{
				String errorMsg = switch (groupModuleTagFindInfo.getCode())
				{
					case 0x1 -> "没有找到群聊模块开始标签";
					case 0x2 -> "没有找到群聊模块结束标签";
					case 0x3 -> "群聊开始标签不得在结束标签之后";
					default -> "群聊模块处理错误";
				};
				compileResult.setCode(0x1);
				compileResult.setSuccess(false);
				compileResult.setMsg(errorMsg);
				compileResult.setErrorLine(-1);
				return compileResult;
			}
			System.out.println(groupModuleTagFindInfo);
			ModuleTagFindInfo privateModuleTagFindInfo = ModuleTagFindInfo.getInstance(scriptLineList, PRIVATE_TAG_START, PRIVATE_TAG_END);
			if (!privateModuleTagFindInfo.isSuccess())
			{
				String errorMsg = switch (privateModuleTagFindInfo.getCode())
				{
					case 0x1 -> "没有找到私聊模块开始标签";
					case 0x2 -> "没有找到私聊模块结束标签";
					case 0x3 -> "私聊开始标签不得在结束标签之后";
					default -> "私聊模块处理错误";
				};
				compileResult.setCode(0x1);
				compileResult.setSuccess(false);
				compileResult.setMsg(errorMsg);
				compileResult.setErrorLine(-1);
				return compileResult;
			}
//			List<String> codeBlockList = Arrays.stream(scriptContent.split("\n".repeat(2))).toList();
//			int currentGlobalLine = 1;
//			for (String codeBlock : codeBlockList)
//			{
//				compileResult.setErrorLine(currentGlobalLine);
//				System.out.println(currentGlobalLine);
//				List<String> commandBlock = Arrays.stream(codeBlock.split("\n")).toList();
//				// 指令块行数
//				int blockInnerLine = 1;
//				CodeBlock groupCodeBlock = new CodeBlock();
//				CodeBlock privateCodeBlock = new CodeBlock();
//				if (commandBlock.size() > 1)
//				{
//					String firstLine = commandBlock.getFirst();
//					String command = firstLine.substring(4);
//					groupCodeBlock.setCommand(command);
//					privateCodeBlock.setCommand(command);
//					boolean isGroup;
//					boolean isPrivate;
//					if (firstLine.startsWith(GROUP_TAG_END))
//					{
//						isGroup = true;
//						isPrivate = false;
//					}
//					else if (firstLine.startsWith(PRIVATE_TAG_END))
//					{
//						isGroup = false;
//						isPrivate = true;
//					}
//					else
//					{
//						isGroup = false;
//						isPrivate = false;
//					}
//					commandBlock.stream().skip(1).forEach(operation ->
//					{
//						if (operation.startsWith("$") && operation.endsWith("$"))
//						{
//							String[] functionParts = operation.substring(1, operation.length() -1).trim().split(" ");
//							List<String> commandParms = Arrays.stream(Arrays.copyOfRange(functionParts, 1, functionParts.length)).toList();
//							if (isGroup)
//							{
//								Map<Integer, List<String>> opertionList = new LinkedHashMap<>();
//								if (operation.startsWith(OPERATION_SEND_TEXT_MSG))
//								{
//									opertionList.put(0x0, commandParms);
//								}
//								else
//								{
//									compileResult.setCode(0x5);
//									compileResult.setSuccess(false);
//									compileResult.setMsg("未知群聊函数");
//								}
//								groupCodeBlock.setOperation(opertionList);
//							}
//							else if (isPrivate)
//							{
//								Map<Integer, List<String>> opertionList = new LinkedHashMap<>();
//								if (operation.startsWith(OPERATION_SEND_TEXT_MSG))
//								{
//									opertionList.put(0x0, commandParms);
//								}
//								else
//								{
//									compileResult.setCode(0x6);
//									compileResult.setSuccess(false);
//									compileResult.setMsg("未知私聊函数");
//								}
//								privateCodeBlock.setOperation(opertionList);
//							}
//						}
//						else
//						{
//							compileResult.setCode(0x7);
//							compileResult.setSuccess(false);
//							compileResult.setMsg("非法函数格式");
//						}
//						groupCodeBlockList.add(groupCodeBlock);
//						privateCodeBlockList.add(privateCodeBlock);
//					});
//				}
//				currentGlobalLine++;
//				blockInnerLine++;
//			}
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
		return GROUP_CODE_BLOCK_LIST;
	}

	public static List<CodeBlock> getPrivateCodeBlockList()
	{
		return PRIVATE_CODE_BLOCK_LIST;
	}
}
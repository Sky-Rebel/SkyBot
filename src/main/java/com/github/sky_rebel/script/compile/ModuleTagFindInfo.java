package com.github.sky_rebel.script.compile;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

class ModuleTagFindInfo
{
	private int code;

	private int endLine;

	private int startLine;

	private boolean isSuccess;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public int getEndLine()
	{
		return endLine;
	}

	public void setEndLine(int endLine)
	{
		this.endLine = endLine;
	}

	public int getStartLine()
	{
		return startLine;
	}

	public void setStartLine(int startLine)
	{
		this.startLine = startLine;
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(boolean success)
	{
		isSuccess = success;
	}

	@Override
	public String toString()
	{
		return "ModuleTagLineFindInfo{" + "startLine=" + startLine + ", endLine=" + endLine + ", isSuccess=" + isSuccess + ", code=" + code + '}';
	}

	public static ModuleTagFindInfo getInstance(List<String> scriptLineList, String startTag, String endTag)
	{
		ModuleTagFindInfo moduleTagFindInfo = new ModuleTagFindInfo();
		moduleTagFindInfo.setCode(0x0);
		moduleTagFindInfo.setSuccess(false);
		moduleTagFindInfo.setStartLine(-1);
		moduleTagFindInfo.setEndLine(-1);
		System.out.println(scriptLineList);
		OptionalInt startIndex = IntStream.range(0, scriptLineList.size()).filter(index -> scriptLineList.get(index).equals(startTag)).findFirst();
		if (!startIndex.isPresent())
		{
			moduleTagFindInfo.setCode(0x1);
			return moduleTagFindInfo;
		}
		moduleTagFindInfo.setStartLine(startIndex.getAsInt() + 1);
		OptionalInt endIndex = IntStream.range(0, scriptLineList.size()).filter(index -> scriptLineList.get(index).equals(endTag)).findFirst();
		if (!endIndex.isPresent())
		{
			moduleTagFindInfo.setCode(0x2);
			return moduleTagFindInfo;
		}
		moduleTagFindInfo.setEndLine(endIndex.getAsInt() + 1);
		if (startIndex.getAsInt() < endIndex.getAsInt())
		{
			moduleTagFindInfo.setCode(0x3);
			return moduleTagFindInfo;
		}
		return moduleTagFindInfo;
	}
}
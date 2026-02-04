package com.github.sky_rebel.script;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class CodeBlock
{
	private String command;

	private Map<Integer, List<String>> operation;

	public String getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = command;
	}

	public Map<Integer, List<String>> getOperation()
	{
		return operation;
	}

	public void setOperation(Map<Integer, List<String>> operation)
	{
		this.operation = operation;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", CodeBlock.class.getSimpleName() + "[", "]").add("command='" + command + "'")
		.add("operation=" + operation)
		.toString();
	}
}
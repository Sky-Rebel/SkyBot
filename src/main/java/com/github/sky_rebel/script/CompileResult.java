package com.github.sky_rebel.script;

public class CompileResult
{
	private int code;

	private String msg;

	private boolean isSuccess;

	private int errorLine;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(boolean success)
	{
		isSuccess = success;
	}

	public int getErrorLine()
	{
		return errorLine;
	}

	public void setErrorLine(int errorLine)
	{
		this.errorLine = errorLine;
	}
}
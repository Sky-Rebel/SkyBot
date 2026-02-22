package com.github.sky_rebel.skybot.util.data;

import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataManage
{
	private static boolean isInit;

	private static final String FILE_NAME = "data.json";

	private static final Path FILE_PATH = Path.of(FILE_NAME);

	private static final Logger LOGGER = SkybotLogger.getLogger(DataManage.class);

	static
	{
		try
		{
			if (!Files.exists(FILE_PATH))
			{
				Files.createFile(FILE_PATH);
				Files.writeString(FILE_PATH, "{}");
			}
			else
			{
				try
				{
					new JSONObject(Files.readString(FILE_PATH));
				}
				catch (JSONException e)
				{
					LOGGER.error("数据文件内容格式非法");
					Files.writeString(FILE_PATH, "{}");
				}
			}
			isInit = true;
		}
		catch (Exception e)
		{
			LOGGER.error("数据文件初始化失败", e);
			isInit = false;
		}
	}

	public static Object getDataClassByKey(String key, Class<Object> dataClass)
	{
		Gson gson = new Gson();
		return gson.fromJson(getData().optJSONObject(key).toString(), dataClass);
	}

	public static void putDataClassByKey(String key, Object dataClass)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		putData(getData().put(key, gson.toJson(dataClass)));
	}

	public static JSONObject getData()
	{
		try
		{
			if (!isInit)
			{
				LOGGER.error("未初始化，无法读取数据");
				return new JSONObject();
			}
			return new JSONObject(Files.readString(FILE_PATH));
		}
		catch (IOException e)
		{
			LOGGER.error("数据读取失败", e);
			return new JSONObject();
		}
	}

	public static void putData(JSONObject json)
	{
		try
		{
			if (!isInit)
			{
				LOGGER.error("未初始化，无法写入数据");
				return;
			}
			Files.writeString(FILE_PATH, json.toString(4));
		}
		catch (IOException e)
		{
			LOGGER.error("数据写入失败", e);
		}
	}
}
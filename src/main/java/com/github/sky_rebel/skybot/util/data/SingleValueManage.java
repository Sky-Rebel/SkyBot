package com.github.sky_rebel.skybot.util.data;

import org.json.JSONObject;

import java.nio.file.Files;

public class SingleValueManage
{
	private static final String DATA_KEY = "single";

	public static void set(String key, Object value)
	{
		JSONObject data = DataManage.getData();
		boolean isExist = data.has(DATA_KEY);
		if (!isExist)
		{
			data.put(DATA_KEY, new JSONObject());
		}
		JSONObject single = data.getJSONObject(DATA_KEY);
		single.put(key, value);
		data.put(DATA_KEY, single);
		DataManage.putData(data);
	}

	public static boolean getBoolean(String key)
	{
		return getBoolean(key, false);
	}

	public static boolean getBoolean(String key, boolean def)
	{
		return DataManage.getData().optJSONObject(DATA_KEY, new JSONObject()).optBoolean(key, def);
	}

	public static String getString(String key)
	{
		return getString(key, "");
	}

	public static String getString(String key, String def)
	{
		return DataManage.getData().optJSONObject(DATA_KEY, new JSONObject()).optString(key, def);
	}
}
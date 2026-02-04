package com.github.sky_rebel.bot.msg;

import com.github.sky_rebel.bot.msg.element.OB11AtMsgElement;
import com.github.sky_rebel.bot.msg.element.OB11MsgElement;
import com.github.sky_rebel.bot.msg.element.OB11TextMsgElement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OB11MessageSegment
{
	private static final Logger LOGGER = LoggerFactory.getLogger(OB11MessageSegment.class);

	public static List<OB11MsgElement> getMessageElementArray(JSONArray messageArray)
	{
		if (messageArray == null || messageArray.isEmpty())
		{
			LOGGER.error("无法解析为消息元素数组，消息段为空！");
			return null;
		}
		List<OB11MsgElement> ob11MsgElementList = new ArrayList<>();
		messageArray.forEach(messageObject ->
		{
			JSONObject messageObjectJson = (JSONObject) messageObject;
			OB11MsgElement.ElementType elementType = OB11MsgElement.ElementType.valueOf(messageObjectJson.getString("type").toUpperCase());
			JSONObject dataJson = messageObjectJson.getJSONObject("data");
			switch (elementType)
			{
				case AT ->
				{
					OB11AtMsgElement ob11AtMsgElement = new OB11AtMsgElement();
					ob11AtMsgElement.setQQ(dataJson.getString("qq"));
					ob11MsgElementList.add(ob11AtMsgElement);
				}
				case TEXT ->
				{
					OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
					ob11TextMsgElement.setText(dataJson.getString("text"));
					ob11MsgElementList.add(ob11TextMsgElement);
				}
			}
		});
		return ob11MsgElementList;
	}

	public static List<Long> getAtList(List<OB11MsgElement> ob11MsgElementList)
	{
		List<Long> atList = new ArrayList<>();
		ob11MsgElementList.forEach(messageElementObject ->
		{
			OB11MsgElement.ElementType elementType = messageElementObject.getElementType();
			if (elementType.equals(OB11MsgElement.ElementType.JSON))
			{
				OB11AtMsgElement ob11AtMsgElement = (OB11AtMsgElement) messageElementObject;
				if (!ob11AtMsgElement.getQQ().equals("all")) atList.add(Long.valueOf(ob11AtMsgElement.getQQ()));
			}
		});
		return atList;
	}
}

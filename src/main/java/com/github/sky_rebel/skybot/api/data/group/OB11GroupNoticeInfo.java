package com.github.sky_rebel.skybot.api.data.group;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class OB11GroupNoticeInfo
{
	public long senderId;

	public Message message;

	public String noticeId;

	public long publishTime;

	public static class Message
	{
		public String text;

		public Image[] image;

		@Override
		public String toString()
		{
			return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
				.add("text='" + text + "'")
				.add("image=" + Arrays.toString(image))
				.toString();
		}

		public static class Image
		{
			public String id;

			public int height;

			public int width;

			@Override
			public String toString()
			{
				return new StringJoiner(", ", Image.class.getSimpleName() + "[", "]")
					.add("id='" + id + "'")
					.add("height=" + height)
					.add("width=" + width)
					.toString();
			}
		}
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupNoticeInfo.class.getSimpleName() + "[", "]")
			.add("noticeId=" + noticeId)
			.add("senderId=" + senderId)
			.add("message=" + message.toString())
			.add("publishTime=" + publishTime)
			.toString();
	}

	public static OB11GroupNoticeInfo getInstance(JSONObject data)
	{
		OB11GroupNoticeInfo ob11GroupNoticeInfo = new OB11GroupNoticeInfo();
		ob11GroupNoticeInfo.noticeId = data.getString("notice_id");
		ob11GroupNoticeInfo.senderId = data.getLong("sender_id");
		ob11GroupNoticeInfo.publishTime = data.getLong("publish_time");
		JSONObject messageData = data.getJSONObject("message");
		if (messageData != null)
		{
			OB11GroupNoticeInfo.Message message = new Message();
			message.text = messageData.optString("text");
			JSONArray imageMessageData = messageData.optJSONArray("image");
			if (imageMessageData != null)
			{
				List<Message.Image> imageList = new ArrayList<>();
				imageMessageData.forEach(imageObject ->
				{
					if (imageObject instanceof JSONObject imageJson)
					{
						Message.Image image = new Message.Image();
						image.id = imageJson.getString("id");
						image.width = imageJson.getInt("width");
						image.height = imageJson.getInt("height");
						imageList.add(image);
					}
				});
				message.image = imageList.toArray(new Message.Image[0]);
			}
			ob11GroupNoticeInfo.message = message;
		}
		return ob11GroupNoticeInfo;
	}
}
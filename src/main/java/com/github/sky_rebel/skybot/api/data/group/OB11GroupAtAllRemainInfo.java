package com.github.sky_rebel.skybot.api.data.group;

import org.json.JSONObject;

import java.util.StringJoiner;

public class OB11GroupAtAllRemainInfo
{
	public boolean canAtAll;

	public int remainAtAllCountForGroup;

	public int remainAtAllCountForUin;

	public static OB11GroupAtAllRemainInfo getInstance(JSONObject data)
	{
		OB11GroupAtAllRemainInfo ob11GroupAtAllInfo = new OB11GroupAtAllRemainInfo();
		ob11GroupAtAllInfo.canAtAll = data.getBoolean("can_at_all");
		ob11GroupAtAllInfo.remainAtAllCountForGroup = data.getInt("remain_at_all_count_for_group");
		ob11GroupAtAllInfo.remainAtAllCountForUin = data.getInt("remain_at_all_count_for_uin");
		return ob11GroupAtAllInfo;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupAtAllRemainInfo.class.getSimpleName() + "[", "]")
			.add("canAtAll=" + canAtAll)
			.add("remainAtAllCountForGroup=" + remainAtAllCountForGroup)
			.add("remainAtAllCountForUin=" + remainAtAllCountForUin)
			.toString();
	}
}
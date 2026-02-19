package com.github.sky_rebel.skybot.api.data.group;

import org.json.JSONObject;

import java.util.StringJoiner;

public class OB11GroupExInfo
{
	public long ownerUin;

	public String ownerUid;

	public String ownerQid;

	public static OB11GroupExInfo getInstance(JSONObject data)
	{
		JSONObject extInfo = data.getJSONObject("extInfo");
		JSONObject groupOwnerId = extInfo.getJSONObject("groupOwnerId");
		OB11GroupExInfo ob11GroupExInfo = new OB11GroupExInfo();
		ob11GroupExInfo.ownerUin = groupOwnerId.getLong("memberUin");
		ob11GroupExInfo.ownerUid = groupOwnerId.getString("memberUid");
		ob11GroupExInfo.ownerQid = groupOwnerId.getString("memberQid");
		return ob11GroupExInfo;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupExInfo.class.getSimpleName() + "[", "]")
			.add("ownerUin=" + ownerUin)
			.add("ownerUid='" + ownerUid + "'")
			.add("ownerQid='" + ownerQid + "'")
			.toString();
	}
}
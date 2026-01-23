package com.skybot.event.notice;

import com.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupUploadNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public long groupId;

	public GroupUploadFile groupUploadFile;

	public static class GroupUploadFile
	{
		public String id;

		public String name;

		public long size;

		public long busid;
	}
}
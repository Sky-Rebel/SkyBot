package com.skybot.event.notice;

import com.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupMsgEmojiLikeNoticeEvent extends OB11BaseNoticeEvent
{
	public class MsgEmojiLike
	{
		public String emojiId;

		public int count;
	}

	public long messageId;

	public MsgEmojiLike[] msgEmojiLikes;
}
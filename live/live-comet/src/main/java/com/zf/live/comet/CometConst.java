package com.zf.live.comet;

public interface CometConst {

	public static interface Channel{
		
		/**
		 * 发送公聊消息频道
		 */
		public static final String chatSendPubChannel = "/chat/send_pub";
		
		/**
		 * 接收公聊消息频道
		 */
		public static final String chatRcvPubChannel = "/chat/rcv_pub";
		
	}
	
}

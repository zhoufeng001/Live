package com.zf.live.robot.test;

import org.slf4j.Logger;

import com.zf.live.robot.Params;
import com.zf.live.robot.WebSocketService;

public class ChatTest {
	
	static final Logger log = org.slf4j.LoggerFactory.getLogger(ChatTest.class);

	public static void main(String[] args) {
		WebSocketService webSocketService = new WebSocketService() ;
		Params params = new Params() ;
		params.setActionInterval(3000);
		params.setPassword("111111");
		params.setUserPrefix("zzzz"); 
		params.setUserFrom(1); 
		params.setUserTo(300); 
		params.setVideoId("6699158fad0611e4ba363417ebbccb7e016"); 
		webSocketService.joinRoom(params); 
	}
	
}

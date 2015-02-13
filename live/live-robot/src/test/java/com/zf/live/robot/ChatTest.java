package com.zf.live.robot;

import org.junit.Test;
import org.slf4j.Logger;

public class ChatTest {
	
	static final Logger log = org.slf4j.LoggerFactory.getLogger(ChatTest.class);

	
	@Test
	public void testChat(){
		WebSocketService webSocketService = new WebSocketService() ;
		Params params = new Params() ;
		params.setActionInterval(3000);
		params.setPassword("111111");
		params.setUserPrefix("zzzz"); 
		params.setUserFrom(1); 
		params.setUserTo(2); 
		params.setVideoId("6699158fad0611e4ba363417ebbccb7e016"); 
		webSocketService.joinRoom(params); 
	}
	
}

package com.zf.live.robot;

import org.junit.Test;
import org.slf4j.Logger;

public class LoginTest {
	
	static final Logger log = org.slf4j.LoggerFactory.getLogger(LoginTest.class);
	
	@Test
	public void testLogin(){
		String token = HttpUtil.doLogin("A12345", "111111") ;
		log.info(token); 
	}

}

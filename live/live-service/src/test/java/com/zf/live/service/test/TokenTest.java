package com.zf.live.service.test;

import org.junit.Test;

import com.zf.live.service.impl.util.TokenFactory;

public class TokenTest {

	@Test
	public void testGenerator(){
		
		System.out.println(TokenFactory.newToken());
		
	}
	
}

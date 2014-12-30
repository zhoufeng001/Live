package com.zf.live.web.test;

import org.junit.Test;

import com.zf.live.common.util.DesSecureFactory;
import com.zf.live.common.util.DesSecureFactory.DesSecure;

public class DesTest {
	
	String key = "is_zhoufeng";
	DesSecure desSecure = DesSecureFactory.newInstance(key);
	
	@Test
	public void encryptTest(){
		String source = String.valueOf(System.currentTimeMillis());
		String result = desSecure.encrypt(source);
		System.out.println(result);
	}
	
	@Test
	public void decryptTest(){
		String dec = "zenDqKx4tF3LbrXP9BiKsA==";
		String result = desSecure.decrypt(dec); 
		System.out.println(result);
	}
}

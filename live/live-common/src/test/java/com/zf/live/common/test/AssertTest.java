package com.zf.live.common.test;

import org.junit.Test;

import com.zf.live.common.assertx.ZFAssert;
import com.zf.live.dao.pojo.Lvuser;

public class AssertTest {
	
	@Test
	public void testNotNull(){
		ZFAssert.notNull(null, "用户名不能为空");  
	}
	
	@Test
	public void testFieldNotNull(){
		Lvuser user = new Lvuser() ;
		user.setLoginname("aa");
		ZFAssert.fieldNotNull(user, "loginname", null);
	}

}

package com.zf.live.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zf.live.client.user.LvuserService;
import com.zf.live.dao.pojo.Lvuser;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午5:25:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"classpath*:test-service-beans.xml"}		
		)
public class ValidateTest {
	
	@Autowired
	private LvuserService lvuserService ;
	
	/**
	 * 
	 */
	@Test
	public void test01(){
		Lvuser user = new Lvuser() ;
		user.setLoginname("C12345");  
		user.setNick("xfdwa");
		user.setPassword("is_zhoufeng");  
		user.setIdxcode("50"); 
		lvuserService.regist(user) ;
	}

}

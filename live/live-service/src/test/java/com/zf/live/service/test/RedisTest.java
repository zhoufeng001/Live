package com.zf.live.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月17日 下午11:14:03
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"classpath*:service-beans.xml"}		
)
public class RedisTest {

	@Autowired
	private RedisTemplate<String, String> redisService ;
	
	@Test
	public void testConnect(){
		System.out.println(redisService);  
	}
	
}

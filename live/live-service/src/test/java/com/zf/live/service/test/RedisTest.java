package com.zf.live.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
    private RedisTemplate<String, String> stringTemplate ;
    
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps ;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOps ;
    
    @Test
    public void testString(){
	
	
	String result = stringTemplate.execute(new RedisCallback<String>() {
	    @Override
	    public String doInRedis(RedisConnection connection)
		    throws DataAccessException { 
		connection.set(stringTemplate.getStringSerializer().serialize("name"), 
			stringTemplate.getStringSerializer().serialize("is_zhoufeng")); 
		return "ok";
	    }
	});
	System.out.println("OK");
    }
    
    @Test
    public void testSet(){
	long result = listOps.leftPush("list", "b");
	System.out.println(result);
    }

    @Test
    public void testGet(){
	List<String> ls = listOps.range("list", 0, -1); 
	if(ls != null){
	    for (String string : ls) {
		System.out.println(string);
	    } 
	}
    }
    
    @Test
    public void testSetValueOpt(){
	valueOps.set("fff", "is_zhoufengx"); 
    }
    
    @Test
    public void testGetValueOpt(){
	System.out.println(valueOps.get("fff"));
    }
    
    
}

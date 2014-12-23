package com.zf.live.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.user.CacheUser;
import com.zf.live.service.impl.cache.user.UserCacheService;

public class LvuserServiceTest extends ServiceBaseTester {

	@Autowired
	private LvuserService lvuserService ;
	
	@Autowired
	UserCacheService userCacheService;
	
	@Test
	public void testLogin(){
		
		ServiceResult<String> result =lvuserService.login4Platform("C12345", "is_zhoufeng");
		if(result.isSuccess()){
			System.out.println("成功");
		}else{
			System.out.println(result.getErrMssage());
		}
	}
	
	@Test
	public void testGet(){
		
		CacheUser cacheUser = userCacheService.getCacheUserById(11L);
		if(cacheUser == null){
			System.out.println("空。。。");
		}else{
			System.out.println(cacheUser);
		}
		
	}
	
}

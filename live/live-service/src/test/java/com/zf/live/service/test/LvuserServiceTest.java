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
		
		CacheUser cacheUser = userCacheService.getCacheUserById(3L);
		if(cacheUser == null){
			System.out.println("空。。。");
		}else{
			System.out.println(cacheUser);
		}
		
	}
	
	@Test 
	public void testGetTokens(){
		 String token = userCacheService.getTokenByUserId(3L);
		 System.out.println(token);
	}
	
	@Test
	public void testRemove(){
		userCacheService.removeLoginUserInfo(3L);
		System.out.println("Ok.");
	}
	
	@Test
	public void testGetUserByToken(){
		Long userid = null ;
		userid = userCacheService.getUserIdByToken("a4e1b73d2a444c2bbb02f6512a638295");
		System.out.println(userid);
		userid = userCacheService.getUserIdByToken("4049ad434b7a4bdb966035ce162abbb9");
		System.out.println(userid);
		userid = userCacheService.getUserIdByToken("0290c4daafd442129dad83287ad7a233");
		System.out.println(userid);
	}

	
}

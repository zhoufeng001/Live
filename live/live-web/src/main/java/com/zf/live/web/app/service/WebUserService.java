package com.zf.live.web.app.service;

import org.slf4j.Logger;

import com.zf.live.dao.pojo.Lvuser;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月26日 下午1:55:45
 */
public class WebUserService {
	
	static final Logger log = org.slf4j.LoggerFactory.getLogger(WebUserService.class);


	private static final ThreadLocal<Lvuser> currentUser = new ThreadLocal<Lvuser>() ;
	
	/**
	 * 设置当前登录用户
	 * @param lvuser
	 */
	public static void setCurrentUser(Lvuser lvuser){
		currentUser.set(lvuser);
	}
	
	/**
	 * 获取当前登录的用户
	 * @param lvuser
	 */
	public static Lvuser getCurrentUser(){
		return currentUser.get() ;
	}
	
	/**
	 * 清楚当前登录用户信息
	 */
	public static void clearCurrentUser(){
		currentUser.remove(); 
	}
	
	
}

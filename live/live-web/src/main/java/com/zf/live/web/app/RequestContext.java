package com.zf.live.web.app;

import org.slf4j.Logger;

import com.zf.live.dao.pojo.Lvuser;

/**
 * 请求上下文
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月28日 下午11:26:48
 */
public class RequestContext {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(RequestContext.class);

	/**
	 * 保存当前登录用户信息
	 */
	private static final ThreadLocal<Lvuser> currentUser = new ThreadLocal<Lvuser>() ;

	/**
	 * 保存错误提示消息
	 */
	private static final ThreadLocal<String> errTipMessage = new ThreadLocal<String>() ;

	/**
	 * 保存成功提示消息
	 */
	private static final ThreadLocal<String> successTipMessage = new ThreadLocal<String>();

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
	 * 清除当前登录用户信息
	 */
	public static void clearCurrentUser(){
		currentUser.remove(); 
	}

	public static String getErrTipMessage(){
		return errTipMessage.get() ;
	}

	public static void setErrTipMessage(String message){
		errTipMessage.set(message);
	}

	public static void clearErrTipMessage(){
		errTipMessage.remove(); 
	}

	public static void setSuccessTipMessage(String message){
		successTipMessage.set(message);
	}

	public static String getSuccessTipMessage(){
		return successTipMessage.get() ;
	}

	public static void clearSuccessTipMessage(){
		successTipMessage.remove() ;
	}


}

package com.zf.live.web.app.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.user.LvuserService;
import com.zf.live.common.util.DesSecureFactory;
import com.zf.live.common.util.DesSecureFactory.DesSecure;
import com.zf.live.dao.pojo.Lvuser;

/**
 * Token相关工具类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月25日 下午11:45:33
 */
@Component("webTokenUtil")
public class WebTokenUtil {

	static final Logger log = LoggerFactory.getLogger(WebTokenUtil.class);

	
	/**
	 * 存放在浏览器的cookie名称
	 */
	public static final String TOKEN_COOKIE_KEY = "_tc_k_";
	
	/**
	 * cookie中的用户id
	 */
	public static final String COOKIE_USER_ID = "_c_uid_";
	
	/**
	 * cookie中的用户昵称
	 */
	public static final String COOKIE_USER_NICK = "_c_unick_";
	
	/**
	 * cookie上次被使用时间(加密)
	 */
	public static final String TOKEN_COOKIE_LAST_TIME_KEY = "_tc_lt_k_";

	/**
	 * 加密时间戳密钥
	 */
	private static final String TCLT_DES_KEY = "is_zhoufeng_tc_lt_";

	/**
	 * 加密时间戳工具
	 */
	private static final DesSecure desSecure = DesSecureFactory.newInstance(TCLT_DES_KEY);

	/**
	 * Token有效时间（7天）
	 */
	private static final Integer TOKEN_MAX_AGE = (( 60 * 60 * 1000 ) * 24) * 7 ;

	/**
	 * Cookie Path
	 */
	private static final String COOKIE_PATH = "/";
	
	@Autowired
	private LvuserService lvuserService;

	/**
	 * 在浏览器创建包含token的cookie
	 * @param request
	 * @param response
	 * @param token
	 */
	public void createTokenCookie(HttpServletRequest request , HttpServletResponse response , String token , Lvuser lvuser){

		//如果之前的用户信息还存在，则先删除之前的登录信息
		deleteTokenCookiee(request, response); 

		//添加cookie到浏览器
		{
			Cookie tokenCookie = new Cookie(TOKEN_COOKIE_KEY, token);  
			tokenCookie.setMaxAge(TOKEN_MAX_AGE);
			tokenCookie.setPath(COOKIE_PATH);
			
			Cookie useridCookie = new Cookie(COOKIE_USER_ID, String.valueOf(lvuser.getId()));  
			useridCookie.setMaxAge(TOKEN_MAX_AGE);
			useridCookie.setPath(COOKIE_PATH);
			
			Cookie usernickCookie = new Cookie(COOKIE_USER_NICK, lvuser.getNick());  
			usernickCookie.setMaxAge(TOKEN_MAX_AGE);
			usernickCookie.setPath(COOKIE_PATH);
			
			response.addCookie(tokenCookie);
			response.addCookie(useridCookie);
			response.addCookie(usernickCookie); 
//			setLastTimeToken(response); 
		}


	}

	/**
	 * 删除浏览器包含token的cookie，并从Redis缓存中移除用户的登录信息
	 * @param request
	 * @param response
	 */
	public  void deleteTokenCookiee(HttpServletRequest request , HttpServletResponse response){
		Map<String, String> cookies = getCookies(request , response);
		String tokenValue = cookies.get(TOKEN_COOKIE_KEY) ;
//		String lastTimeValue = cookies.get(TOKEN_COOKIE_LAST_TIME_KEY) ;
		String userIdValue = cookies.get(COOKIE_USER_ID) ;
		String userNickValue = cookies.get(COOKIE_USER_NICK) ;
		if(StringUtils.isNotBlank(tokenValue)){
			Cookie tokenCookie = new Cookie(TOKEN_COOKIE_KEY , tokenValue);
			tokenCookie.setMaxAge(0);
			tokenCookie.setPath(COOKIE_PATH);
			response.addCookie(tokenCookie); 
		}
		/*if(StringUtils.isNotBlank(lastTimeValue)){
			Cookie lastTimeCookie = new Cookie(TOKEN_COOKIE_LAST_TIME_KEY , tokenValue);
			lastTimeCookie.setPath(COOKIE_PATH);
			response.addCookie(lastTimeCookie); 
		}*/
		if(StringUtils.isNotBlank(userIdValue)){
			Cookie userIdCookie = new Cookie(COOKIE_USER_ID , userIdValue);
			userIdCookie.setMaxAge(0);
			userIdCookie.setPath(COOKIE_PATH);
			response.addCookie(userIdCookie); 
		}
		if(StringUtils.isNotBlank(userNickValue)){
			Cookie userNickCookie = new Cookie(COOKIE_USER_NICK , userIdValue);
			userNickCookie.setMaxAge(0);
			userNickCookie.setPath(COOKIE_USER_NICK);
			response.addCookie(userNickCookie); 
		}
		
	}

	/**
	 * 从请求的Cookie中获取Token
	 * @param request
	 * @param response
	 * @return
	 */
	public String getTokenFromCookie(HttpServletRequest request , HttpServletResponse response){
		Map<String, String> cookies = getCookies(request , response);
		String tokenValue = cookies.get(TOKEN_COOKIE_KEY) ;
		/*
		String lastTimeValue = cookies.get(TOKEN_COOKIE_LAST_TIME_KEY) ;
		
		if(StringUtils.isBlank(tokenValue) || StringUtils.isBlank(lastTimeValue)){
			return null;
		}
		Long prevTimeValue = Long.valueOf(lastTimeValue) ;
		Long currentTime = System.currentTimeMillis();
		if((currentTime - prevTimeValue ) > TOKEN_MAX_AGE){
			deleteTokenCookiee(request, response); 
			return null ;
		}*/
		return tokenValue ;
	}
	
	/**
	 * 从请求中获取cookie存入map，以cookie名称当作key，值当作value
	 * @param request
	 * @param response
	 * @return
	 */
	private Map<String, String> getCookies(HttpServletRequest request , HttpServletResponse response){
		Map<String , String> cookiesMap = new HashMap<String, String>() ;
		Cookie[] cookies = request.getCookies() ;
		if(cookies == null || cookies.length == 0){
			return cookiesMap;
		}
		for (Cookie cookie : cookies) {
			cookiesMap.put(cookie.getName(), cookie.getValue()) ;
		}
//		setLastTimeToken(response); 
		return cookiesMap ;
	}
	
	/**
	 * 添加最后访问时间到cookie
	 * @param response
	 */
	private void setLastTimeToken(HttpServletResponse response){
		Cookie lastTimeCookie = null ;
		lastTimeCookie = new Cookie(TOKEN_COOKIE_LAST_TIME_KEY, String.valueOf(System.currentTimeMillis()));
		lastTimeCookie.setPath(COOKIE_PATH);
		response.addCookie(lastTimeCookie);
	}


}

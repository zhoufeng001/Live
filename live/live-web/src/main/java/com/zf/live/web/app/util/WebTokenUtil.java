package com.zf.live.web.app.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.zf.live.web.app.util.DesSecureFactory.DesSecure;

/**
 * Token相关工具类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月25日 下午11:45:33
 */
@Component("webTokenUtil")
public class WebTokenUtil {

	/**
	 * 存放在浏览器的cookie名称
	 */
	public static final String TOKEN_COOKIE_KEY = "_tc_k_";

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
	 * Token两小时不使用则自动失效
	 */
	private static final Integer TOKEN_MAX_AGE = 7200 ;

	/**
	 * Cookie Path
	 */
	private static final String COOKIE_PATH = "/";

	/**
	 * 在浏览器创建包含token的cookie
	 * @param request
	 * @param response
	 * @param token
	 */
	public void createTokenCookie(HttpServletRequest request , HttpServletResponse response , String token){

		Cookie lastTimeCookie = null ;
		Cookie tokenCookie = null ;

		//如果之前的用户信息还存在，则先删除之前的登录信息
		deleteTokenCookiee(request, response); 

		//添加cookie到浏览器
		{
			tokenCookie = new Cookie(TOKEN_COOKIE_KEY, token);  
			tokenCookie.setMaxAge(TOKEN_MAX_AGE);
			tokenCookie.setDomain(COOKIE_PATH);

			lastTimeCookie = new Cookie(TOKEN_COOKIE_LAST_TIME_KEY, "123");
			lastTimeCookie.setDomain(COOKIE_PATH);

			response.addCookie(tokenCookie);
			response.addCookie(lastTimeCookie);
		}


	}

	/**
	 * 删除浏览器包含token的cookie
	 * @param request
	 * @param response
	 */
	public  void deleteTokenCookiee(ServletRequest request , ServletResponse response){

	}



}

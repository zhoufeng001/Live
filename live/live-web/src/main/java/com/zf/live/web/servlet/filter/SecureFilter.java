package com.zf.live.web.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zf.live.client.user.LvuserService;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.web.app.service.WebUserService;
import com.zf.live.web.app.util.WebTokenUtil;

/**
 * 获取用户信息，并设置到
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月26日 下午1:36:33
 */
public class SecureFilter implements Filter{
	
	static final Logger log = LoggerFactory.getLogger(SecureFilter.class);

	private LvuserService lvuserService ;

	private WebTokenUtil webTokenUtil ;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(filterConfig.getServletContext()) ;
		if(applicationContext == null){
			log.error("不能找到applicationContext，初始化SecureFilter失败");
			return ;
		}
		lvuserService = applicationContext.getBean(LvuserService.class);
		webTokenUtil = applicationContext.getBean(WebTokenUtil.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			//如果使用线程池，当前线程可能会残留之前用户的信息，所以需要先清除
			WebUserService.clearCurrentUser();
			String token = webTokenUtil.getTokenFromCookie((HttpServletRequest)request, (HttpServletResponse)response);
			if(StringUtils.isBlank(token)){
				return ;
			}
			Lvuser currentUser = lvuserService.getUserByToken(token) ;
			if(currentUser == null){ //如果用户失效，则清除cookie信息
				webTokenUtil.deleteTokenCookiee((HttpServletRequest)request, (HttpServletResponse)response);
			}else{
				WebUserService.setCurrentUser(currentUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			chain.doFilter(request, response); 
		}
		
	}

	@Override
	public void destroy() {

	}

}

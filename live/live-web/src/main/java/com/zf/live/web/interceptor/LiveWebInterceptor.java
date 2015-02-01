package com.zf.live.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zf.live.web.app.RequestContext;

/**
 * 对所有请求拦截处理
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月28日 下午10:45:50
 */
public class LiveWebInterceptor implements HandlerInterceptor {

	static final Logger log = LoggerFactory.getLogger(LiveWebInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
					throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mav) throws Exception {
		//将当前登录用户放入request
		if(mav != null){
			mav.addObject("user", RequestContext.getCurrentUser()) ;
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		return true;
	}

}

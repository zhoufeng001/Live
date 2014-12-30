package com.zf.live.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zf.live.web.app.RequestContext;

/**
 * 暴露Beans给freemarker使用
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月29日 上午1:03:08
 */
public class ExportStaticBeansInterceptor implements HandlerInterceptor{

	static final Logger log = LoggerFactory.getLogger(ExportStaticBeansInterceptor.class);


	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
					throws Exception {
		log.info("ExportStaticBeansInterceptor.afterCompletion()");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mav) throws Exception {

		String errorMessage = RequestContext.getErrTipMessage() ;
		String successMessage = RequestContext.getSuccessTipMessage() ;
		
		request.setAttribute("errMsg", errorMessage);
		request.setAttribute("sucMsg", successMessage);
		log.info("ExportStaticBeansInterceptor.postHandle()");

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		log.info("ExportStaticBeansInterceptor.preHandle()");

		return true;
	}


}

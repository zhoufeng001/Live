package com.zf.live.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zf.live.web.app.RequestContext;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModelException;

/**
 * 暴露Beans给freemarker使用
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月29日 上午1:03:08
 */
public class ExportStaticBeansInterceptor implements HandlerInterceptor{

	static final Logger log = LoggerFactory.getLogger(ExportStaticBeansInterceptor.class);


	private static Class<?>[] exportClasses = new Class<?>[]{
		RequestContext.class
	};


	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
					throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mav) throws Exception {

		if(exportClasses == null || exportClasses.length == 0){
			return ;
		}
		for (Class<?> clz : exportClasses) {  
			String name = clz.getSimpleName();  
			mav.getModelMap().addAttribute(name, getStaticModel(clz));  
		}  

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		return true;
	}


	@SuppressWarnings("deprecation")
	private static Object getStaticModel(Class<?> clz) {  
		BeansWrapper wrapper = BeansWrapper.getDefaultInstance(); 
		try {  
			return wrapper.getStaticModels().get(clz.getName());  
		} catch (TemplateModelException e) {  
			log.error(e.getMessage());  
		}  
		return null;  
	}  

}

package com.zf.live.web.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zf.live.web.app.RequestContext;
import com.zf.live.web.app.service.LiveWebUtil;

/**
 * 请求开始时清除RequestContext中的tipMessage消息
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月29日 上午12:40:24
 */
public class TipMessageFilter implements Filter{

	static final Logger log = LoggerFactory.getLogger(TipMessageFilter.class);

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			String sysRedirectKey = ((HttpServletRequest)request).getParameter(LiveWebUtil.SYSTEM_REDIRECT_FLAG_KEY) ;
			if(LiveWebUtil.SYSTEM_REDIRECT_FLAG_VALUE.equals(sysRedirectKey)){
				//系统重定向，不清除消息
			}else{
				RequestContext.clearSuccessTipMessage();
				RequestContext.clearErrTipMessage(); 
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}finally{
			chain.doFilter(request, response); 
		}
	}


}

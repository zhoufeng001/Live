package com.zf.live.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zf.live.web.app.RequestContext;
import com.zf.live.web.app.service.LiveWebUtil;

/**
 * 清除RequestContext中的message信息
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月31日 上午12:39:37
 */
public class ClearContextMessageListener implements ServletRequestListener{
	
	static final Logger log = LoggerFactory.getLogger(ClearContextMessageListener.class);
	

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		log.info("ClearContextMessageListener.requestDestroyed()");
		String sysRedirectKey = ((HttpServletRequest)event.getServletRequest()).getParameter(LiveWebUtil.SYSTEM_REDIRECT_FLAG_KEY) ;
		if(LiveWebUtil.SYSTEM_REDIRECT_FLAG_VALUE.equals(sysRedirectKey)){
			//系统重定向，不清除消息
		}else{
			RequestContext.clearSuccessTipMessage();
			RequestContext.clearErrTipMessage(); 
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		log.info("ClearContextMessageListener.requestInitialized()");
	}

	
	
}

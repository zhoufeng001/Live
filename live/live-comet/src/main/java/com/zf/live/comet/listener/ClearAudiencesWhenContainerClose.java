package com.zf.live.comet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zf.live.comet.service.room.AudienceContainerManager;

/**
 * 容器关闭时清除观众列表
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月24日 下午1:12:27
 */
public class ClearAudiencesWhenContainerClose implements ServletContextListener{

	static final Logger log = LoggerFactory.getLogger(ClearAudiencesWhenContainerClose.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//清除本机器上面的观众列表
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		AudienceContainerManager audienceContainerManager = context.getBean(AudienceContainerManager.class);
		audienceContainerManager.clearAllAudience();
	}

	@Override
	public void contextInitialized(ServletContextEvent envet) {
		
	}

}

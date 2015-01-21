package com.zf.live.comet.service;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletContext;

import org.cometd.annotation.Configure;
import org.cometd.annotation.Service;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.springframework.web.context.ServletContextAware;

import com.zf.live.comet.CometConst.Channel;

@Named
@Singleton
@Service("rcvChatListener")
public class RcvChatListener implements ServletContextAware {

	private ServletContext servletContext ;

	@Configure(Channel.chatRcvPubChannel)
	public void configure(ConfigurableServerChannel channel)
	{
		
	}


	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext ;
	}



}

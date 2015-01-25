package com.zf.live.comet.service.chat;

import javax.inject.Named;
import javax.inject.Singleton;

import org.cometd.annotation.Configure;
import org.cometd.annotation.Service;
import org.cometd.bayeux.server.ConfigurableServerChannel;

import com.zf.live.comet.CometConst.Channel;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月25日 上午1:55:00
 */
@Named
@Singleton
@Service("rcvChatListener")
public class RcvChatListener {

	@Configure(Channel.chatRcvPubChannel + "/{videoId}")
	public void configure(ConfigurableServerChannel channel)
	{
		
	}


}

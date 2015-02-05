package com.zf.live.comet.service.chat;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerMessage.Mutable;
import org.cometd.bayeux.server.ServerSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.vo.room.Audience;
import com.zf.live.comet.CometConst.Channel;

/**
 * 订阅用户进入房间后的消息，然后群发到房间内所有人
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月27日 上午12:02:08
 */
@Named
@Singleton
@Service("comeInRoomListener")
public class ComeInRoomListener {
	
	@Inject
	private BayeuxServer bayeux;
	
	@Session
	private ServerSession serverSession;
	
	@Session
	private LocalSession localSession ;
	
	@Autowired
	private AudienceChangeService audienceChangeService; 
	
	
	/**
     * 监听公聊消息
     * @param remote
     * @param message
     */
	@Listener(Channel.comeInRoomChannel) 
	public void chatPub(ServerSession remote, ServerMessage message)
	{
		Audience audience = (Audience) remote.getAttribute("audience");
		if(audience == null){
			Mutable mutable = message.getAssociated();
			mutable.setSuccessful(false);
			mutable.setData("没有权限！");
			return ;
		}
		String videoId = audience.getVideoId() ;
		
		//发送通知到房间
		audienceChangeService.sendAudienceChangeNotice(videoId, audience, 
				AudienceChangeService.TYPE_COMEIN); 
		
	}

}

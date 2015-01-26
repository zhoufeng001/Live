package com.zf.live.comet;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.cometd.annotation.Service;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.SecurityPolicy;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerMessage.Mutable;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.oort.Oort;
import org.cometd.oort.Seti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.room.Audience;
import com.zf.live.comet.service.chat.AudienceChangeService;
import com.zf.live.comet.service.room.AudienceContainerManager;
import com.zf.live.common.util.UUID;
import com.zf.live.dao.pojo.Lvuser;

/**
 * 握手权限校验，以及session管理
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月24日 下午2:47:54
 */
@Component("authorcation")
@Service
public class Authorcation implements SecurityPolicy , ServletContextAware{

	static final Logger log = LoggerFactory.getLogger(Authorcation.class);

	private ServletContext servletContext ;
	
	@Autowired
	private AudienceContainerManager audienceContainerManager ;
	
	@Resource(name="lvuserService")
	private LvuserService lvuserService ;
	
	@Override
	public boolean canHandshake(BayeuxServer server, ServerSession session,
			ServerMessage message) {
		if(session.isLocalSession()	){
			log.info("local session handshake success!");
			return true ;
		}
		
		Oort oort = (Oort)servletContext.getAttribute(Oort.OORT_ATTRIBUTE) ;
		
		if(oort.isOortHandshake(message)){  
			log.info("oort handshake[{}]" , message); 
			return true ;
		}

		Map<String, Object> ext =  message.getExt();
		Object token = ext.get("token");
		Object videoId = ext.get("videoId");
		if(videoId == null){
			Mutable replay = message.getAssociated();
			replay.setSuccessful(false);
			replay.setData("videoId不能为空");
			return false ;
		}
		Audience audience = new Audience() ;
		audience.setVideoId((long)videoId);  
		audience.setSessionId(session.getId());
		audience.setUuid(UUID.newUUID());
		audience.setComeInTime(System.currentTimeMillis());
		if(token == null){
			audience.setTourist(true);
		}else{
			String tokenValue = token.toString() ;
			audience.setTourist(false);
			Lvuser lvuser = lvuserService.getUserByToken(tokenValue);
			if(lvuser == null){
				Mutable replay = message.getAssociated();
				replay.setSuccessful(false);
				replay.setData("用户未登录");
				return false ;
			}
			audience.setUserId(lvuser.getId()); 
			audience.setUserNick(lvuser.getNick());
			audience.setUserPhoto(lvuser.getPhoto());
			
			Seti seti = (Seti)servletContext.getAttribute(Seti.SETI_ATTRIBUTE) ;
			seti.associate(String.valueOf(lvuser.getId()), session) ;  
		}
		session.setAttribute("audience", audience); 
		audienceContainerManager.addAudience((long)videoId, audience);
		
		AudienceChangeService audienceChangeService = WebApplicationContextUtils
				.getWebApplicationContext(servletContext).getBean(AudienceChangeService.class);
		
		//监听移除事件
		session.addListener(new ServerSession.RemoveListener(){
			@Override
			public void removed(ServerSession session, boolean timeout) {
				audienceContainerManager.removeAudience((long)videoId, session.getId()); 
				//发送通知到房间
				audienceChangeService.sendAudienceChangeNotice((long)videoId, audience, AudienceChangeService.TYPE_GOOUT); 
			}
			
		}); 

		log.info("session[{}] handshake success!",session.getId()); 

		return true;
	}

	@Override
	public boolean canCreate(BayeuxServer server, ServerSession session,
			String channelId, ServerMessage message) {
		return true;
	}

	@Override
	public boolean canSubscribe(BayeuxServer server, ServerSession session,
			ServerChannel channel, ServerMessage message) {
		return true;
	}

	@Override
	public boolean canPublish(BayeuxServer server, ServerSession session,
			ServerChannel channel, ServerMessage message) {
		return true;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext ;
	}

}

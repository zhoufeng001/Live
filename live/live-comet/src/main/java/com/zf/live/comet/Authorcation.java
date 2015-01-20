package com.zf.live.comet;


import java.util.Map;

import javax.servlet.ServletContext;

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

import com.zf.live.client.room.RoomService;
import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.room.Audience;
import com.zf.live.dao.pojo.Lvuser;

public class Authorcation implements SecurityPolicy{

	static final Logger log = LoggerFactory.getLogger(Authorcation.class);

	private ServletContext servletContext ;

	private RoomService roomService ;
	
	private LvuserService lvuserService ;

	public Authorcation(ServletContext servletContext, RoomService roomService, LvuserService lvuserService ) {
		this.servletContext = servletContext ;
		this.roomService = roomService ;
		this.lvuserService = lvuserService ;
	}

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
		audience.setSessionId(session.getId());
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
		}
		roomService.comeInRoom((long)videoId, audience);  

		Seti seti = (Seti)servletContext.getAttribute(Seti.SETI_ATTRIBUTE) ;


		/*
		Object obj = message.getExt().get("user") ;
		if(obj == null){
			return false ;
		}

		seti.associate(obj.toString(), session) ;

		System.out.println("seti --- " + seti);*/

		log.info("session handshake success!");

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
}

package com.zf.live.comet.service.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerSession;

import com.alibaba.fastjson.JSON;
import com.zf.live.client.vo.room.Audience;
import com.zf.live.comet.CometConst;

/**
 * 观众变动通知
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月25日 上午1:21:02
 * 
 * 观众列表通知放在用户加载完html后，自己发送通知
 * 
 */
@Named
@Singleton
@Service("audienceChangeListener")
public class AudienceChangeListener {

	@Inject
	private BayeuxServer bayeux;
	
	@Session
	private ServerSession serverSession;
	
	@Session
	private LocalSession localSession ;
	
	
	/**
	 * 进入房间
	 */
	public static final Byte TYPE_COMEIN = 1;
	
	/**
	 * 退出房间
	 */ 
	public static final Byte TYPE_GOOUT = 2;
	
	/**
	 * 发送观众变动通知到房间
	 * @param videoId
	 * @param audience
	 */
	public void sendAudienceChangeNotice(Long videoId , Audience audience,Byte type){
		List<Audience> audiences = new ArrayList<Audience>();
		audiences.add(audience);
		sendAudienceChangeNotice(videoId, audiences, type);
	}
	
	
	/**
	 * 发送观众变动通知到房间
	 * @param videoId
	 * @param audience
	 */
	public void sendAudienceChangeNotice(Long videoId , List<Audience> audiences,Byte type){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("type", type);
		data.put("audiences", JSON.toJSONString(audiences));   
		localSession.getChannel(CometConst.Channel.audienceChangeChannel + "/" + videoId).publish(data);
	}
	
}

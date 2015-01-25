package com.zf.live.comet.service.chat;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletContext;

import org.cometd.annotation.Configure;
import org.cometd.annotation.Listener;
import org.cometd.annotation.Param;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.ChannelId;
import org.cometd.bayeux.server.Authorizer;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerMessage.Mutable;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.oort.Oort;
import org.cometd.server.authorizer.GrantAuthorizer;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.HtmlUtils;

import com.zf.live.client.vo.room.Audience;
import com.zf.live.comet.CometConst.Channel;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月25日 上午1:55:07
 */
@Named
@Singleton
@Service("pubChatListener")
public class PubChatListener implements ServletContextAware{
	
	@Inject
	private BayeuxServer bayeux;
	
	@Session
	private ServerSession serverSession;
	
	@Session
	private LocalSession localSession ;

	private ServletContext servletContext ;

    @Configure(Channel.chatSendPubChannel)
    public void configure(ConfigurableServerChannel channel)
    {
        channel.setLazy(false);
        channel.addAuthorizer(GrantAuthorizer.GRANT_PUBLISH);
        channel.addAuthorizer(new Authorizer(){
			@Override
			public Result authorize(Operation operation, ChannelId channel,
					ServerSession session, ServerMessage message) {
				if(session.isLocalSession()){
					return Result.grant() ;
				}
				
				Oort oort = (Oort)servletContext.getAttribute(Oort.OORT_ATTRIBUTE) ;
				if(oort.isOort(session)){
					return Result.grant();
				}
				
				Object audienceObj = session.getAttribute("audience"); 
				if(audienceObj == null){
					return Result.deny("没有登录！") ;
				}
				
				Audience audience = (Audience)audienceObj ;
				if(audience.isTourist()){
					return Result.deny("游客不允许发言！");
				}
				
				return Result.grant();
			}
        });
        
    }
	
    /**
     * 监听公聊消息
     * @param remote
     * @param message
     */
	@Listener(Channel.chatSendPubChannel + "/{videoId}") 
	public void chatPub(ServerSession remote, ServerMessage message , @Param("videoId") String videoId)
	{
		Audience audience = (Audience)remote.getAttribute("audience");
		Map<String, Object> dataMap = message.getDataAsMap() ;
		Object msg = dataMap.get("msg");
		if(msg  == null){
			Mutable mutable = message.getAssociated();
			mutable.setSuccessful(false);
			mutable.setData("消息不能为空！");
			return ;
		}
		String msgText = HtmlUtils.htmlEscape(msg.toString(), "utf-8");
		Map<String, Object> data = new HashMap<String, Object>(); 
		data.put("time", String.format("%tR", System.currentTimeMillis())); 
		data.put("msg", msgText);
		data.put("fromUserNick", audience.getUserNick()) ;
		data.put("fromUserId", audience.getUserId()) ;
		localSession.getChannel(Channel.chatRcvPubChannel + "/" + videoId ).publish(data); 
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext ;
	}

	
}

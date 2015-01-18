package com.zf.live.comet;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.DefaultSecurityPolicy;

/**
 * 权限校验
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月19日 上午1:00:43
 */
public class LiveSecurityPolicy  extends DefaultSecurityPolicy{
	
	@Override
	public boolean canHandshake(BayeuxServer server, ServerSession session,
			ServerMessage message) {
		System.out.println("canHandshake");
		return super.canHandshake(server, session, message);
	}
	
	@Override
	public boolean canPublish(BayeuxServer server, ServerSession session,
			ServerChannel channel, ServerMessage message) {
		System.out.println("canPublish");
		return super.canPublish(server, session, channel, message);
	}
	
	@Override
	public boolean canSubscribe(BayeuxServer server, ServerSession session,
			ServerChannel channel, ServerMessage message) {
		System.out.println("canSubscribe");
		return super.canSubscribe(server, session, channel, message);
	}
	

}

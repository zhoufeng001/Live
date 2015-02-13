package com.zf.live.robot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.bayeux.client.ClientSessionChannel.MessageListener;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;
import org.cometd.websocket.client.JettyWebSocketTransport;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.slf4j.Logger;


/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月13日 下午2:00:16
 */
public class WebSocketService {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(WebSocketService.class);

	private static final String cometUrl = Config.getInstance().getProperty("test.comet.url");  

	private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(30);

	public void joinRoom(final Params params){

		for (int i = params.getUserFrom(); i < params.getUserTo(); i++) {  
			final String username = params.getUserPrefix() + i ;
			final String token = HttpUtil.doLogin(username, params.getPassword()) ;
			if(StringUtils.isBlank(token)){
				return ;
			}
			final BayeuxClient client = newClient(cometUrl) ;
			Map<String, Object> handshakeParams = new HashMap<String, Object>();
			Map<String, String> handshakeInfo = new HashMap<String, String>();
			handshakeInfo.put("token", token);
			handshakeInfo.put("videoId", params.getVideoId());
			handshakeParams.put("ext", handshakeInfo );  
			client.handshake(handshakeParams, new MessageListener() {
				@Override
				public void onMessage(ClientSessionChannel channel, Message message) {
					if (message.isSuccessful())  
					{
						client.getChannel("/chat/comeIn").publish(null);  
						log.info("用户[{}]进入房间成功",username);
						if(params.isDoChat()){
							scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
								@Override
								public void run() {
									Map<String, String> chatData = new HashMap<String, String>();
									chatData.put("msg", MessageFactory.buildMessage((MessageFactory.TextType_CommonEm |
											MessageFactory.TextType_Text)));  
									client.getChannel("/chat/send_pub/" + params.getVideoId()).publish(chatData); 
								}
							}, params.getActionInterval(), params.getActionInterval(), TimeUnit.MILLISECONDS) ; 
						}
					}
				}
			}); 
		}
	}

	/**
	 * 根据cometdURL创建一个websocket连接
	 * @param cometdUrl
	 * @return
	 */
	private BayeuxClient newClient(String cometUrl){
		WebSocketClient webSocketClient = new WebSocketClient();
		webSocketClient.setMaxIdleTimeout(6000);
		webSocketClient.setConnectTimeout(6000);
		webSocketClient.setStopTimeout(6000);
		try {
			webSocketClient.start();
		} catch (Exception e) {
			log.error(e.getMessage(),e);  
		}
		ClientTransport wsTransport = new JettyWebSocketTransport(null, null, webSocketClient);
		HttpClient httpClient = new HttpClient();
		httpClient.setIdleTimeout(6000);
		httpClient.setStopTimeout(6000);  
		try {
			httpClient.start();
		} catch (Exception e) { 
			log.error(e.getMessage());  
			return null ;
		}
		ClientTransport httpTransport = new LongPollingTransport(null, httpClient);
		BayeuxClient client = new BayeuxClient(cometUrl, wsTransport, httpTransport);
		return client ;
	}


}

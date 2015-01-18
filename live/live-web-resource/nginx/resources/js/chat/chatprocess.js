var msgUL ;

(function($)
{
	
	function ChatMsgBox(){
		
		/* 添加系统信息 */
		this.appendSystemMsg = function(msg){
			
			msgUL.append('<li><span class="username">系统消息</span>：'+ msg + '</li>');
		}
		
		/* 添加用户消息 */
		this.appendUserMsg = function(msgBody){
			msgUL.append('<li><span>'+ msgBody.time +'</span><span class="username">'+  msgBody.username +'</span>：'+  msgBody.content + '</li>');
		}
		
	}
	
    var cometd = $.cometd;
    
    var chatMsgBox = new ChatMsgBox();

    $(document).ready(function()
    {
    	
    	msgUL = $("#chatlist_ul");
    	
        function _connectionEstablished()
        {   
           chatMsgBox.appendSystemMsg("服务器连接成功！");
        }

        function _connectionBroken()
        {
            chatMsgBox.appendSystemMsg("服务器连接中断！");
        }

        function _connectionClosed()
        {
            chatMsgBox.appendSystemMsg("服务器连接关闭！");
        }

        // Function that manages the connection status with the Bayeux server
        var _connected = false;
        function _metaConnect(message)
        {
            if (cometd.isDisconnected())
            {
                _connected = false;
                _connectionClosed();
                return;
            }

            var wasConnected = _connected;
            _connected = message.successful === true;
            if (!wasConnected && _connected)
            {
                _connectionEstablished();
            }
            else if (wasConnected && !_connected)
            {
                _connectionBroken();
            }
        }

        function _metaHandshake(handshake)
        {
            if (handshake.successful === true)
            {
            	
            }else{
            	 chatMsgBox.appendSystemMsg("服务器连接失败！");
            }
        }

        // Disconnect when the page unloads
        $(window).unload(function()
        {
            cometd.disconnect(true);
        });
        
        var cometURL = cometdHandshake ;
        cometd.configure({
            url: cometURL,
            logLevel: 'debug',
            requestHeaders:'{"aaa":"111" , "bbb":"222"}',
        });

        cometd.addListener('/meta/handshake', _metaHandshake);
        cometd.addListener('/meta/connect', _metaConnect);
        
        cometd.addListener('/service/hello', function(message){
        	alert("/service/hello " + message);    
        });
        
        cometd.handshake();
    });
    
})(jQuery);

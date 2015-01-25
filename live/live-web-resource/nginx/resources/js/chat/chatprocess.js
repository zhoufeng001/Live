var msgUL ;
var msgInput ;
var sendBut ;

(function($)
{
	
	//握手失败次数
	var handshakeFailCount = 0 ;
	var handshakeRetryCount = 3 ;
	
	function ChatMsgBox(){
		
		/* 添加系统信息 */
		this.appendSystemMsg = function(msg){
			msgUL.append('<li><span>系统消息</span>：'+ msg + '</li>');
		}
		
		/* 添加聊天消息 */
		this.appendChatMsg = function(time , fromUserNick , msg){   
			var msgText = replace_em(msg);
			msgUL.append('<li><span>'+ time +'</span><span class="username">'+  fromUserNick +'</span>：'+ msgText + '</li>');
		}
		
		/**
		 * 清除输入内容
		 */
		this.clearInput = function(){
			msgInput.val("");
		}
		
		/**
		 * 启用发送按钮
		 */
		this.enableSendButton = function(){
			sendBut.attr('disabled',false); 
		}
		
		/**
		 * 禁用发送按钮
		 */
		this.disableSendButton = function(){
			sendBut.attr('disabled',true); 
		}
		
	}
	
    var cometd = new $.CometD();  
    
    var chatMsgBox = new ChatMsgBox();

    $(document).ready(function()
    {
    	
    	init();
    	
    	//----------------------连接部分------------------------
    	
    	var pubMsgSubscripe ;
    	var audienceChagenSubscripe ;
    	
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
            	
            	 //订阅公聊
            	 pubMsgSubscripe = cometd.subscribe('/chat/rcv_pub/' + videoId , function(dataBody){
            		 var data =  dataBody.data; 
            		 var time = data.time ;
            		 var fromUserNick = data.fromUserNick ;
            		 var fromUserId = data.fromUserId;
            		 var msg = data.msg ;
            		 if(userId && userId != fromUserId){
            			 chatMsgBox.appendChatMsg(time,fromUserNick,msg);
            		 }
                 });  
            	 
            	 //订阅观众列表变动
            	 audienceChagenSubscripe = cometd.subscribe('/chat/audienceChange/' + videoId , function(dataBody){
            		 var data =  dataBody.data; 
            		 var type = data.type ;
            		 var audiences = data.audiences;
            		 console.log(type + "..." + audiences); 
                 });  
            	 
            }else{
            	handshakeFailCount++;  
            	if(handshakeFailCount >= handshakeRetryCount ){
            		chatMsgBox.appendSystemMsg("服务器连接失败！");
            		cometd.disconnect();  
            	}
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
         
        chatMsgBox.appendSystemMsg("服务器连接中...");
        cometd.handshake({
            ext: {
        		token : userToken,
        		videoId : videoId
            }
        });
        
      //----------------------连接部分------------------------
        
    });
    
    
    /**
     * 发送消息  
     */
    var sendMsg = function(){
 	   var msg = msgInput.val();
 	   if(userToken == null){
 		   alert("请先登录");
 		   return;
 	   }
 	   if(msg == null || "" == msg.trim()){
 		   return ;
 	   }
 	   chatMsgBox.disableSendButton();  
 	   cometd.publish("/chat/send_pub/"  + videoId  , {   
 		   msg : msg 
 	   },function(publishAck){
 		   chatMsgBox.enableSendButton();
 		   if(publishAck.successful){  
 			 chatMsgBox.clearInput();
 			 var time = new Date().format("hh:mm") ;
       		 chatMsgBox.appendChatMsg(time,userNick,msg);
 		   }else{
 			 chatMsgBox.appendSystemMsg("消息发送失败！");
 		   }
 	   });
    }  
    
    /**
     * 页面加在完后初始化函数
     */
    function init(){
    	
    	msgUL = $("#chatlist_ul");
    	msgInput = $("#chat_textarea");
    	sendBut = $("#chat_send");
    	
    	//发送按钮绑定时间
    	sendBut.click(sendMsg);
    	//输入框绑定回车时间
    	msgInput.bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
            	sendMsg();     
            }
        });
    }
    
    
})(jQuery);


//替换表情标签
function replace_em(str){     
	str = str.replace(/\[em_([0-9]*)\]/g,'<img src="'+ static_server +'/img/face/$1.gif" border="0" />');
	return str;
}

/**
 * 给Date对象添加format方法
 */
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} 
	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		}
	}
	return format ;
}

/** 
 * 给String对象添加trim方法  
 */  
String.prototype.trim=function() {  
    return this.replace(/(^\s*)|(\s*$)/g,'');  
};   

<!DOCTYPE html>
<html>
<head>
<!-- 公共meta信息 -->
<#include "./common/live_common_meta.ftl" >	
<#include "./common/live_common_js.ftl" >	
<link href="${static_server}/css/videoview.css" rel="stylesheet" />
<link href="${static_server}/js/qqface/face.css" rel="stylesheet" />
<script type="text/javascript">
var ctx = '${ctx!""}';

var cometServerUrl = "${cometServerUrl!""}" ;
var cometdHandshake = "${cometdHandshake!""}" ;
var config = {
    contextPath: cometServerUrl
};
</script>


<script type="text/jscript" src="${static_server}/js/jquery-migrate-1.2.1.js"></script>
<script type="text/jscript" src="${static_server}/js/qqface/jquery.qqFace.js"></script> 
<script type="text/jscript" src="${static_server}/js/chat/cometd.js"></script>  
<script type="text/jscript" src="${static_server}/js/chat/jquery.cometd.js"></script>
<script type="text/jscript" src="${static_server}/js/chat/chatprocess.js"></script>


<title>${(videoDetailVo.video.videoname)!""}</title>
</head>
<body>  

<div class="index_body"  >

<!-- 头部信息  -->
<#include "./common/head.ftl" >	
 
<!-- 内容部分 -->
    <div class="video_main_content">
    	
        <!-- 视频标题部分 -->
        <div class="video_head">
        	<img src="${static_server}/img/play_big_icon.png" class="playing_icon" />
            <span class="video_name"> ${(videoDetailVo.video.videoname)!""}</span>
            <img src="${static_server}/img/play_icon.png" class="play_icon" />
            <span class="play_count">${(videoDetailVo.video.viewCount)!""}</span>
            <img src="${static_server}/img/praise.jpg" class="praise_icon"/>
            <span class="praise_count"> ${(videoDetailVo.video.thirdPraise)!""}</span>
        </div>
         <!--/视频标题部分 -->
        
        <div class="video_main_box">
        	
            <!-- 观众列表 -->
        	<div class="audience_list">
        		<div class="online_count">
        			300人在线
        		</div>
            	<div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝赖宝赖宝赖宝赖宝赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                 <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                 <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                 <div class="audience">
                	<img class="photo" src="${static_server}/img/userphoto/1.jpg" />
                    <span class="audience_name">赖宝</span>
                </div>
                 <div class="audience_loadmore">
                    <span class="load_more">
                    	<a href="javascript:void(0);">加载更多>></a>
                    </span>
                </div>
            </div>
        	 <!--/ 观众列表 -->
            
            <!-- 播放器和聊天窗 -->
            <div class="player_and_chat">
            	<div class="player">
            	    <div  id="youkuplayer" style="width:700px;height:470px"></div>
                </div>
                <div class="chat_input">
                	<textarea cols="80" rows="5" class="chat_textarea" id="chat_textarea"></textarea>
                     <div class="chat_control">
                    	 <span class="emotion">表情</span>
                		<input type="button" value="发送" class="chat_send" />
               		 </div>
                </div>
            </div>
            <!--/播放器和聊天窗 -->
            
            <!-- 聊天记录 -->
            <div class="chat_recored">
            	<ul class="chatlist_ul" id="chatlist_ul" >
                </ul>
            </div>
            <!--/聊天记录  --> 	
            
            
    </div> 
 <!-- 内容部分结束 -->

<!-- 底部信息  -->
<#include "./common/bottom.ftl" >

</div>
<!--
<script type="text/javascript" src="http://player.youku.com/jsapi">
	player = new YKU.Player('youkuplayer',{
		styleid: '0',
		client_id: '5310d5903f6c9904',   
		vid: '${(videoDetailVo.video.fromid)!""}',
		show_related: false,
		autoplay: false
	});
</script>
-->
<script type="text/javascript">
	$(function(){
		$('.emotion').qqFace({
			assign:'chat_textarea', //给那个控件赋值  
			path:'${static_server}/img/face/'	//表情存放的路径
		});
		
	});
</script>

</body>
</html>
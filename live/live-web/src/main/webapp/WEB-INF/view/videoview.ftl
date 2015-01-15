<!DOCTYPE html>
<html>
<head>
<!-- 公共meta信息 -->
<#include "./common/live_common_meta.ftl" >	
<#include "./common/live_common_js.ftl" >	
<link rel="stylesheet" href="${static_server}/css/videoview.css">

<script type="text/javascript">
 var ctx = '${ctx!""}';

</script>

<title>${(videoDetailVo.video.videoname)!""}</title>
</head>
<body>  

<div class="index_body"  >

<!-- 头部信息  -->
<#include "./common/head.ftl" >	
 
<!-- 内容部分开始 -->
<div class="main_content">
	
	<div class="player_left"></div>
	<div class="player">
		<span class="titleInfo">${(videoDetailVo.video.videoname)!""}</span>
		<div  id="youkuplayer" style="width:800px;height:480px"></div>  
	</div>
	<div class="player_right"></div>

</div>
 <!-- 内容部分结束 -->

<!-- 底部信息  -->
<#include "./common/bottom.ftl" >

</div>

<script type="text/javascript" src="http://player.youku.com/jsapi">
	player = new YKU.Player('youkuplayer',{
		styleid: '0',
		client_id: '5310d5903f6c9904',   
		vid: '${(videoDetailVo.video.fromid)!""}'
	});
</script>

</body>
</html>
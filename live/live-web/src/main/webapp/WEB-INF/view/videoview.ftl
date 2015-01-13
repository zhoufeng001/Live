<!DOCTYPE html>
<html>
<head>
<!-- 公共meta信息 -->
<#include "./common/live_common_meta.ftl" >	
<#include "./common/live_common_js.ftl" >	
<link rel="stylesheet" href="${static_server}/css/category.css">

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
<center>
	<div id="youkuplayer" style="width:800px;height:480px"></div>  
</center>
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
<!DOCTYPE html>
<html>
<head>
<!-- 公共meta信息 -->
<#include "./common/live_common_meta.ftl" >	
<meta property="qc:admins" content="207157331766416567167" />
<meta property="wb:webmaster" content="dfaf9701554df20f" />
<link rel="stylesheet" href="${static_server}/css/index.css">
<link rel="stylesheet" href="${static_server}/css/category.css">  
<title>Live首页</title>

</head>
<body>  

<div class="index_body"  >

<!-- 头部信息  -->
<#include "./common/head.ftl" >	
 
<!-- 内容部分开始 -->
<div class="main_content">

<#if categoryRecomendVoList??>  
<#list categoryRecomendVoList as categoryRecommendVo>
 <!-- 推荐视频列表开始 -->
        <div class="recommend_box">  
        	<p class="list_title">热门${categoryRecommendVo.topVideoDetailVo.video.category!""}</p>
            <hr/>
            <#if categoryRecommendVo.topVideoDetailVo??>
	            <!-- 置顶视频 -->
	            <div class="left_video_box">  
	            	<div class="video_info">
	                    <span class="video_name">  
	                    	<a href="${ctx}/video/view/${(categoryRecommendVo.topVideoDetailVo.video.id)!""}.htm" target="_blank">${(categoryRecommendVo.topVideoDetailVo.video.videoname)!""}</a>
	                    </span>
	                    <span class="user_info"> 
	                    	520人在线
	                    </span>
	                    <span class="praise_info">
	                    	<img src="${static_server}/img/praise.jpg" /> 
	             			<p class="praise_count">${(categoryRecommendVo.topVideoDetailVo.video.thirdPraise)!""}</p>
	                    </span>  
	                </div>   
	            	<div class="video_img">    
	                	<a href="${ctx}/video/view/${(categoryRecommendVo.topVideoDetailVo.video.id)!""}.htm" target="_blank"><img src="${(categoryRecommendVo.topVideoDetailVo.videoDetail.bigthumbnail)!""}" /></a>
	                </div>
	            </div>   
            </#if>  
            <!-- 推荐视频 -->
            <div class="recommend_list_box">
				<#if categoryRecommendVo.recommendVideoList??>
	            	<#list categoryRecommendVo.recommendVideoList as video>
						<!-- 一个视频 -->
		            	<div class="recommend_video_box">  
		                	<span class="video_name"><a href="${ctx}/video/view/108811.htm" target="_blank">${(video.videoname)!""}</a></span>
		                    <a href="${ctx}/video/view/${(video.id)!""}.htm" target="_blank"><img src="${(video.thumbnail)!""}"  /></a>
		                    <span class="praise_info">
		                    	<img src="${static_server}/img/praise.jpg" class="praise_icon" />
		                        <p>${(video.thirdPraise)!""}</p>
		                    </span>
		                    <span class="user_info">
		                    	520人在线
		                    </span>
		                </div>
		                <!--/ 一个视频 -->
					</#list>
				</#if>
            </div>
            
        </div>
        <!-- 推荐视频列表结束 -->
</#list>
</#if>
</div>
 <!-- 内容部分结束 -->

<!-- 底部信息  -->
<#include "./common/bottom.ftl" >


</div>

</body>
</html>
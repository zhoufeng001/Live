<!DOCTYPE html>
<html>
<head>
<!-- 公共meta信息 -->
<#include "./common/live_common_meta.ftl" >	
<link rel="stylesheet" href="${static_server}/css/category.css">
<title>${category!""}频道</title>

</head>
<body>  

<div class="index_body"  >

<!-- 头部信息  -->
<#include "./common/head.ftl" >	
 
<!-- 内容部分开始 -->
<div class="main_content">
	
	
        <!-- 推荐视频列表开始 -->
        <div class="recommend_box">
        	<p class="list_title">热门${category!""}</p>
            <hr/>
            <!-- 置顶视频 -->
            <div class="left_video_box">
            	<div class="video_info">
                    <span class="video_name">
                    	<a href="#">人在囧途</a>
                    </span>
                    <span class="user_info"> 
                    	520人在线
                    </span>
                    <span class="praise_info">
                    	<img src="${static_server}/img/praise.jpg" />
             			<p class="praise_count">12343</p>
                    </span>
                </div>
            	<div class="video_img">
                	<a href="#"><img src="${static_server}/img/video_big_img.jpg" /></a>
                </div>
            </div>
            
            <!-- 推荐视频 -->
            <div class="recommend_list_box">
            	<!-- 一个视频 -->
            	<div class="recommend_video_box">
                	<span class="video_name"><a href="#">武媚娘传奇</a></span>
                    <a href="#"><img src="${static_server}/img/video.jpg"  /></a>
                    <span class="praise_info">
                    	<img src="${static_server}/img/praise.jpg" class="praise_icon" />
                        <p>14421</p>
                    </span>
                    <span class="user_info">
                    	520人在线
                    </span>
                </div>
                <!--/ 一个视频 -->
                <!-- 一个视频 -->
            	<div class="recommend_video_box">
                	<span class="video_name"><a href="#">武媚娘传奇</a></span>
                    <a href="#"><img src="${static_server}/img/video.jpg"  /></a>
                    <span class="praise_info">
                    	<img src="${static_server}/img/praise.jpg" class="praise_icon" />
                        <p>14421</p>
                    </span>
                    <span class="user_info">
                    	520人在线
                    </span>
                </div>
                <!--/ 一个视频 -->
                <!-- 一个视频 -->
            	<div class="recommend_video_box">
                	<span class="video_name"><a href="#">武媚娘传奇</a></span>
                    <a href="#"><img src="${static_server}/img/video.jpg"  /></a>
                    <span class="praise_info">
                    	<img src="${static_server}/img/praise.jpg" class="praise_icon" />
                        <p>14421</p>
                    </span>
                    <span class="user_info">
                    	520人在线
                    </span>
                </div>
                <!--/ 一个视频 --> 
            	<div class="recommend_video_box">
                	<span class="video_name"><a href="#">武媚娘传奇</a></span>
                    <a href="#"><img src="${static_server}/img/video.jpg"  /></a>
                    <span class="praise_info">
                    	<img src="${static_server}/img/praise.jpg" class="praise_icon" />
                        <p>14421</p>
                    </span>
                    <span class="user_info">
                    	520人在线
                    </span>
                </div>
                <!--/ 一个视频 --> 
            	<div class="recommend_video_box">
                	<span class="video_name"><a href="#">武媚娘传奇</a></span>
                    <a href="#"><img src="${static_server}/img/video.jpg"  /></a>
                    <span class="praise_info">
                    	<img src="${static_server}/img/praise.jpg" class="praise_icon" />
                        <p>14421</p>
                    </span>
                    <span class="user_info">
                    	520人在线
                    </span>
                </div>
                <!--/ 一个视频 -->
            	<div class="recommend_video_box">
                	<span class="video_name"><a href="#">武媚娘传奇</a></span>
                    <a href="#"><img src="${static_server}/img/video.jpg"  /></a>
                    <span class="praise_info">
                    	<img src="${static_server}/img/praise.jpg" class="praise_icon" />
                        <p>14421</p>
                    </span>
                    <span class="user_info">
                    	520人在线
                    </span>
                </div>
                <!--/ 一个视频 -->
            </div>
            
        </div>
        <!-- 推荐视频列表结束 -->
      	
        <!-- 排序开始 -->
        <div class="sort_box">
        	<p class="title">排序：</p>   
            <p class="sort_type"><a <#if orderby == 1 >class="sort_current"</#if> href="${ctx}/video/category/${category!""}/1/1.htm"  >在线人数↓</a></p>
            <p class="sort_type"><a <#if orderby == 2 >class="sort_current"</#if> href="${ctx}/video/category/${category!""}/1/2.htm"  >赞数量↓</a></p>
            <p class="sort_type"><a <#if orderby == 3 >class="sort_current"</#if> href="${ctx}/video/category/${category!""}/1/3.htm"  >更新时间↓</a></p>
        </div>  
  		<hr/>
        <!-- 排序结束 -->
        
        <!-- 视频列表开始 -->
        <div class="video_list">
        	<#list videoList as video >
				<!-- 一个视频 -->
	            <div class="list_video_box">
	                <span class="video_name"><a href="#" title="${(video.videoname)!""}">${(video.videoname)!""}</a></span>
	                <a href="#"><img src="${(video.thumbnail)!""}"  /></a>
                <span class="praise_info">
                    <img src="${static_server}/img/praise.jpg" class="praise_icon" />
                    <p>${(video.thirdPraise)}</p>
                </span>
                <span class="user_info">
                    520人在线
                </span>
	            </div>
	            <!--/ 一个视频 -->
			</#list>
        </div>
        <!-- 视频列表结束 -->
        
        <!-- 分页信息开始 -->
        <div class="page_info">
        	<a href="#"> <<  </a>
            <a href="#"> 1 </a>
            <a href="#"> 2 </a>
            <a href="#" class="current_page"> 3 </a>
            <a href="#"> 4 </a>
            <a href="#"> 5 </a>
            <a href="#"> 6 </a>
            <a href="#"> >> </a>
        </div>
         <!-- 分页信息结束 -->
	

</div>
 <!-- 内容部分结束 -->

<!-- 底部信息  -->
<#include "./common/bottom.ftl" >


</div>

</body>
</html>
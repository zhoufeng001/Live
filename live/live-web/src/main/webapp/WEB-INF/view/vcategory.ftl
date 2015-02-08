<!DOCTYPE html>
<html>
<head>
<!-- 公共meta信息 -->
<#include "./common/live_common_meta.ftl" >	
<#include "./common/live_common_js.ftl" >	
<#include "./common/zfpage.ftl" >
<link rel="stylesheet" href="${static_server}/css/category.css">

<script type="text/javascript" src="${static_server}/js/video/vcategory.js" /></script>
<script type="text/javascript">
 var ctx = '${ctx!""}';
 var category = '${category!""}';
 var orderby = '${orderby!""}';
 var pageno =  ${page!""} ;
 var totalRecored = ${(videoPageVo.totalRecored)!""}; 
 var totalPage = ${(videoPageVo.totalPage)!""} ;  

var zfpager = $.zfpager ; 
 
</script>

<title>${category!""}频道</title>  
</head>
<body redirect="${ctx!""}/video/category/${category!""}/${page!""}/${orderby!""}.htm">

<div class="index_body" >

<!-- 头部信息  -->
<#include "./common/head.ftl" >	
 
<!-- 内容部分开始 -->
<div class="main_content">
	
	
        <!-- 推荐视频列表开始 -->
        <div class="recommend_box">
        	<p class="list_title">热门${category!""}</p>
            <hr/>
            <#if categoryRecommendVo.topVideoDetailVo??>
	            <!-- 置顶视频 -->
	            <div class="left_video_box">  
	            	<div class="video_info">
	                    <span class="video_name">  
	                    	<a href="${ctx}/video/view/${(categoryRecommendVo.topVideoDetailVo.video.id)!""}.htm" target="_blank">${(categoryRecommendVo.topVideoDetailVo.video.videoname)!""}</a>
	                    </span>
	                    <span class="user_info"> 
	                    	${(categoryRecommendVo.topVideoDetailVo.video.audienceCount)!""}人在线 
	                    </span>
	                    <span class="praise_info">
	                    	<img src="${static_server}/img/praise.jpg" />    
	             			<p class="praise_count">${(categoryRecommendVo.topVideoDetailVo.video.praise) + (categoryRecommendVo.topVideoDetailVo.video.thirdPraise) }</p>  
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
		                    <span class="video_thumbnail" >
			                    <a href="${ctx}/video/view/${(video.id)!""}.htm" target="_blank">
			                   	 <img src="${(video.thumbnail)!""}"  />
			                    </a>
		                    </span>
		                    <span class="praise_info">
		                    	<img src="${static_server}/img/praise.jpg" class="praise_icon" />
		                        <p>${(video.praise) + (video.thirdPraise)}</p> 
		                    </span> 
		                    <span class="user_info"> 
		                    	${(video.audienceCount)!""}人在线
		                    </span>   
		                </div>   
		                <!--/ 一个视频 -->   
					</#list> 
				</#if>
            </div>
            
        </div>
        <!-- 推荐视频列表结束 -->
      	
        <!-- 排序开始 -->
        <div class="sort_box">
        	<p class="title">排序：</p>   
            <p class="sort_type"><a <#if orderby == 1 >class="sort_current"</#if> href="${ctx}/video/category/${category!""}/1/1.htm"  >在线人数↓</a></p>
            <p class="sort_type"><a <#if orderby == 2 >class="sort_current"</#if> href="${ctx}/video/category/${category!""}/1/2.htm"  >热门↓</a></p>
            <p class="sort_type"><a <#if orderby == 3 >class="sort_current"</#if> href="${ctx}/video/category/${category!""}/1/3.htm"  >更新时间↓</a></p>
        </div>  
  		<hr />
        <!-- 排序结束 -->
         
        <!-- 视频列表开始 -->
        <div class="video_list">  
        	<#list videoPageVo.data as video >
				<!-- 一个视频 -->
	            <div class="list_video_box">
	                <span class="video_name"><a href="${ctx}/video/view/${video.id}.htm" target="_blank" title="${(video.videoname)!""}">${(video.videoname)!""}</a></span>
	                   <span class="video_thumbnail" >
	               		 	<a href="${ctx}/video/view/${video.id}.htm" target="_blank" >
						  	  <img src="${(video.thumbnail)!""}" />
							</a>
	                   </span>
               		 <span class="praise_info">
                    <img src="${static_server}/img/praise.jpg" class="praise_icon" />
                    <p>${(video.praise) + (video.thirdPraise)}</p> 
                	</span>
                <span class="user_info">
                    ${(video.audienceCount)!""}人在线
                </span>  
	            </div>
	            <!--/ 一个视频 -->
			</#list>
        </div>
        <!-- 视频列表结束 -->
        
        <!-- 分页信息开始 -->
         <div id="div_pager" class="page_info"></div>
         <!-- 分页信息结束 -->
</div>
 <!-- 内容部分结束 -->

<!-- 底部信息  -->
<#include "./common/bottom.ftl" >


</div>

</body>
</html>

    <!-- 顶部banner -->
    <div class="top_banner">
    
    </div>
    
    <!-- 顶部导航菜单 -->
    <div class="index_menu_bar">
    	<div class="index_menu">   
	        <!-- 菜单 -->
			<div class="menu_buts">
	    		<span class="menu_but_sp"><a  href="${ctx}/index.htm" class="menu_cur">首  页</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/电影/1/2.htm" >电影</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/电视剧/1/2.htm" >电视剧</a></span> 
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/综艺/1/2.htm" >综艺</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/音乐/1/2.htm" >音乐</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/微电影/1/2.htm" >微电影</a></span>  
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/网剧/1/2.htm" >网剧</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/游戏/1/2.htm" >游戏</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/动漫/1/2.htm" >动漫</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/娱乐/1/2.htm" >娱乐</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/搞笑/1/2.htm" >搞笑</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/自拍/1/2.htm" >自拍</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/时尚/1/2.htm" >时尚</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/汽车/1/2.htm" >汽车</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/科技/1/2.htm" >科技</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/生活/1/2.htm" >生活</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/video/category/其他/1/2.htm" >其他</a></span>  
	           <!-- <span class="menu_but_sp"><a  href="${ctx}/video/category/专辑/1/2.htm" >专辑</a></span> -->  
            </div> 
            <!-- 搜索框 -->
            <div id="search_box">  
                <input type="text" id="search" value="Search" class="swap_value" /> 
                <input type="image" src="${static_server}/img/btn_search_box.gif" width="27" height="24" id="go" alt="Search" title="Search" /> 
             </div>
        </div>
         
       <#if user??>
       <div class="user_info_bar logined">
        	<img class="user_photo" src="${static_server}/img/userphoto/1.jpg" />
            <span class="user_info">  
           	 ${user.nick!""}，<a href="${ctx}/user/doLogout.htm">退出</a>
            </span> 
        </div>
       <#else>
        <div class="user_info_bar unlogin">
        	<span class="user_info">
            	<a href="${ctx}/user/loginView.htm">登录</a>
            	<a href="${ctx}/user/registView.htm">注册</a>
            </span>
            <span class="third_login_logo" >
                <a href="${ctx}/qq/gotoLogin.htm"><img  src="${static_server}/img/qq_login_logo_small.png" /></a>
                <a href="${ctx}/sina/gotoLogin.htm"><img src="${static_server}/img/sina_login_logo_small.png" /></a>
            </span>
        </div>
		</#if>
    </div>
    
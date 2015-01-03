
    <!-- 顶部banner -->
    <div class="top_banner">
    
    </div>
    
    <!-- 顶部导航菜单 -->
    <div class="index_menu_bar">
    	<div class="index_menu">
	        <!-- 菜单 -->
			<div class="menu_buts">
	    		<span class="menu_but_sp"><a  href="${ctx}/index.htm" >首  页</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/index.htm" >正在直播</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/index.htm" >热门播主</a></span>
	            <span class="menu_but_sp"><a  href="${ctx}/index.htm" >热门视频</a></span>
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
    
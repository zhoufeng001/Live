<!DOCTYPE html>
<html>
<head>
<!-- 公共meta信息 -->
<#include "../common/live_common_meta.ftl" >	
<link rel="stylesheet" href="${static_server}/css/login_regist.css">
<title>Live登录</title>
</head>
<body>  

<div class="index_body"  >

<!-- 头部信息  -->
<#include "../common/head.ftl" >	


 
<!-- 内容部分开始 -->
<div class="main_content">
	
    <!-- 左侧广告位 -->
    <div class="left_guanggao" >
    	<img  src="${static_server}/img/login_left_img.jpg" />
    </div>
    
    <div class="right_login_box">
    	<div class="welcome_box">
        	<img src="${static_server}/img/login_welcome_img.jpg"/>
            <span class="login_label">登录</span>
            <span class="regist_lable">
            	<a href="${ctx}/user/registView.htm">注册帐号》</a>
            </span>
        </div>
    	<div class="plateform_login_box">
    	  <form method="post" action="${ctx}/user/doLogin.htm">
        	<ul class="lable_ul">
            	<li>帐 号：</li>
                <li>密 码：</li>
            </ul>
            <ul class="input_ul">
            	<li>
                	<input type="text" name="userkey" class="live_input login_input"/>
                </li>
                <li>
                	<input type="password"  name="secret" class="live_input login_input" />
                </li>
                <li class="rem_password">
                	<input type="checkbox" />
                    <span> 记住密码</span>
                </li>
                <li>
                	<input type="submit"  class="login_but" value="登 录" />
                </li> 
                <li class="third_login">
                	<a href="${ctx}/qq/gotoLogin.htm"><img src="${static_server}/img/qq_login_logo_big.png" /></a>
                    <a href="${ctx}/sina/gotoLogin.htm"><img src="${static_server}/img/sina_login_logo_big.png" /></a>
                </li>
             </ul>
            </form>
       </div> 
    </div>
</div>
 <!-- 内容部分结束 -->


<!-- 底部信息  -->
<#include "../common/bottom.ftl" >

</div>

</body>
</html>
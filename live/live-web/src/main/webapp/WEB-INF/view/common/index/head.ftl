<link rel="stylesheet" href="${static_server}/css/index_head.css">
<div class="index_head_top_div">
<#if user?? >
	欢迎您，${user.nick}！ <a href="${ctx}/user/doLogout.htm">退出</a> 
<#else>
	您好，请<a href="${ctx}/user/loginView.htm">登录</a> 或 <a href="${ctx}/user/registView.htm">注册</a>
</#if>
</div>
  
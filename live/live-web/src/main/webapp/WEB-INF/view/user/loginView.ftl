<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="${static_server}/img/favicon.ico" />
<title>登录</title>

</head>
<body>  
<h1>用户登录</h1>
<br/></br/>
<form method="post" action="${ctx}/user/doLogin.htm">
登录名：<input type="text" name="userkey" value="C12345" />
&nbsp;&nbsp;密码：<input type="text" name="secret" value="is_zhoufeng" />
<br/><br/>
<input type="submit" value="登录" />
&nbsp;&nbsp;<a href="${ctx}/user/registView.htm">注册</a>
</form>  

<br/>
<font color="red">${(RequestContext.getErrTipMessage())!"xx"}</font>

</body>
</html>
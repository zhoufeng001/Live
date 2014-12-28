<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="${static_server}/img/favicon.ico" />
<title>注册</title>
</head>
<body>  
<h1>用户注册</h1>
<br/></br/>
<form method="post" action="${ctx}/user/doRegist.htm">
登录名：<input type="text" name="userkey" />
&nbsp;&nbsp;昵称：<input type="text" name="nick" />
&nbsp;&nbsp;密码：<input type="text" name="secret" />

<br/><br/>
<input type="submit" value="登录" />
</form>  
</body>
</html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Freemarker</title>

</head>
<body>  
<form method="post" action="${ctx}/user/doLogin.htm">
登录名：<input type="text" name="userkey" />
&nbsp;&nbsp;密码：<input type="text" name="secret" />
<br/><br/>
<input type="submit" value="登录" />
</form>  
</body>
</html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Freemarker</title>

</head>
<body>  
<form method="post" action="${ctx}/user/doLogin.htm">
登录名：<input type="text" name="userkey" value="C12345" />
&nbsp;&nbsp;密码：<input type="text" name="secret" value="is_zhoufeng" />
<br/><br/>
<input type="submit" value="登录" />
</form>  
</body>
</html>
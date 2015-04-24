<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>教学互动系统登录</title>
  
	<meta http-equiv="expires" content="0">
  </head>
  
  <body>
     <div style="position:absolute; left:500px; top:200px; background-color: #EEDFCC; width: 500px;height: 215px;">
	    <form action="${pageContext.request.contextPath }/servlet/Login" method="post" style="text-align: center; margin-top: 9%;">
	    	用户名：<input type="text" name="username"/><br/><br/>
	    	密 &nbsp;码：<input type="password" name="pass"/><br/><br/>
	    	<div style="margin-bottom: 5px; margin-left:194px ;width: 150px; height: 30px;">
		    	<input type="submit" value="登录">&nbsp;&nbsp;&nbsp;
		    	<input type="reset" value="重置">
	    	</div>
	    </form>
    </div>
  </body>
</html>

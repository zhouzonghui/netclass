<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>add student</title>
  
	<meta http-equiv="expires" content="0">   
  </head>
  <!-- 这里终于用到了EL的param内置对象  上一个jsp传递过来的课堂的id -->
  <body style="text-align: center;">
  	<strong>${param.name}</strong>
  	<form action="${pageContext.request.contextPath}/servlet/AddStudent" method="post" onsubmit="return confirm('确定提交？');">
  		<input type="hidden" name="classid" value="${param.id }"/>
  		<textarea rows="20" cols="100" name="info"></textarea>
  		<input type="submit" value="提 交"/>
  	</form>
    
  </body>
</html>
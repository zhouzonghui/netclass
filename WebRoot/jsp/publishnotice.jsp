<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>发布通知</title>
  
	<meta http-equiv="expires" content="0">   
  </head>
  
  <body>
  	<center>发布通知<br/><br/>
  		<form action="${pageContext.request.contextPath }/servlet/AddNotice" method="post">
  			<textarea rows="8" cols="70" name="content"></textarea><br/><br/>	
  			<input type="submit" value="提交"/>
  			
  		</form>
    	
    </center>
  </body>
</html>

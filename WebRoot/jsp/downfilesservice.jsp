<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>文件下载列表</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
	<meta http-equiv="expires" content="0">   
  </head>
  
  <body>
    <center>
    	<table frame="border" bordercolor="blue" rules="all" align="center">
	    	<thead>文件列表</thead>
	    	<tr>
	    		<td><font color="red"><strong>文件名称</strong></font></td>
	    		
	    	</tr>
	    	
	    	<c:forEach var="n" items="${list }">
	    		<tr>
	    			<td><br/><a href="${pageContext.request.contextPath}/servlet/FileDownloadService?filename=${n.name }">${n.name }</a></td>
	    			
	    		</tr>
	    		
	    	</c:forEach>
    	
    	</table>
    </center>
  </body>
</html>
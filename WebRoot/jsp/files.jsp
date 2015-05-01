<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>文件列表</title>
	<meta http-equiv="expires" content="0"> 
	<style type="text/css">
		td {
			text-align: center;
		}
	</style>  
  </head>
  
  <body>
    <center>
    	<table frame="border" bordercolor="blue" rules="all" width="800px" align="center">
	    	<thead><font size="5">文件列表</font></thead>
	    	<tr>
	    		<td><font color="red"><strong>文件名称</strong></font></td>
	    		<td><font color="red"><strong>上传时间</strong></font></td>
	    		<td><font color="red"><strong>删除文件</strong></font></td>
	    	</tr>
	    	
	    	<c:forEach var="n" items="${list }">
	    		<tr>
	    			<td>${n.name }</td>
	    			<td>${n.time }</td>
	    			<td><a href="${pageContext.request.contextPath }/servlet/DeleteFile?id=${n.id}" target="body" onclick="return confirm('确定删除？');">删除</a></td>
	    		</tr>
	    		
	    	</c:forEach>
    	
    	</table>
    </center>
  </body>
</html>
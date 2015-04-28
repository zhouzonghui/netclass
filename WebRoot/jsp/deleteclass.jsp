<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>删除课堂</title>
	<meta http-equiv="expires" content="0">   
	
	<style type="text/css">
		td {
			text-align: center;
		}
	</style>
  </head>
  
  <body>
  
    <center>
    	<table frame="border" bordercolor="green" rules="all" width="500px">
    		<thead><font size="5">课堂列表</font></thead>
    		<tr>
    			<td>课堂ID</td>
    			<td>名 称</td>
    			<td>上课时间</td>
    			<td>是否删除</td>
    		</tr>
    		<c:forEach var="c" items="${list }">
    			<tr>
    				<td>${c.id }</td>
    				<td>${c.name }</td>
    				<td>${c.time }</td>
    				<%-- <td><input type="checkbox" value="${c.id }" id="">删除</td> --%>
    				<td><a href="${pageContext.request.contextPath }/servlet/DeleteClass?id=${c.id}" target="body" onclick="return confirm('确定删除？');">删除</a></td>
    			</tr>
    		</c:forEach>
    	</table>
    </center>
  </body>
</html>

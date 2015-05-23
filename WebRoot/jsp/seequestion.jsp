<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查看留言</title>
	<meta http-equiv="expires" content="0">   
	<style type="text/css">
		td {
			text-align: left;
		}
	</style>
  </head>
  
  <body>
    <center>
    	<table frame="border" bordercolor="blue" rules="all" width="800px" align="center">
	    	<thead><font size="5">学生留言</font></thead>
	    	<tr>
	    		<td><font color="red"><strong>详　　情</strong></font></td>
	    		
	    	</tr>
	    	
	    	<c:forEach var="n" items="${page.list }">
	    		<tr>
	    			<td>
	    				<font color="red">标题：</font><c:out escapeXml="true" value="${n.title }"/><br/>
	    				<font color="red">内容：</font><c:out escapeXml="true" value="${n.content }"/><br/>
	    				<font color="red">回复：</font><c:out escapeXml="true" value="${n.answer }"/>
	    			</td>
	    			
	    		</tr>
	    		
	    	</c:forEach>
    	</table>
    	<br/>
    	<%@ include file="/jsp/page.jsp" %>
    </center>
  </body>
</html>
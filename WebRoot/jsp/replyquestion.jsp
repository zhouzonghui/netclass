<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>留言回复</title>
    <script type="text/javascript" src="/netclass/js/jquery-1.11.2.js"></script>
	<meta http-equiv="expires" content="0">   
	<style type="text/css">
		td {
			text-align: left;
		}
	</style>
	
	<script type="text/javascript">
		$(document).ready(function() {
		//postSubmit这个函数从网上复制来的，经测试这段代码在较新的IE、Chrome、firefox都能运行
			function postSubmit(url, data, msg) {  
			    var postUrl = url;//提交地址  
			    var postData = data;//第一个数据  
			    var msgData = msg;//第二个数据  
			    var ExportForm = document.createElement("FORM");  
			    document.body.appendChild(ExportForm);  
			    ExportForm.method = "POST";  
			    var newElement = document.createElement("input");  
			    newElement.setAttribute("name", "qid");  
			    newElement.setAttribute("type", "hidden");  
			    var newElement2 = document.createElement("input");  
			    newElement2.setAttribute("name", "qans");  
			    newElement2.setAttribute("type", "hidden");  
			    ExportForm.appendChild(newElement);  
			    ExportForm.appendChild(newElement2);  
			    newElement.value = postData;  
			    newElement2.value = msgData;  
			    ExportForm.action = postUrl;  
			    ExportForm.submit();
			};
		
			$(".btn_reply").click(function(){
				var x = $(this).parent().find("textarea");
				
				var url = "${pageContext.request.contextPath}/servlet/AnswerQuestion";
				postSubmit(url, x.attr("id"), x.val());
			});
		
		});
	</script>
	
  </head>
  
  <body>
  <!-- 回复一个问题后跳转回本页，此时request域带来了一个key为victory的数据，而如果不是回复问题后更新跳转回来，则该数据为空，这样就控制了是否弹出这个对话框 -->
  <c:if test="${!empty victory}">
  	<script type="text/javascript">alert("已回复");</script>
  </c:if>
    <center>
    	<table frame="border" bordercolor="blue" rules="all" width="800px" align="center">
	    	<thead><font size="5">回复学生留言</font></thead>
	    	<tr>
	    		<td><font color="red"><strong>详　　情</strong></font></td>
	    		
	    	</tr>
	    	
	    	<c:forEach var="n" items="${page.list }">
	    		<tr>
	    			<td>
	    				<font color="red">标题：</font><c:out escapeXml="true" value="${n.title }"/><br/>
	    				<font color="red">内容：</font><c:out escapeXml="true" value="${n.content }"/><br/>
	    				<font color="red">回复：</font><br/>
	    				<div>
		    				<textarea rows="5" cols="80" id="${n.id }"></textarea>
		    				<input type="button" value="回复" class="btn_reply"/>
	    				</div>
	    			</td>
	    			
	    		</tr>
	    		
	    	</c:forEach>
    	</table>
    	<br/>
    	<%@ include file="/jsp/page.jsp" %>
    </center>
  </body>
</html>
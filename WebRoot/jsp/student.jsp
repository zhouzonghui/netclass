<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>student</title>
  	<script type="text/javascript" src="/netclass/js/jquery-1.11.2.js"></script>
	<meta http-equiv="expires" content="0">   
	<script type="text/javascript">
		$(document).ready(function(){
			$("tr:even").addClass("even");
 			$("tr:odd").addClass("odd");
 			
 			//用类选择器给所有分数输入框添加over事件，鼠标一上去就被选中
 			$(".score").mouseover(function(){
 				//this指代当前特定的这个框，不可用$(".score")，因其会选中所有的框
				this.focus();
				this.select();
			});
 			
 			//验证分数的格式合法性
 			$(".score").focusout(function(){
 				var reg = /^(100|[1-9]?\d(\.\d)?)$/;
 				if(!reg.test($(this).val())){
 					$(this).parent().find("span").text("不合法");
 				}else{
 					$(this).parent().find("span").text("");
 				}
 				
 			});
 			
 			//文本框内容改变的时候把内容赋值给隐藏表单域
 			$(".score").change(function(){
 				$(this).parent().find(".hdn").val($(this).val());
 				
 			});
 			
 			
		});
	</script>
	<style type="text/css">
		body {
			text-align: center;
		}
		td {
			text-align: center;
		}
		.even {
 			background-color:#99CC99;
		}
		.odd {
		 	background-color:#FFCC99;
		}
		
	</style>
  </head>
  
  <body>
  	<!-- 这个表单提交弄了一天。。。 -->
    <font size="5">课堂：${clazz.name } 　　　上课时间：${clazz.time}</font><br/><br/>
    <table frame="border" bordercolor="green" rules="all" width="500px" align="center">
    	<tr>
    		<td>学号</td>
    		<td>姓名</td>
    		<td>性别</td>
    		<td>分数</td>
    	</tr>
    	<form action="${pageContext.request.contextPath }/servlet/UpdateScore" method="post">
		    <c:forEach var="s" items="${list}">
		    	<tr>
		    		<td>${s.id }</td>
		    		<td>${s.name }</td>
		    		<td>${s.gender }</td>
		    		<td><div><input type="text" id="${s.id }" value="${s.score }" class="score"/><input type="hidden" class="hdn" name="score" value=""/><span></span></div></td>
		    		<input type="hidden" name="id" value="${s.id}"/>
		    		
		    	</tr>
		    </c:forEach>
	    
	    <input type="submit" value="提交"/>
	    </form>
    </table>
  </body>
</html>













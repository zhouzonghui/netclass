<%@page import="org.graduate.domain.Class"%>
<%@page import="org.graduate.dao.ClassDao"%>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="/netclass/js/jquery-1.11.2.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#notice").click(function() {
				$("#n_content").toggle("fast");
			});
			$("#resource").click(function() {
				$("#r_content").toggle("fast");
			});
			$("#message").click(function() {
				$("#m_content").toggle("fast");
			});
			//待处理
			$("#score").click(function() {
				$("#s_content").toggle("fast");
			});
			
			
			
			$("#cs").click(function() {
			
				if ($("#c_content").css("display") == "none") {
					var result = confirm("确定要打开课堂与学生面版吗？");
					if (result) {
						$("#c_content").show("fast");
					}
				}else {
					$("#c_content").hide("fast");
				}
				
			});
			
		});
		
	</script>
	<style>
		.content {
			margin-left: 30px;
		}
		#c_content {
			margin-left: 30px;
			display: none;
		}
		
	</style>
  </head>
  
  <body>
  	<%--按照MVC，jsp不应出现java代码，但根据实际情况，这里是最方便的 --%>
  	<%
  		//从数据库取出所有课堂，放到page域
  		ClassDao classDao = new ClassDao();
  		List<Class> list = classDao.findAll();
  		pageContext.setAttribute("list", list);
  	 %>
  	<ul>
	    <div id="notice"><li><a href="#">通知管理</a></li></div>
	    <div id="n_content" class="content">
		    <a href="${pageContext.request.contextPath }/servlet/ListNotice" target="body">查看通知</a><br/>
		    <a href="${pageContext.request.contextPath }/jsp/publishnotice.jsp" target="body">发布通知</a>
	    </div>
	    <br/>
	    <div id="resource"><li><a href="#">资源管理</a></li></div>
	    <div id="r_content" class="content">
		    <a href="">查看资源</a><br/>
		    <a href="${pageContext.request.contextPath }/jsp/upload.jsp">上传资源</a>
	    </div>
	    <br/>
	    <div id="message"><li><a href="#">留言管理</a></li></div>
	    <div id="m_content" class="content">
		    <a href="">查看留言</a><br/>
		    <a href="">留言回复</a>
	    </div>
	    <br/>
	    <!-- 待处理 -->
	    <div id="score"><li><a href="#">成绩管理</a></li></div>
	    <div id="s_content" class="content">
		    <c:forEach var="c" items="${list}">
		    	<a href="${pageContext.request.contextPath }/servlet/ScoreMrgUI?id=${c.id}" target="body">${c.name}</a><br/>
		    </c:forEach>
		    
	    </div>
	    <br/>
	    <div id="cs"><li><a href="#">课堂与学生管理</a></li></div>
	    <div id="c_content">
		    <a href="${pageContext.request.contextPath }/jsp/addclass.jsp" target="body">添加课堂</a><br/>
		    <a href="${pageContext.request.contextPath }/servlet/DeleteClassUI" target="body">删除课堂</a><br/>
		    <a href="">为课堂添加学生</a>
	    </div>
    </ul>
    
  </body>
</html>











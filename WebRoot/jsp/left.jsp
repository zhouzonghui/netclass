<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>

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
  	<ul>
	    <div id="notice"><li><a href="#">通知管理</a></li></div>
	    <div id="n_content" class="content">
		    <a href="">查看通知</a><br/>
		    <a href="">发布通知</a>
	    </div>
	    <br/>
	    <div id="resource"><li><a href="#">资源管理</a></li></div>
	    <div id="r_content" class="content">
		    <a href="">查看资源</a><br/>
		    <a href="">上传资源</a>
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
		    <a href="">查看留言</a><br/>
		    <a href="">留言回复</a>
	    </div>
	    <br/>
	    <div id="cs"><li><a href="#">课堂与学生管理</a></li></div>
	    <div id="c_content">
		    <a href="${pageContext.request.contextPath }/jsp/addclass.jsp" target="body">添加课堂</a><br/>
		    <a href="">删除课堂</a><br/>
		    <a href="">为课堂添加学生</a>
	    </div>
    </ul>
    
    
    
    
  </body>
</html>











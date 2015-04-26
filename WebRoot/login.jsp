<%@ page contentType="text/html; charset=UTF-8" import="java.util.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>教学互动系统登录</title>
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
	<meta http-equiv="expires" content="0">
	<style type="text/css">
		body {
			margin: 0 auto;
			width: 800px;
			height: auto;
			font-size: 20px
		}
		
		#form {
			margin: 130px auto;
			background-color: #f5f5f5; width : 380px;
			height: 180px;
			width: 380px;
		}
		
		#u {
			width: 280px;
			height: 75px;
			margin: 50px auto;
			
		}
		
		#s {
			width: 280px;
			height: 30px;
			margin: -10px auto;
			margin-left: 130px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//给String增加新的方法trim()，用来去掉字符串前后的空格
			String.prototype.trim=function() {
    			return this.replace(/(^\s*)|(\s*$)/g,'');
			}
			//js判断输入表单是否为空，为空则给出提示信息，并阻止提交
			$("#form").submit(
				function() {
					var username = $("#user").val();
					var pass = $("#pwd").val();
					if (username.trim() == "" || pass.trim() == "") {
						alert("请填写完整！");
						return false;
					}
					return true;
				}
			);
		});
	</script>
</head>

<body>
<br/>
	<center><font size="6">教学互动系统后台管理登录</font></center>

	<form action="${pageContext.request.contextPath }/servlet/Login"
		method="post" id="form">
		<div id="u"><br/>
			用户名：<input type="text" name="username" id="user" /><br />
			<br /> 密　码：<input type="password" name="pass" id="pwd" /><br />
			<br />
		</div>
		<div id="s">
			<input type="submit" value="登录" id="sub">&nbsp;&nbsp;&nbsp; <input
				type="reset" value="重置">
		</div>
	</form>


</body>
</html>

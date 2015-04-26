<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加课堂</title>
  	<script type="text/javascript" src="/netclass/js/jquery-1.11.2.js"></script>
	<meta http-equiv="expires" content="0">
	<script type="text/javascript">
		$(document).ready(function() {
			String.prototype.trim=function() {
    			return this.replace(/(^\s*)|(\s*$)/g,'');
			}
			$("#sub").click(function(){
				var name = $("#cn").val();
				var time = $("#ti").val();
				
				if (name.trim() == "" || time.trim() == "") {
					alert("请填写完整！");
					return false;
				}
				return true;
			});
		});
	</script>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/servlet/AddClass" method="post" onsubmit="return confirm('确定提交？');">
    	课堂名称：<input type="text" name="classname" id="cn"/><br/><br/>
    	上课时间：<input type="text" name="time" id="ti"/><br/><br/>
    	　　　　　<input type="submit" value="提交" id="sub"/>　　　　<input type="reset" value="重置"/>
    </form>
  </body>
</html>
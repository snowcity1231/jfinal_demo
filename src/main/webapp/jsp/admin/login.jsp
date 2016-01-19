<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>admin-login</title>
    
  </head>
  
  <body>
    <form action="admin/user/login">
    	用户名:<input type="text" name="userName"></br>
    	密&nbsp;&nbsp;码:<input type="password" name="密码"></br>
    	<input type="submit" value="登录">
    </form>
  </body>
</html>

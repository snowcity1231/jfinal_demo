<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>admin-register</title>
    
  </head>
  
  <body>
    <form action="admin/user/register">
    	用户名:<input type="text" name="userName"></br>
    	密&nbsp;&nbsp;码:<input type="password" name="password"></br>
    	年&nbsp;&nbsp;龄:<input type="text" name="age"></br>
    	<input type="submit" value="注册">
    </form>
  </body>
</html>

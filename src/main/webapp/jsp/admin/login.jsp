<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String userName = request.getParameter("userName") == null ? "" : request.getParameter("userName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>admin-login</title>
    
  </head>
  
  <body>
    <form action="admin/user/login">
    	用户名:<input type="text" name="userName" value="<%=userName%>">${nameMsg}</br>
    	密&nbsp;&nbsp;码:<input type="password" name="password">${pwdMsg }</br>
    	<input type="submit" value="登录">
    </form>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String title = (String)request.getAttribute("title");
String content = (String)request.getAttribute("content");
String imgUrl = (String)request.getAttribute("path");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title><%=title %></title>
  </head>
  
  <body>
    <p><%=content %></p>
    <img src="<%=imgUrl%>" width="400" height="400">
    
  </body>
</html>

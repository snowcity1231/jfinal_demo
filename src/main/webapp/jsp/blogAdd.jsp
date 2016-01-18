<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>jfinal getModel方法</title>
  </head>
  
  <body>
    <form action="blog/save" method="post" enctype="multipart/form-data">
    	<input name="blog.title" type="text">
    	<input name="blog.content" type="text">
    	<input name="img" type="file">
    	<input value="提交" type="submit">
    </form>
  </body>
</html>

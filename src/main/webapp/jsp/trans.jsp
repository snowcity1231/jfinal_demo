<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>trans</title>
  </head>
  
  <body>
    <form action="account/update" method="post">
    	转出账户：<input name="fromAccountId" type="text">
    	目标账户：<input name="toAccountId" type="text">
    	金额：<input name="amount" type="text">
    	<input value="提交" type="submit">
    </form>
  </body>
</html>

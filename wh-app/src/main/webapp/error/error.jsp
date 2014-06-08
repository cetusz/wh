<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>错误页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="JavaScript"> 
             if (window != top) 
                top.location.href = location.href; 
     </script>

  </head>
  
  <body align="center">
  <div style="width:800px;height:300px;margin:0 auto;border:#ccc solid 1px;text-align:center;valign:middle">
      <s:property value="exception.message" />
      <br> <a href="system!toLogin.do"/>返回登录页</a>
  
  </div>
  </body>
</html>

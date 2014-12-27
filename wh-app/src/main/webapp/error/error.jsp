<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  
  <body align="center" style="margin:0 auto">
    <div style="width:800px;height:300px;margin:0 auto;border:#ccc solid 1px;text-align:center;valign:middle">
         <p>登錄超時！</p>
         <br> <a href="<%=path%>/system/tologin"/>返回登录页</a>
    </div>  
    
  
  </div>
  </body>
</html>

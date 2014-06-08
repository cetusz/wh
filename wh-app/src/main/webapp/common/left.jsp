<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<div title="新闻管理" selected="true">
		<li><a href="javascript:linkPage('cibn/userSync.jsp','同步CIBN')" class="easyui-linkbutton" plain="true">同步CIBN</a>
	</div>
	<div title="发布管理" selected="true">
		<li><a href="javascript:linkPage('manage/client!list','版本管理')" class="easyui-linkbutton" plain="true">版本管理</a>
		<li><a href="javascript:linkPage('gray/userList.jsp','用户管理')" class="easyui-linkbutton" plain="true">用户管理</a>
	</div>
  </body>
</html>

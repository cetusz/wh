<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>媒资/节目单爬虫管理-登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script>
	 $(function(){
	      document.onkeydown = function(e){
	    	    if(!e) e = window.event;//火狐中是 window.event
		        if((e.keyCode || e.which) == 13){
		        	submitForm();
		        }
	        }
	     });
	</script>
  </head>
  
  <body style="background:#E0ECFF;">
	<div id="w" class="easyui-window" title="微航后台管理-登录" style="width:300px;height:200px;top:200px;padding:10px;">
	    <form id="ff" method="post" action="<%=path%>/system/login">
	    	<table align="center">
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input class="easyui-validatebox" id="userName" type="text" name="userName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密 码:</td>
	    			<td><input class="easyui-validatebox" id="passWord" type="password" name="passWord" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	 
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清除</a>
	    </div>
	</div>
	<script>
		function submitForm(){
			var userName = $("#userName").val();
			var pwd =  $("#passWord").val();
			$.post('<%=path%>/system/finduser',{userName:userName,password:pwd},function(data){
                       if(data.success){
                    	   $('#ff').submit();
                       }else{
                            $.messager.alert('错误','用户名密码不匹配','error');
                        }
			});
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>

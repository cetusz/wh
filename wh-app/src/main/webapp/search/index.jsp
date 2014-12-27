<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>搜索配置</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery.easyui.min.js"></script>
     <script type="text/javascript" src="<%=path %>/resources/js/common.js"></script>
      <script type="text/javascript" src="<%=path %>/resources/js/ajaxloading.js"></script>	
    <script type="text/javascript" src="<%=path %>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">

		<!-- 列表数据定义的存放表格 -->
		<table id="list_data"  title="公众账号列表"  fit="true" data-options="toolbar:'#tb'">
		</table>

		<div id="tb" style="padding:5px;height:auto">
		    <div style="margin-bottom:5px">
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="indexAccount()">索引账号</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="indexEassay()">索引文章</a>
			</div>
		</div>
	</div>
</body>
<script>
  $(function(){
	
  });
  
  function indexAccount(){
      $.ajax({
          type: "POST",
          url: '<%=path%>/admin/search/indexaccount',
          success: function(data){
        	  loading.end();
         	 $.messager.alert("提示", data.message, "info");
          },
          complete:function(XHR, TS){
        	  loading.end();
          },
          beforeSend:function(XHR){
        	  loading.start();
          }/*,
          error:function(){
         	 $.messager.alert('错误','索引失败','error');
           }*/
      });
  }
  
  function indexEassay(){
	  $.ajax({
          type: "POST",
          url: '<%=path%>/admin/search/indexEassay',
          success: function(data){
        	  loading.end();
         	 $.messager.alert("提示", data.message, "info");
          },
          complete:function(XHR, TS){
        	  loading.end();
          },
          beforeSend:function(XHR){
        	  loading.start();
          }/*,
          errr:function(){
        	 $.messager.alert('索引失败');
          }*/
      });
  }
  </script>
</html>
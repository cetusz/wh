<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文章列表页</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/mygrid.js"></script>	
     <script type="text/javascript" src="<%=path %>/resources/js/common.js"></script>	
    <script type="text/javascript" src="<%=path %>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
      <script type="text/javascript" src="<%=path %>/resources/easyui/extends/easyui-extends-dateformatter.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">
		
		<!-- 列表数据定义的存放表格 -->
		<table id="list_data"  title="公众账号列表"  fit="true" data-options="toolbar:'#tb'">
		</table>
		
		<div id="tb" style="padding:5px;height:auto">
		    <div style="margin-bottom:5px">
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="crawler()">新增</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
			</div>
		    <div>
				公众账号名称:<input class="easyui-text" style="width: 150px"
						id="chineseName" name="chineseName" > 
				公众账号ID:<input class="easyui-text" style="width: 150px"
						id="accountId" name="accountId" > 
				<a href="#" class="easyui-linkbutton" iconCls="icon-search"
					onclick="doSearch()">查询</a>
			</div>
		</div>
	</div>
</body>
<script>
  $(function(){
	  var options = {
	            columns:[[
						{field:'id',hidden:true},
						{field:'title',title:'标题'},
						{field:'contentUrl',title:'链接地址',align:'center',width:130,formatter:function(value,row,index){
							return '<a href=#>'+value+'</a>';
						}
						},
						{field:'categoryName',title:'分类名称',align:'center',width:150},
					{field:'accountName',title:'所属公众账户',align:'center'},
					{field:'pubDate',title:'发布日期',align:'center',width:100,formatter:function(value,row,index){
						return formatDatebox(value);
					}}
			 	]],
			 	url:'<%=path%>/admin/eassay/list',
			 	pageList: [20,50,500]
			}; 
			$('#list_data').mygrid(options);	
			//绑定用户点击事件
			$('#list_data').datagrid({
				    onClickCell: function(index,field,value){
					      if(field == "contentUrl"){
					    	  //showDetail(index,field,value);
					    	  window.open(value);
					      }else if(field == "edit"){
					    	  showDetail(index,field,value);
					      }
					 }
			});
  });
  
  function del(){
	  var rows = $("#list_data").datagrid('getSelections');
	  var idArray = [];
	  for(var i=0,len=rows.length;i<len;i++){
		  idArray.push(rows[i].id);
	  }
	  
	  $.post("<%=path%>/admin/eassay/del",{ids:idArray.toString()},function(data){
		  $("#list_data").datagrid('load');
		  
	  });
	  
  }
  </script>
</html>

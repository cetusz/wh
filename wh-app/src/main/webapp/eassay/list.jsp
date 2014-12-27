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
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="setType()">设置分类</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="setRecommend(true)">推荐</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="setRecommend(false)">取消推荐</a>
			</div>
		    <div>
		                文章標題:<input class="easyui-text" style="width: 150px"
						id="title" name="title" > 
				公众账号名称:<input class="easyui-text" style="width: 150px"
						id="accountName" name="accountName" > 
				公众账号ID:<input class="easyui-text" style="width: 150px"
						id="accountId" name="accountId" > 
			       分类:<select id="categoryId" name="categoryId" style="width:80px" class="easyui-combobox" 
						data-options=" url:'<%=path%>/admin/category/getlist',
									valueField: 'id',
									textField: 'cateName',
									method:'get'">
							<option value="">请选择</option>
					</select>
				在<input id="dateAfter" name="dateAfter" type="text" class="easyui-datebox">之后更新
				<a href="#" class="easyui-linkbutton" iconCls="icon-search"
					onclick="doSearch()">查询</a>
			</div>
		</div>
	</div>
	
	<div id="set_type_window" class="easyui-window" style="width:600px;height:370px;"  data-options="modal:true,closed:true"  title="设置分类">
			       	<table id="category_list_data" style="width:600px;height:370px;"  title="分类列表"  fit="true" data-options="toolbar:'#ctb'">
		            </table>
					<div id="ctb" style="padding:5px;height:auto">
					    <div>
							分类名称:<input class="easyui-text" style="width: 150px"
									id="cateName" name="cateName" > 
							<a href="#" class="easyui-linkbutton" iconCls="icon-search"
								onclick="doTypeSearch()">查询</a>
								 <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:save_set_type()">设置</a>
					          <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="javascript:close_set_type()">关闭</a>
								
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
						{field:'categoryName',title:'分类名称',align:'center',width:150},
						{field:'accountName',title:'所属公众账户',align:'center'},
					{field:'pubDate',title:'发布日期',align:'center',width:100,formatter:function(value,row,index){
						return formatDatebox(value);
					}},
					{field:'isRecommanded',title:'是否推荐',align:'center',width:100,formatter:function(value,row,index){
						return value==false?'否':'是';
					}},
					{field:'contentUrl',title:'詳情',align:'center',width:130,formatter:function(value,row,index){
						return '<a href=#>查看詳情</a>';
					}
					}
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

	  $.post("<%=path%>/admin/eassay/del",{ids:idArray.join(',')},function(data){
		  if(data.success){
			  $.messager.alert('提示','删除成功!','info');
		  }else{
			  $.messager.alert('提示','删除失败!','info');
		  }
		  $("#list_data").datagrid('load');

	  });

  }
  
  function doSearch(){
	  var accountName = $("#accountName").val();
	  var accountId = $("#accountId").val();
	  var title = $("#title").val();
	  var categoryId = $("#categoryId").combobox('getValue');
	  var dateAfter = $("#dateAfter").datebox('getValue');
	  $("#list_data").datagrid('load',{accountName:accountName,accountId:accountId,title:title,categoryId:categoryId,dateAfter:dateAfter});
  }
  
	function setType(){
		var rows = $("#list_data").datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('info','请选择记录设置','提示');
			return;
		}
		$("#set_type_window").window("open");
		 var options = {
		            columns:[[
							{field:'id',title:'id',align:'center',hidden:true},
							{field:'cateName',title:'分类名称',align:'center',width:130,formatter:function(value,row,index){
								return '<a href=#>'+value+'</a>';
							}
							},
							{field:'orderNum',title:'排序号',align:'center',width:150}
				 	]],
				 	url:'<%=path %>/admin/category/list',
				 	pageList: [20,50,500]
				}; 
				$('#category_list_data').mygrid(options);
	}
	
	
	function closeupload(){
	//	$("#myfiles").val('');
		$('#uploadfileDiv').window('close');
	}
	
	function save_set_type(){
	      var rows = $("#list_data").datagrid("getSelections");
	      var typeId = $("#category_list_data").datagrid("getSelections")[0].id;
	      var typeName = $("#category_list_data").datagrid("getSelections")[0].cateName;
	      var accountIds = [];
	      //选择要设置分类的行
	      if (rows.length > 0) {
	          $.messager.confirm("提示", "你确定要设置吗?", function (r) {
	        	  for(var i=0,len=rows.length;i<len;i++){
	        		  accountIds.push(rows[i].id);
	        	  }
	        	  $.post("<%=path%>/admin/eassay/settype",{accountIds:accountIds.toString(),typeId:typeId,typeName:typeName},function(data){
		        	  if(data.success){
		        	 	 $.messager.alert("info",'设置成功!',"提示");
		        	 	$("#set_type_window").window("close");
		        	 	$("#list_data").datagrid('load');
		        	  }else{
		        		  $.messager.alert("info",data.message,"提示"); 
		        	  }
		          });
	          });
	      }else{
	    	  $.messager.alert("error",'请选择记录设置!',"错误");
	      }
		
	}
	
	//关闭设置分类的窗口
	function close_set_type(){
		$("#set_type_window").window("close");
	}
	
	 function del(){
		 //删除时先获取选择行
      var rows = $("#list_data").datagrid("getSelections");
      //选择要删除的行
      if (rows.length > 0) {
          $.messager.confirm("提示", "你确定要删除吗?", function (r) {
              if (r) {
                  var ids = '';
                  for (var i = 0; i < rows.length; i++) {
                 	if(i>0){
                 		ids+=',';
                 	}
                 	ids+=rows[i].id;
                  }
                  $.post('<%=path%>/admin/eassy/del',{ids:ids},function(data){
                 	 if(data.success){
                 		 $.messager.alert("提示", '删除成功!', "info");
                 		 $("#list_data").datagrid('reload');
                 	 }else{
                 		 $.messager.alert("提示", '删除失败!', "info");
                 		 $('#list_data').datagrid('reload')
                 	 }
                  });
                  
              }
          });
      }
      else {
          $.messager.alert("提示", "请选择要删除的行", "error");
      }
	 }
	
	 //设置推荐
	 function setRecommend(isRecommend){
		 var rows = $("#list_data").datagrid("getSelections");
		 var ids = [];
		 for(var i=0,len=rows.length;i<len;i++){
			 ids.push(rows[i].id);
		 }
		 $.post('<%=path%>/admin/eassay/setRecmmend',{ids:ids.join(','),isRecommend:isRecommend},function(data){
			 if(data.success){
				$.messager.alert('提示','设置成功！','info');
			 }else{
				 $.messager.alert('提示','设置失败！','error');
			 }
		 });
	 }
  </script>
</html>
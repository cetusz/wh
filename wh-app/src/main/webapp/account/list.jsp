<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/style.css">
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/mygrid.js"></script>	
     <script type="text/javascript" src="<%=path %>/resources/js/common.js"></script>	
    <script type="text/javascript" src="<%=path %>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>公众账号列表</title>
</head>
<body>

	<div class="easyui-layout" fit="true">
		
		<!-- 列表数据定义的存放表格 -->
		<table id="list_data"  title="公众账号列表"  fit="true" data-options="toolbar:'#tb'">
		</table>
		
		<div id="tb" style="padding:5px;height:auto">
		    <div style="margin-bottom:5px">
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="del()">删除</a>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="setType()">设置分类</a>
		    
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
						{field:'id',title:'公众账号id',align:'center',hidden:true},
						{field:'accountId',title:'公众账号id',align:'center'},
						{field:'chineseName',title:'公众账号名称',align:'center',width:130,formatter:function(value,row,index){
							return '<a href=#>'+value+'</a>';
						}
						},
						{field:'cateIds',title:'分类',align:'center',width:150,
					},
					{field:'bizId',title:'bizId',align:'center'},
					{field:'isShielded',title:'是否屏蔽',align:'center',width:100,
						formatter:function(value,row,index){
						   if(value){
							   return '是';
						   }else {
							   return "否";
						   }
						}
					},
					{field:'headImg',title:'头像',align:'center',width:100,formatter:function(value,row,index){
			                  if(value!=''){			
						   	 	 return "<img src='"+value+"' width=50"+" onerror='javascript:this.src=''' height=50/>";
			                  }
			                  return value;
					}}
			 	]],
			 	url:'<%=path %>/admin/publicaccountedit/list',
			 	pageList: [20,50,500]
			}; 
			$('#list_data').mygrid(options);	
			//绑定用户点击事件
			$('#list_data').datagrid({
				    onClickCell: function(index,field,value){
					    	  showDetail(index,field,value);
					 }
			});
  });
  
  function showDetail(index,field,value){
		var data = $('#list_data').datagrid('getData');
		var selectRec = data.rows[index];
		var id = selectRec.id;
		common.addTab('添加账号','<%=path %>/admin/publicaccountedit/toadd?id='+id,'icon icon-nav');
  }
  
  function add(){
	  common.addTab('添加账号','<%=path %>/admin/publicaccountedit/toadd','icon icon-nav');
  }
  
  function update(){
	  var rows = $("#list_data").datagrid("getSelections");
      if (rows.length == 1) {
 			common.addTab('添加账号','<%=path %>/admin/publicaccountedit/toadd?id='+rows[0].id,'icon icon-nav');
      }else{
     	 $.messager.alert('提示','请选择一条记录','info')
      }
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
                  $.post('<%=path%>/admin/publicaccountedit/del',{ids:ids},function(data){
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

  function doSearch(){
	        var chineseName = $("#chineseName").val();
		    var accountId = $("#accountId").val();

		   	var query = {
	   			'accountId':accountId,
	   			'chineseName':chineseName
		   	};
		   
		   $('#list_data').datagrid('load',query);
  }
</script>
</html>
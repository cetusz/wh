<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
 String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加分类</title> 
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/style.css">
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/mygrid.js"></script>	
     <script type="text/javascript" src="<%=path %>/resources/js/common.js"></script>	
    <script type="text/javascript" src="<%=path %>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
    <style type="text/css">
       body{
       	    font-size:12px;
		 	text-family:'宋体';
       }
 		textarea{
 			width:500px;
 			height:100px;
 		}
 </style>

</head>
<body>
<div id="p" class="easyui-panel" title="" style="fit:true;background:#fff;text-align:left" >
		<form id="cateForm" style="text-align:left;float:left">
             <table class="mytable" align=center border-collapse="collapse" cellspacing=0 cellspadding=10>
               	<tr>
					<td class="alignright">分类名称:</td>	
					<td> 
						<input class="easyui-validatebox" style="width:200px" type="text" 
						id="cateName" name="cateName"/> 
					</td>
				</tr>
				<tr>
					<td class="alignright">排序:</td>	
					<td> 
						<input class="easyui-validatebox" style="width:200px" type="text" 
						id="orderNum" name="orderNum"/> 
					</td>
				</tr>    
				<tr>
			    <td colspan=2>  
			     <div id="buttonDiv" style="margin:10px;text-align:center">
				    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:save()">保存</a>
			      	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="javascript:cancel()">取消</a>
	             </div>	
	            </td>
			    
			    </tr>
             </table>
		</form>
</div>
</body>
<script>
	$(function(){
		$('#cateForm').form({
		    url:"<%=path%>/admin/category/save",
		    onSubmit: function(){
		    	var isValid = $(this).form('validate');
				return isValid;
		    },
		    success:function(data){
		    	data = $.parseJSON(data);
				console.log(data);
				if(data.success)
				{
					$.messager.alert('Success','<center>操作成功</center>');
					common.refreshTabByFunc('分类列表','doSearch');
					tabClose();
				}
				else
				{
					$.messager.alert('Warning','<center>操作失败!'+data.message+"</center>");
					//tabClose();
				}
		    },
		    onLoadSuccess:function(data){
			}
		});
		 
		$('#cateForm').form('load','<%=path%>/admin/category/get?id=${id}');
	});


	function save()
	{
		$('#cateForm').submit();
	}
	
	function tabClose(){
		var jq = top.jQuery;
		jq('#tabs').tabs('close', '添加分类');
	}
	function cancel(){
		tabClose();
	}



</script>
</html>
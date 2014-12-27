<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
 String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>推荐配置列表</title> 
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
		<form id="accountForm" style="text-align:left;float:left">
             <table class="mytable" align=center border-collapse="collapse" cellspacing=0 cellspadding=10>
               	<tr>
					<td class="alignright">公众账号名称:</td>	
					<td> 
						<input class="easyui-validatebox" style="width:200px" type="text" 
						id="chineseName" name="chineseName"/> 
					</td>
				</tr>
               	<c:if test="${! empty id}">
               	<tr>
					<td class="alignright">公众账号ID:</td>	
					<td> 
					    <input type="hidden" name ="id" value=${id}/>
						<input class="easyui-validatebox" style="width:200px" type="text" 
						id="accountId" name="accountId" data-options="required:true" /> 
					</td>
			    </tr>
            
				  <tr>
					<td class="alignright">头像地址:</td>	
					<td> 
						<input class="easyui-validatebox" style="width:500px" type="text" 
						id="headImg" name="headImg"/> 
					</td>
				</tr>
			   	<tr>
					<td class="alignright">所属分类:</td>	
					<td> 
					<select id="cateIds" name="cateIds" style="width:200px" class="easyui-combobox" 
						data-options=" url:'<%=path%>/admin/category/getlist',
									valueField: 'id',
									textField: 'cateName',
									method:'get'">
							<option value="">请选择</option>
					</select>	
					<input type="hidden" name="cateNames" id="cateNames"/>
				    </td>
			    </tr>
			  	<tr>
					<td class="alignright">功能简介:</td>	
					<td> 
					<textarea name="funcintro"></textarea>
				    </td>
			    </tr>
			    
			    
			   <tr>
					<td class="alignright">微信认证:</td>	
					<td> 
					<textarea name="wxcredit"></textarea>
				    </td>
			    </tr>
			
			    
			    <tr>
					<td class="alignright">新浪认证:</td>	
					<td> 
					<textarea name="sinacredit"></textarea>
				    </td>
			    </tr>
			    
			    <tr>
					<td class="alignright">二维码地址:</td>	
					<td> 
					    <input class="easyui-validatebox" style="width:500px"  type="text" 
						id="qrCodeUrl" name="qrCodeUrl"></textarea>
				    </td>
			    </tr>
			     <tr>
					<td class="alignright">openid:</td>	
					<td> 
					    <input class="easyui-validatebox"  style="width:500px" type="text" 
						id="bizId" name="bizId"></textarea>
				    </td>
			    </tr>
			       <tr>
					<td class="alignright">上次爬取時間:</td>	
					<td> 
					    <input class="easyui-validatebox"  style="width:500px" type="text" 
						id="lastCrawlerDate" name="lastCrawlerDate"></textarea>
				    </td>
			    </tr>
			    </c:if>
			    <tr>
			    <td colspan=2>  <div id="buttonDiv" style="margin:10px;text-align:center">
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:save()">保存</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="javascript:cancel()">取消</a>
	         </div>	</td>
			    
			    </tr>
             </table>
		</form>
</div>
</body>
<script>
	$(function(){
		$('#accountForm').form({
		    url:"<%=path%>/admin/publicaccountedit/save",
		    onSubmit: function(){
		    	$("#cateNames").val($("#cateIds").combobox('getText'));
		    	var isValid = $(this).form('validate');
				return isValid;
		    },
		    success:function(data){
				data = $.parseJSON(data);
				console.log(data);
				if(data.success)
				{
					$.messager.alert('Success','<center>操作成功</center>');
					common.refreshTabByFunc('账号列表','doSearch');
					tabClose();
				}
				else
				{
					$.messager.alert('Warning','<center>操作失败!'+data.message+"</center>");
					tabClose();
				}
		    },
		    onLoadSuccess:function(data){
			}
		});
		 
		$('#accountForm').form('load','<%=path%>/admin/publicaccountedit/get?id=${id}');
	});


	function save()
	{
		$('#accountForm').submit();
	}
	
	function tabClose(){
		var jq = top.jQuery;
		jq('#tabs').tabs('close', '添加账号');
	}
	function cancel(){
		tabClose();
	}



</script>
</html>
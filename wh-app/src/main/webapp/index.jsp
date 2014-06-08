<%@ page language="java" pageEncoding="UTF-8"%>
 <%@taglib uri="/struts-tags" prefix="s" %>
<% 
  String path =request.getContextPath();
	//System.out.println(" ====== " + path);
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>微航后台管理</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/default.css">
	<script type="text/javascript" src="<%=path%>/resources/easyui/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='<%=path%>/resources/js/outlook.js'> </script>
	 <script type="text/javascript">
    var _menus = {
	basic : [ {
		"menuid" : "10",
		"icon" : "icon-sys",
		"menuname" : "节目单管理",
		"menus" : [ {
			"menuid" : "111",
			"menuname" : "频道管理",
			"icon" : "icon-nav",
			"url" : "<%=path%>/manage/channel!tolist.do?ts="+new Date()
		}, {
			"menuid" : "112",
			"menuname" : "爬虫任务管理",
			"icon" : "icon-nav",
			"url" : "<%=path%>/manage/channel!tochannellist4day.do?ts="+new Date()
		}, {
			"menuid" : "112",
			"menuname" : "搜视网频道配置管理",
			"icon" : "icon-nav",
			"url" : "<%=path%>/manage/channel!tochannelmapping.do?ts="+new Date()
		}]
	} ],
	partner : [{
		"menuid" : "20",
		"icon" : "icon-sys",
		"menuname" : "媒资管理",
		"menus" : [ {
			"menuid" : "201",
			"menuname" : "媒资列表",
			"icon" : "icon-nav",
			"url" : "<%=path%>/manage/asset!tolist.do?ts="+new Date()
		}
]}],
	point : [{
		"menuid" : "21",
		"icon" : "icon-sys",
		"menuname" : "用户管理",
		"menus" : [ {
			"menuid" : "211",
			"menuname" : "用户管理",
			"icon" : "icon-nav",
			"url" : "<%=path%>/manage/user_tolist.do?ts="+new Date()
		}]


	}]
};

</script>
<Style>
	#css3menu li{ float:left; list-style-type:none;}
#css3menu li a{	color:#fff; padding-right:20px;}
#css3menu li a.active{color:yellow;}
</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">

	  <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
            <div id='wnav' class="easyui-accordion" fit="true" border="false">
		
			</div>

    </div>
	<div data-options="region:'south',border:true" style="height:50px;padding:10px;text-align:center">爬虫管理系统v1.0</div>
	  <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(<%=path %>/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head"><s:property value="#session.user.userName"/> 你好！ <a href="#" id="editpass" onclick="openPassword()">修改密码</a> <a href="system!logout.do" id="loginOut">安全退出</a></span>

        <span style="padding-left:10px; font-size: 16px; float:left;"><img src="<%=path %>/images/blocks.gif" width="20" height="20" align="absmiddle" /> 爬虫管理</span>
		
		<ul id="css3menu" style="padding:0px; margin:0px;list-type:none; float:left; margin-left:40px;">
				<li ><a class="active" name="basic" href="javascript:;" title="节目单管理">节目单管理</a></li>
				<li ><a name="partner" href="javascript:;" title="节目单管理">媒资管理</a></li>
				<li><a name="point" href="javascript:;" title="用户管理">用户管理</a></li>
		</ul>
    </div>
    <div region="south" split="true" >
        <div class="footer" >爬虫管理v1.0@coship.com.cn</div>
    </div>
	<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎页面" style="padding:20px;overflow:hidden;" id="home">
				<h2>欢迎使用爬虫管理系统</h2>
			</div>
		</div>
    </div>
    
    <div id="password" class="easyui-window" title="修改密码" data-options="modal:true,closed:true,iconCls:'icon-save',tools:'#tt'" style="width:300px;height:200px;">
    	<form id="ff" method="post" action="<%=path%>/manage/system!login.do">
	    	<table align="center">
	    		<tr>
	    			<td>旧密码:</td>
	    			<td><input class="easyui-validatebox" type="text" id="oldPassword" name="oldPassword" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>新密码:</td>
	    			<td><input class="easyui-validatebox" type="text" id="newPassword" name="newPassword" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    	<input type="hidden" name="userId" id="userId" value="<s:property value="#session.user.userId"/>"/>
	    </form>
	     <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">修改</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清除</a>
	    </div>
    </div>
    <script>
        function openPassword(){
             $("#password").window('open');
        }
		function submitForm(){
		     var oldp = $("#oldPassword").val();
             var newp = $("#newPassword").val();
             var dbp = '<s:property value="#session.user.passWord"/>';
             var userId = $("#userId").val();
             if(dbp!=oldp){
            	 $.messager.alert('提示','旧密码输入有误!','error');
				 return;
             }
        	 
			$.post('<%=path%>/manage/user_editPassword.do?userId='+userId+"&newPassword="+newp,function(data){
				$.messager.alert('提示',data.info,'info');
			});
			 $("#password").window('close');
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>s
</body>
</html>
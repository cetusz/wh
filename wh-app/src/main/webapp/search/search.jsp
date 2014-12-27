<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% 
     String path =request.getContextPath();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>搜索界面</title>
</head>
<body>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<div id="mainPanle" region="center" style="overflow-y:hidden">
        <div id="searchDiv" class="easyui-pannel" style="width:1000px;height:800px;margin:auto 0;text-align:center">
          <form method="post" action="<%=path%>/admin/search">
            <input type="text" name="keyWord" size=100 /><input  type="button" value="微航搜索"/>
          </form>
          <div>
         <input type="radio" name="searchType" value="account"/>账户 <input type="radio" name="searchType" value="eassay"/>文章
        </div>
        <div id="result">
        
        </div>
        </div>
    </div>
</body>
</html>
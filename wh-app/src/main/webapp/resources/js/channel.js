
/**
 * 频道管理前端代码逻辑封装
 * @author 907708
 */

var channel = {};
(function(){
	channel.initPage = function(){
		 //初始化datagrid
		 var gridOptions = {
				   columns:[[   {field:'id',title:'频道ID',align:'center',width:80},
								{field:'channelName',align:'center',width:200,title:'频道名称'},
								{field:'assorts',align:'center',width:100,title: '频道分类',
									formatter:function(value,row,index){
									               if(value == '0')
									            	   return 'OTT频道';
									               if(value == '1')
									            	   return '电视频道';
								}},
								{field:'playAssetId',align:'center',title:'ASSETID',width:120},
								{field:'videoQuality',align:'center',width:100,title:'高标清'},
								{field:'status',align:'center',title:'状态',width:120,hidden:true},
								{field:'_operate',align:'center',title:'操作',width:120,
									formatter: function(value,row,index){
										return "<a href='javascript:void' onclick=\"common.addTabs('epg','../cms-manager/manage/event/pagelist?channelId="+row.id+"','icon-nav')\">节目单</a>&nbsp;&nbsp;<a href=''>DVB设置</a>";
									}
								}
					 		   ]],
					url:'../channel/list'
		 };
		 $("#list_data").mygrid(gridOptions);
		 //初始化下拉列表等todo
	}
	channel.add = function(){
		$("<div id='addChannelWindow'></div>").window({
			width : 800,
			modal : true,
			href:'../channel/add',
			title : '添加频道',
			onClose : function() {
				$(this).dialog('destroy');
				//刷新 重新加载当前页datagrid的数据  
				$("#list_data").datagrid('reload');
			}
		});
	};
	//查询方法
	channel.search = function(){
		var condition = {
				channelName : $("#channelName").val(),
				playAssetId : $("#playAssetId").val(),
				assorts		: $("#assorts").combobox('getValue'),
				status		: $("#status").combobox('getValue'),
				tsId		: $("#tsId").val(),
				serviceId	: $("#serviceId").val()
		};
		$("#list_data").datagrid('reload',condition);
	};
	channel.del = function(){
		var rows = $("#list_data").datagrid("getSelections");
        if(rows.length==0){
			  $.messager.alert("提示","请选择","info");
			  return;
        }
        var ids = "";
        for(var i=0,len=rows.length;i<len;i++){
			  if(i>0){
				  ids+=",";
			  }
			  ids += rows[i].id;
        }
		$.post('../channel/del',{ids:ids},function(data){
			if(data.success)
				$.messager.alert('提示',data.message,'info');
				$("#list_data").datagrid('reload');
		});
	};
	channel.update = function(){
		var rows = $("#list_data").datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert('提示','请选择一条记录','error');
			return;
		}
		if(rows.length>1){
			$.messager.alert('提示','只能选择一条记录','error');
			return;
		}
		var id = rows[0].id;
		$("<div id='editChannelWindow'></div>").window({
			width : 800,
			modal : true,
			href:'../channel/edit/'+id,
			title : '修改频道',
			onClose : function() {
				$(this).dialog('destroy');
				//刷新 重新加载当前页datagrid的数据  
				$("#list_data").datagrid('reload');
			}
		});
	};
	channel.save = function(formname){
		$.messager.progress();
		$("#"+formname).submit();
	};

})();


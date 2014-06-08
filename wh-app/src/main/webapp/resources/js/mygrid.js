/**
 * 
 * options 为hash对象，基于datagrid的标准配置
 * 
 */
(function($){
	//给表头添加控制字段隐现
	var createGridHeaderContextMenu = function(e, field) {
		e.preventDefault();
		var grid = $(this);/* grid本身 */
		var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
		if (!headerContextMenu) {
			var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
			var fields = grid.datagrid('getColumnFields');
			for ( var i = 0; i < fields.length; i++) {
				var fildOption = grid.datagrid('getColumnOption', fields[i]);
				if (!fildOption.hidden) {
					$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
				} else {
					$('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
				}
			}
			headerContextMenu = this.headerContextMenu = tmenu.menu({
				onClick : function(item) {
					var field = $(item.target).attr('field');
					if (item.iconCls == 'icon-ok') {
						grid.datagrid('hideColumn', field);
						$(this).menu('setIcon', {
							target : item.target,
							iconCls : 'icon-empty'
						});
					} else {
						grid.datagrid('showColumn', field);
						$(this).menu('setIcon', {
							target : item.target,
							iconCls : 'icon-ok'
						});
					}
				}
			});
		}
		headerContextMenu.menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	};
	$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
	
	$.fn.mygrid = function(options){
		var defaultOptions = {
		        title:'',  
		        iconCls:'icon-edit',//图标  
		        width: 1000,  
		        height: '90%',  
		        nowrap: false,  
		        striped: true,  
		        border: true,  
		        collapsible:false,//是否可折叠的  
		        fit: true,//自动大小  
		        method:'post',  
		        remoteSort:false,
		        url:'#',
		        //idField:'channelId',
		        pageSize:20,
		        pageNumber:1,
		        pageList: [20,50,100],
		        singleSelect:false,//是否单选  
		        pagination:true,//分页控件  
		        rownumbers:true,//行号  
		        frozenColumns:[[  
		            {field:'ck',checkbox:true}  
		        ]]
		};
		var options = $.extend(defaultOptions,options);
		var _self = this;
		if(options.pagination){
			$(_self).datagrid(options);
			var p = $(_self).datagrid('getPager');  
		    $(p).pagination({
		        pageSize: options.pageSize,//每页显示的记录条数，默认为20  
		        pageList: options.pageList,//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		        pageNumber:1
		    });
		}else{
			delete options['pageSize'];
		    delete options['pageNumber'];
		    delete options['pageList'];
		    $(_self).datagrid(options);
		}
	}
	
})(jQuery);
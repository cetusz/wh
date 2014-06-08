function addTopTab(url, title,icon) {
		var jq = top.jQuery;
		if (!jq('#tabs').tabs('exists', title)) {
			jq('#tabs').tabs('add', {
				title : title,
				content : createFrame(url),
				closable : true,
				icon : icon
			});
		} else {
			$.messager.confirm('确认', title+'页面已经打开，确认要重新打开吗?', function(r){
				if (r){
					jq('#tabs').tabs('close', title);
					jq('#tabs').tabs('add', {
						title : title,
						content : createFrame(url),
						closable : true,
						icon : icon
					});
				}
			});
		}
	}
	
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}
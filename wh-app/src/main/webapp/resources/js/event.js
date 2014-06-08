/**
 * 节目单相关页面逻辑
 * @author 907708
 */
var events = {};
(function(){
	events.initTabs = function(){
		
	};
	events.queryNextWeek = function(weekend){
		
	};
	
	events.search = function(){
		var condition = $("#channelName").val();
		$.post('../channel/all',{channelName:condition},function(data){
			$('#channelTree').tree('loadData',data);
		});
		
		
//		if(condition){
//			var newData = {};
//			newData.id = -1;
//			newData.text = "全部频道";
//			newData.children = [];
//			var result = [];
//			$(data.children).each(function(index,item){
//				var reg = new RegExp(condition);
//				if(reg.test($(item).text)){
//					newData.children.push($(item));
//				}
//			});
//			result.push(newData);
//			$("#channelTree").tree('loadData',newData);
//		}
	}
	
})();
(function(_win){
	_win.loading = {};
	loading.start = function(){
		 $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(document).height()+'px'}).appendTo("body");  
		 $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理,请稍..").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});  
	};
	loading.end = function(){
	     $(".datagrid-mask").remove();  
	     $(".datagrid-mask-msg").remove();         
	};
})(window); 
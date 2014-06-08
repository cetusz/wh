var common = {};
(function(){
	//添加tab方法
	common.addTabs = function(subtitle, url, icon) {
			var jq = top.jQuery;
			var createFrame = function(url) {
				var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
				return s;
			}
			if (!jq('#tabs').tabs('exists', subtitle)) {
				jq('#tabs').tabs('add', {
					title : subtitle,
					content : createFrame(url),
					closable : true,
					icon : icon
				});
			} else {
				$.messager.confirm('Confirm', subtitle+'页面已经存在，确认覆盖吗?', function(r){
					if (r){
						jq('#tabs').tabs('close', subtitle);
						jq('#tabs').tabs('add', {
							title : subtitle,
							content : createFrame(url),
							closable : true,
							icon : icon
						});
					}
				});
			
	    }
	};
	
	common.refreshTabByFunc = function(title, function_name){  
		var jq = top.jQuery;
	    var refresh_tab = title?jq('#tabs').tabs('getTab',title):jq('#tabs').tabs('getSelected');  
	    
	    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
		    var _refresh_ifram = refresh_tab.find('iframe')[0];  
		    _refresh_ifram.contentWindow[function_name]();
	    }  
	};
	
	common.refreshTabByUrl = function(title, url){  
		var jq = top.jQuery;
	    var refresh_tab = title?jq('#tabs').tabs('getTab',title):jq('#tabs').tabs('getSelected');  
	    
	    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
		    var _refresh_ifram = refresh_tab.find('iframe')[0];  
		 	var refresh_url = url?url:_refresh_ifram.src;  
		 	_refresh_ifram.contentWindow.location.href=refresh_url;  
	    }  
	};
	
   
	common.dateUtils = function(date){
		var now = date||new Date();                 //当前日期   
		this.nowDayOfWeek = now.getDay();         //今天本周的第几天   
		this.nowDay = now.getDate();              //当前日   
		this.nowMonth = now.getMonth();           //当前月   
		this.nowYear = now.getFullYear();             //当前年   
		//nowYear += (nowYear < 2000) ? 1900 : 0;  //
		
	}

			//获得本周的开始日期   
	common.dateUtils.prototype.getWeekStartDate =  function(){
				var dayofweek = this.nowDayOfWeek == 0 ? this.nowDay - this.nowDayOfWeek-6 : this.nowDay - this.nowDayOfWeek+1;
			    var weekStartDate = new Date(this.nowYear, this.nowMonth, dayofweek);    
			    return weekStartDate;   
	};
	//获得本周的结束日期 
	common.dateUtils.prototype.getWeekEndDate = function(){
		  var dayofweek = this.nowDayOfWeek == 0 ? this.nowDay  :  this.nowDay + (7 - this.nowDayOfWeek);
		  var weekEndDate = new Date(this.nowYear, this.nowMonth, this.nowDay + (7 - dayofweek));    
		  return weekEndDate; 
	},
	common.dateUtils.prototype.formatDate = function(date){
		    var myyear = date.getFullYear();   
		    var mymonth = date.getMonth()+1;   
		    var myweekday = date.getDate();    
		       
		    if(mymonth < 10){   
		        mymonth = "0" + mymonth;   
		    }    
		    if(myweekday < 10){   
		        myweekday = "0" + myweekday;   
		    }   
		    return (myyear+"-"+mymonth + "-" + myweekday); 
	},
	common.dateUtils.prototype.string2Date = function(dateStr){
		var date = dateStr.split("-");
		var result = new Date();
		result.setYear(date[0]);
		result.setMonth(parseInt(date[1])-1);
		result.setDate(date[2]);
		return result;
	};
	//获得本月的开始日期   
	common.dateUtils.prototype.getMonthStartDate = function(){   
	    var monthStartDate = new Date(this.nowYear, this.nowMonth, 1);    
	    return this.formatDate(monthStartDate);   
	};  
	  
	//获得本月的结束日期   
	common.dateUtils.prototype.getMonthEndDate = function(){   
	    var monthEndDate = new Date(this.nowYear, this.nowMonth, this.getMonthDays(nowMonth));    
	    return this.formatDate(monthEndDate);   
	};   
	  
	//获得本季度的开始日期   
	common.dateUtils.prototype.getQuarterStartDate = function(){   
	    var quarterStartDate = new Date(this.nowYear, this.getQuarterStartMonth(), 1);    
	    return this.formatDate(quarterStartDate);   
	};   
	  
	//或的本季度的结束日期   
	common.dateUtils.prototype.getQuarterEndDate = function(){   
	    var quarterEndMonth = getQuarterStartMonth() + 2;   
	    var quarterStartDate = new Date(this.nowYear, quarterEndMonth, this.getMonthDays(quarterEndMonth));    
	    return this.formatDate(quarterStartDate);   
	};
	//获得本周的数组
	common.dateUtils.prototype.getThisWeekArray = function(){
		var array = [];
		var startDate = this.getWeekStartDate();
		array.push(this.formatDate(startDate));
		for(var i = 1;i<7;i++){
				var d = new Date()
				d.setDate(startDate.getDate()+ i);
				array.push(this.formatDate(d));
		}
		return array;
	};
	//获取下一周的时间数组
	common.dateUtils.prototype.getNextWeekArray = function(date){
		var array = [];
		var time = this.string2Date(date);
		var firstdayofNextweek = time;
		firstdayofNextweek.setDate(time.getDate()+1);
		array.push(this.formatDate(firstdayofNextweek));
		for(var i = 1;i<7;i++){
				var d = new Date();
				d.setYear(firstdayofNextweek.getFullYear());
				d.setMonth(firstdayofNextweek.getMonth());
				d.setDate(firstdayofNextweek.getDate()+ i);
				array.push(this.formatDate(d));
		}
		return array;
		
		
	};
	common.dateUtils.prototype.getPreWeekArray = function(date){
		var array = [];
		var time = this.string2Date(date);
		var lastdayofPreWeek = time;
		lastdayofPreWeek.setDate(time.getDate()-1);
		this.nowDayOfWeek = lastdayofPreWeek.getDay();         //今天本周的第几天   
		this.nowDay = lastdayofPreWeek.getDate();              //当前日   
		this.nowMonth = lastdayofPreWeek.getMonth();           //当前月   
		this.nowYear = lastdayofPreWeek.getYear();
		for(var i = 6;i>0;i--){
				var d = new Date();
				d.setYear(lastdayofPreWeek.getFullYear());
				d.setMonth(lastdayofPreWeek.getMonth());
				d.setDate(lastdayofPreWeek.getDate()- i);
				array.push(this.formatDate(d));
		}
		array.push(this.formatDate(lastdayofPreWeek));
		return array;
	};
})();

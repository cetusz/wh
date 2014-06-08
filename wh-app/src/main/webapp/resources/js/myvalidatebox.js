/**
 * 扩张validatebox校验
 * isNum：判断是否为数字
 * betweenNum:判断一个数字在一个范围内
 * easyui 在version 1.3.2开始支持联合校验
 * 使用方式: validType:['isNum','betweenNum[0,20]']
 * @author 907708
 */
(function($){
	var myvalidate = {
		isNum:{
	        	  validator: function(value){
	                  return !isNaN(value);
	              },
	              message: "Please enter a valid number."
	    },
		betweenNum:{
            validator: function(value,args) {
                return value>args[0]&& value<=args[1];
                	
            },
            message: "Please enter a valid number between {0} and {1}."
        },
        isIp:{
        	validator:function(value){
        		var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
        		return exp.test(value);
        	},
        	message:"please enter a valid IP address"
        },
        afterDate:{
        	validator:function(value,args){
        		var target = $("input[id="+args[0]+"]").val();
        		return value>target;
        	},
        	message:'请输选择大于{0}的日期'
        },
        beforeDate:{
        	validator:function(value,args){
        		var target = $("input[id="+args[0]+"]").val();
        		return value<target;
        	},
            message:'请选择小于{0}的日期'
        	
        }
        
	};
	$.extend($.fn.validatebox.defaults.rules,myvalidate);
})(jQuery);
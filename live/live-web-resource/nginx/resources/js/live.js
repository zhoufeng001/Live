/**
 * 服务端Ajax返回内容
 */
var AjaxResult = function(result){
	
	/**
	 * 是否成功
	 */
	this.success = 0;
	
	/**
	 * 错误代码
	 */
	this.code = 0 ;
	
	/**
	 * 返回消息
	 */
	this.msg = "";
	
	/**
	 * 具体数据
	 */
	this.data = null;
	
	var resultJson = eval('(' + result + ')'); 
	
	if(resultJson){
		this.success = resultJson.success ;
		this.data = resultJson.data ;
		this.code = resultJson.code ;
		this.msg = resultJson.msg ;
	}
	
	/**
	 * 判断是否成功
	 */
	this.isSuccess = function(){
		return this.success === 1 ;  
	}
	
}


/**
 * 给Date对象添加format方法
 */
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} 
	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		}
	}
	return format ;
}

/** 
 * 给String对象添加trim方法  
 */  
String.prototype.trim=function() {  
    return this.replace(/(^\s*)|(\s*$)/g,'');  
};   
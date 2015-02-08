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
 * Json对象
 */
var JsonObject = function(jsonString){
	if(!jsonString){
		return null ;
	}
	var jsonObj = eval('(' + jsonString + ')'); 
	return jsonObj;
}


Array.prototype.remove = function(s) {     
    for (var i = 0; i < this.length; i++) {     
        if (s == this[i])     
            this.splice(i, 1);     
    }     
}     
    
/**   
 * Map对象
 */    
function Map() {     
    /** 存放键的数组(遍历用到) */    
    this.keys = new Array();     
    /** 存放数据 */    
    this.data = new Object();     
         
    /**   
     * 放入一个键值对   
     * @param {String} key   
     * @param {Object} value   
     */    
    this.put = function(key, value) {     
        if(this.data[key] == null){     
            this.keys.push(key);     
        }     
        this.data[key] = value;     
    };     
         
    /**   
     * 获取某键对应的值   
     * @param {String} key   
     * @return {Object} value   
     */    
    this.get = function(key) {     
        return this.data[key];     
    };     
         
    /**   
     * 删除一个键值对   
     * @param {String} key   
     */    
    this.remove = function(key) {     
        this.keys.remove(key);     
        this.data[key] = null;     
    };     
         
    /**   
     * 遍历Map,执行处理函数   
     *    
     * @param {Function} 回调函数 function(key,value,index){..}   
     */    
    this.each = function(fn){     
        if(typeof fn != 'function'){     
            return;     
        }     
        var len = this.keys.length;     
        for(var i=0;i<len;i++){     
            var k = this.keys[i];     
            fn(k,this.data[k],i);     
        }     
    };     
         
    /**   
     * 获取键值数组(类似Java的entrySet())   
     * @return 键值对象{key,value}的数组   
     */    
    this.entrys = function() {     
        var len = this.keys.length;     
        var entrys = new Array(len);     
        for (var i = 0; i < len; i++) {     
            entrys[i] = {     
                key : this.keys[i],     
                value : this.data[i]     
            };     
        }     
        return entrys;     
    };     
         
    /**   
     * 判断Map是否为空   
     */    
    this.isEmpty = function() {     
        return this.keys.length == 0;     
    };     
         
    /**   
     * 获取键值对数量   
     */    
    this.size = function(){     
        return this.keys.length;     
    };     
         
    /**   
     * 重写toString    
     */    
    this.toString = function(){     
        var s = "{";     
        for(var i=0;i<this.keys.length;i++,s+=','){     
            var k = this.keys[i];     
            s += k+"="+this.data[k];     
        }     
        s+="}";     
        return s;     
    };     
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



function gotoLogin(){  
	var url = ctx + "/user/loginView.htm" ;
//	var Body = $("body");
//	var redirect = Body.attr("redirect");
//	if(redirect){  
//		url += "?redirect=" + redirect;
//	}
	location.href = url;  
}

function gotoQQLogin(){  
	var url = ctx + "/qq/gotoLogin.htm" ;
//	var Body = $("body");
//	var redirect = Body.attr("redirect");
//	if(redirect){  
//		url += "?redirect=" + redirect;
//	}
	location.href = url;
}

function gotoSinaLogin(){  
	var url = ctx + "/sina/gotoLogin.htm" ;
//	var Body = $("body"); 
//	var redirect = Body.attr("redirect");
//	if(redirect){  
//		url += "?redirect=" + redirect;
//	}
	location.href = url;
}

function gotoLogout(){  
	var url = ctx + "/user/doLogout.htm" ;
//	var Body = $("body");
//	var redirect = Body.attr("redirect");
//	if(redirect){  
//		url += "?redirect=" + redirect;
//	}
	location.href = url;
}


function html2Escape(text) {  
	 return text.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
}


/**
 * 页面加载完后需要初始化的事情
 */
function init(){
	$("#searchBut").click(function(){
		alert("亲，搜索功能还未实现哦！");
	});
}

$(init);
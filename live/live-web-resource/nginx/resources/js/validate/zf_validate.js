// JavaScript Document
function ValiData(){
	
	msgDiv =  $(document.createElement("div"));		//创建一个跟随鼠标移动的div 显示提示消息
	msgDiv.css({
			"position":"absolute",
			"min-height":"30px",   
			"width":"150px",  
			"background-color":"#f4f4f1",
			"z-index":"8888",
			"font-size":"12px",  
			"color":"red",       
			"line-height":"30px",
			"display":"none",
			"border-top":"none",
			"text-align":"center"  ,
			"filter":"alpha(opacity=30)",
			"opacity":"1"  
	});
	
	alertDiv = $(document.createElement("div"));   //表单提交验证错误后的提示框      
	alertDiv.css({
			"position":"absolute",
			"min-height":"40px", 
			"width":"300px",
			"background-color":"#f4f4f1",
			"z-index":"8888",
			"font-size":"12px",
			"color":"red",
			"line-height":"30px",
			"display":"none",  
			"border-top":"none",
			"text-align":"center"  ,
			"filter":"alpha(opacity=30)",          
			"opacity":"1"  
	});
	
	$("body").append(msgDiv);
	$("body").append(alertDiv);
	
	//给所有input元素绑定失去焦点事件
	$(":input").bind("blur" , function(){
				entity = dom2object($(this));	//包装成Eneity对象										  
				validateStart(entity) ;	
    });
	
	/* 验证一组对象，验证通过返回true类型，否者false */ 
	this.validateGroup = function(ids){
		var result = true ;
		$.each(ids, function(i , v){
			target = $("#" + v);
			entity = dom2object(target);	//包装成Eneity对象
			if(!validateStart(entity)){;		//开始验证
				result = false; 
			}
		});
		if(result == false){	//验证不通过，就显示提示框
			alertDiv.html( "<span style=\"width:100% ; height :8px;background:#f96767;float:left\"></span><span style='margin-left:10px;margin-right:10px;width:90%;float:left;font-size:14px;'>请正确填写表单后提交</span>");
			width =  eval((document.documentElement.clientWidth / 2) - (parseInt(alertDiv.css("width").match("\\d+")) / 2));
			height = eval((document.documentElement.clientHeight / 2) - (parseInt(alertDiv.css("height").match("\\d+")) / 2)) ;  
			alertDiv.css("left" , width);
			alertDiv.css("top" , height); 
			alertDiv.fadeIn("slow");
			window.setTimeout(function(){
				alertDiv.fadeOut("slow");
			},2000);
		}       
		return result ;
	}
	
	/* 验证表单  参数为表单的id ，会自动为所有表单加上onsubmit函数*/
	this.validateForm = function(formId){
 	   //绑定表单提交事件
		$("#" + formId).submit(function(){
			var result = true ;
			inputs = $("#" + formId + " :input") ;
			$.each(inputs , function( i , v){
				v = $(v);
				entity = dom2object(v);	//包装成Eneity对象
				if(!validateStart(entity)){;		//开始验证
					result = false; 
				}
			});
			if(result == false){	//验证不通过，就显示提示框
					alertDiv.html( "<span style=\"width:100% ; height :8px;background:#f96767;float:left\"></span><span style='margin-left:10px;margin-right:10px;width:90%;float:left;font-size:14px;'>请正确填写表单后提交</span>");
					width =  eval((document.documentElement.clientWidth / 2) - (parseInt(alertDiv.css("width").match("\\d+")) / 2));
					height = eval((document.documentElement.clientHeight / 2) - (parseInt(alertDiv.css("height").match("\\d+")) / 2)) ;  
					alertDiv.css("left" , width);
					alertDiv.css("top" , height); 
					alertDiv.fadeIn("slow");
					window.setTimeout(function(){
						alertDiv.fadeOut("slow");
					},2000);
			}
			return result ;	
		});
	}
	
}

/* 将一个jquery得到的dom对象包装成一个ValidataObject对象 */
function dom2object(target){
	var entity = new ValidataEneity();
	entity.setTarget(target);
	entity.setVal(target.val());
	entity.setMaxLen(target.attr("maxLen"));
	entity.setMinLen(target.attr("minLen"));
	entity.setMaxVal(target.attr("maxVal"));
	entity.setMinVal(target.attr("minVal"));
	entity.setNullable(target.attr("nullable"));
	entity.setIsNumber(target.attr("isNumber"));
	entity.setRegex(target.attr("regex"));
	entity.setNotVals(target.attr("notVals"));
	entity.setMsgId(target.attr("msgId"));
	entity.setRelObj(target.attr("relObj"));
	entity.setGlobalErrorMsg(target.attr("globalErrorMsg"));
	entity.setLenErr(target.attr("lenErr"));
	entity.setValErr(target.attr("valErr"));
	entity.setNumberErr(target.attr("numberErr"));
	entity.setRegexErr(target.attr("regexErr"));
	entity.setNotnullErr(target.attr("notnullErr"));
	entity.setNotValErr(target.attr("notValErr"));
	entity.setRelErr(target.attr("relErr"));
	entity.setRelErr(target.attr("relErr"));
	entity.setPromptstr(target.attr("promptstr"));
	return entity ;
}

/* 开始验证 ， 参数为ValidataObject对象 */
function validateStart(entity){
		var result = true;	
	    var target = entity.getTarget();
		var nullable = entity.getNullable();  
		var val = entity.getVal();      
		var maxLen = entity.getMaxLen(); 	
		var minLen = entity.getMinLen(); 		
		var maxVal = entity.getMaxVal(); 		
		var minVal = entity.getMinVal(); 	
		var isNumber = entity.getIsNumber(); 	
		var regex  = entity.getRegex(); 	
		var notVals = entity.getNotVals(); 
		var relObj = entity.getRelObj();
		
		var msgId = entity.getMsgId(); 	
		var globalErrorMsg= entity.getGlobalErrorMsg(); 
		var lenErr= entity.getLenErr(); 	
		var valErr  = entity.getValErr();
		var numberErr = entity.getNumberErr(); 	
		var regexErr = entity.getRegexErr(); 	
		var notnullErr= entity.getNotnullErr(); 	
		var notValErr = entity.getNotValErr(); 	
		var relErr = entity.getRelErr();
		var promptstr = entity.getPromptstr();
		
		var showErrorMsg = "";	//将要显示的错误消息
		
		if(isNotNull(nullable) && result == true){
				if(!isNotNull(val)){
					var showErrorMsg = notnullErr == null ? globalErrorMsg : notnullErr ;  
					result = false ;
				}
		}
		
		if(isNotNull(isNumber) && result == true){
				if(isNaN(val)){
					var showErrorMsg = numberErr == null ? globalErrorMsg : numberErr ;  
					result = false ;
				}
		}
		
		
		if(isNotNull(maxLen) && result == true){
				if( val.length > maxLen){
					var showErrorMsg = lenErr == null ? globalErrorMsg : lenErr ;  
					result = false ;
				}
		}
		
		if(isNotNull(minLen) && result == true){
				if( val.length < minLen){
					var showErrorMsg = lenErr == null ? globalErrorMsg : lenErr ;  
					result = false ;
				}
		}
		
	  if(isNotNull(maxVal) && result == true){
				if( parseInt(val) > maxVal ){
					var showErrorMsg = valErr == null ? globalErrorMsg : valErr ;  
					result = false ;
				}
		}
		
	   if(isNotNull(minVal) && result == true){
				if( parseInt(val) < minVal ){
					var showErrorMsg = valErr == null ? globalErrorMsg : valErr ;  
					result = false ;
				}
		}
		
	  if(isNotNull(regex) && result == true){
		  		re = new RegExp(regex);
				if(!re.test(val)){ 
					var showErrorMsg = regexErr == null ? globalErrorMsg : regexErr ;  
					result = false ;
				}
		}
		
	  if(isNotNull(relObj) && result == true){
			relVal = $("#" + relObj).val();  
			if(val != relVal){    
				var showErrorMsg = relErr == null ? globalErrorMsg : relErr ;  
				result = false;	  
			}
		}
		
	  if(isNotNull(notVals) && result == true){
				vs = notVals.split(",");
				for(var i = 0 ; i < vs.length ; i++){
					if(vs[i] == val){ 
						var showErrorMsg = notValErr == null ? globalErrorMsg : notValErr ;  
						result = false ;
					}
				}
	}
	
	if(result){
			showErrorMsg = "";
			target.removeClass("errorStyle")
			target.unbind("mousemove");					//解除鼠标移动事件
			target.unbind("mouseover");
	}else{
			target.addClass("errorStyle")
			target.bind("mouseover" , function(event){
					x = event.clientX ;      
					y = event.clientY ;       
					msgDiv.html( "<span style=\"width:100% ; height :5px;background:#f96767;float:left;position:relative;z-index:1;\"></span><span style='position:relative;z-index:1;margin-left:10px;margin-right:10px;width:130px;float:left;'>" + showErrorMsg + "</span>");  
					msgDiv.css("left" , x);     
					msgDiv.css("top" , y);    
					msgDiv.css("display" , "");
					target.mousemove(function(event){
						x = event.pageX - 20 ;
						y = event.pageY + 10 ; 
						msgDiv.css("left", x);
						msgDiv.css("top" , y);
					});
					target.bind("mouseout" , function(){
						msgDiv.css("display" , "none"); 							   
					});    
			});	
	}      
	if(isNotNull(msgId)){         
		$("#" + msgId).html(showErrorMsg);  
	}
	return result   ;
}
  
/*判断一个对象是否为空 ，不为空返回true ， 为空返回false */
function isNotNull(obj){
	if(obj == null || "" == obj.toString()){
		return false ;	
	}else{
		return true;	
	}
}

/* 封装要验证对象的验证信息 */
function ValidataEneity(){
	var target ;
	var val  ;			//该验证对象的值
	var nullable;		//能否为空
	var maxLen ;  		//最大长度	（用于字符串，或多选）
	var minLen ;			//最小长度	
	var maxVal ;		//最大值	（用于验证数字类型）
	var minVal ;		//最小值	 （用户验证数字类型）
	var isNumber ;		//是否为数字	
	var regex ;			//正则匹配
	var notVals;	//不能够为的值	
	var relObj ;	//相对元素的id ，用户判断该对象的值 是否与制定的对象的值相同，用于确认密码的操作
	
	var promptstr ;   //提示信息，验证前对用户的提示消息
	
	var msgId ;		//提示消息显示的对象id
	var globalErrorMsg;	//全局默认错误
	var lenErr;		//长度错误
	var valErr;		//值错误
	var numberErr ;		//不是数字
	var regexErr;		//正则不匹配
	var notnullErr;		//参数为空
	var notValErr;		//包含不合法值
	var relErr ;		//当该对象值 与 相对对象值不匹配时错误消息
	
	this.setPromptstr = function(promptstr){
		this.promptstr = promptstr ;
	}
	
	this.getPromptstr = function(){
		return this.promptstr ;	
	}
	
	this.setRelErr = function(relErr){
		this.relErr = relErr ;	
	}
	
	this.getRelErr = function(){
		return this.relErr ;	
	}
	
	this.setRelObj = function(relObj){
		this.relObj = relObj ;	
	}
	
	this.getRelObj = function(){
		return this.relObj ;
	}
	
	this.setTarget = function(target){
		this.target = target;
	}
	
	this.getTarget = function(){
		return this.target ;	
	}
	
	this.setVal = function(val){
		this.val = val ;	
	}
	
	this.getVal = function(){
		return this.val ;
	}
	
	this.setMsgId = function(msgId){
		this.msgId = msgId ;
	}
	
	this.getMsgId = function(){
		return this.msgId ;
	}
	
	this.setGlobalErrorMsg = function(globalErrorMsg){
		 if(isNotNull(globalErrorMsg))
			this.globalErrorMsg = globalErrorMsg;
		 else
			this.globalErrorMsg = "输入不正确";  
	}
	
	this.getGlobalErrorMsg = function(){
		return this.globalErrorMsg;
	}
	
	this.setLenErr = function(lenErr){
			this.lenErr = lenErr ;
	}
	
	this.getLenErr = function(){
		return this.lenErr ;	
	}
	
	this.setValErr = function(valErr){
			this.valErr = valErr ;
	}
	
	this.getValErr = function(){
		return this.valErr ;	
	}
	
	this.setNumberErr = function(numberErr){
			this.numberErr = numberErr;
	}
	
	this.getNumberErr = function(){
		return this.numberErr ;	
	}
	
	this.setRegexErr = function(regexErr){
			this.regexErr = regexErr ;
	}
	
	this.getRegexErr = function(){
		return this.regexErr ;	
	}
	
	this.setNotnullErr = function(notnullErr){
			this.notnullErr = notnullErr ;
	}
	
	this.getNotnullErr = function(){
		return this.notnullErr ;	
	}
	
	this.setNotValErr = function(notValErr){
			this.notValErr = notValErr ;
	}
	
	this.getNotValErr = function(){
		return this.notValErr ;	
	}
	
	this.getMaxLen = function(){
		return this.maxLen ;	
	}
	
	this.setMaxLen = function(maxLen){
		this.maxLen = maxLen ;    
	}
	
	this.setMinLen = function(minLen){
		this.minLen = minLen ;
	}
	
	this.getMinLen = function(minLen){
		return this.minLen ;	
	}
	
	this.setMaxVal = function(maxVal){
		this.maxVal = maxVal ;
	}
	
	this.getMaxVal = function(){
		return this.maxVal ;	
	}
	
	this.setMinVal = function(minVal){
		this.minVal = minVal ;	
	}
	
	this.getMinVal = function(){
		return this.minVal ;	
	}
	
	this.setNullable = function(nullable){
		this.nullable = nullable ;
	}
	
	this.getNullable = function(){
		return this.nullable ;	
	}
	
	this.setIsNumber = function(isNumber){
		this.isNumber = isNumber ;
	}
	
	this.getIsNumber = function(){
		return this.isNumber;	
	}
	
	this.setRegex = function(regex){
		this.regex = regex ;
	}
	
	this.getRegex = function(){
		return this.regex ;	
	}
	
	this.setNotVals = function(val){
		this.notVals = val ;
	}
	
	this.getNotVals = function(){
		return this.notVals ;	
	}
	
}

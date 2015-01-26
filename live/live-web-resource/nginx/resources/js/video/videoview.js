var userCountI;
var touriseCountI;
var audienceListDiv;

$(function(){
	 userCountI = $("#userCount");
	 touriseCountI = $("#touriseCount");
	 audienceListDiv = $("#audience_list");
});

/**
 * 观众列表操作
 */
var AudienceList = function(){
	
	/**
	 * 初始化观众列表以及观众人数等信息
	 */
	this.init = function(){
		var requestURL = ctx + "/chatRoom/audiences/" + videoId + ".htm" ;
		var that = this;
	    $.ajax({
			url: requestURL,
			async:true,
			success:function(resultData){
				var ajaxResult = new AjaxResult(resultData);
				if(ajaxResult.isSuccess()){
					var data = ajaxResult.data ;
					that.fillByRoomInfo(data);
				}else{
					$.messager.popup(ajaxResult.msg);  
				}
			},
			error:function(resultData){
				 $.messager.popup("获取观众列表失败！");  
			}
	    });
	}
	
	/**
	 * 根据roomInfo填充观众列表内容
	 */
	this.fillByRoomInfo = function(roomInfo){
		if(roomInfo){
			userCountI.val(roomInfo.userCount);
			touriseCountI.val(roomInfo.touristCount);
			if(roomInfo.users){
				for(var i in roomInfo.users){
					this.addUser(roomInfo.users[i]);  
				}
			}
		}
	}
	
	/**
	 * 添加一个用户
	 */
	this.addUser = function(user){
		if(user){
			if(user.tourist){
				this.incrTouriseCount(1);
			}else{
				this.incrUserCount(1);
				var userHtml =
					'<div class="audience" data-id="'+ user.userId + '"  data-uuid="'+ user.uuid +'" >' 
				 +  '<img class="photo" src="'+ user.userPhoto +'" /> '
				 +  '<span class="audience_name">'+ user.userNick +'</span>'
				 +  '</div>';
				audienceListDiv.append(userHtml);
			}
		}
	}
	
	/**
	 * 移除一个用户
	 */
	this.removeUser = function(user){
		if(user){
			if(user.tourist){
				this.incrTouriseCount(-1);
			}else{
				this.incrUserCount(-1);
				var uuid = user.uuid ;
				audienceListDiv.find("div.audience[data-uuid='"+ uuid +"']").remove();  
			}
		}
	}
	
	
	this.incrUserCount = function(count){
		var currVal = userCountI.val();
		if(!currVal){
			currVal = 0 ;
		}
		currVal += count ;
		userCountI.val(currVal); 
	}
	
	this.incrTouriseCount = function(){
		var currVal = touriseCountI.val();
		if(!currVal){
			currVal = 0 ;
		}
		currVal += count ;
		touriseCountI.val(currVal); 
	}
	
}
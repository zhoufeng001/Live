/**
 * 观众列表操作
 */
var AudienceList = function(){
	
	var userCountI = $("#userCount");
	var touriseCountI = $("#touriseCount");
	var audienceListDiv = $("#audience_list");
	
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
			var userHtml =
				'<div class="audience" data-id="'+ user.userId + '">' 
			 +  '<img class="photo" src="'+ user.userPhoto +'" /> '
			 +  '<span class="audience_name">'+ user.userNick +'</span>'
			 +  '</div>';
			audienceListDiv.append(userHtml);
		}
	}
	
	/**
	 * 移除一个用户
	 */
	this.removeUser = function(userId){
		audienceListDiv.find("div.audience").remove("div.audience[data-id='"+ userId +"']");
	}
	
}
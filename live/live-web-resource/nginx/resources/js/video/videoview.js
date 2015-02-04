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
	
	var audiencesMap = new Map();
	
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
//			userCountI.val(roomInfo.userCount);
//			touriseCountI.val(roomInfo.touristCount);
			if(roomInfo.users){
				for(var i = 0 ; i < roomInfo.users.length ; i++){
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
			//如果存在，则跳过，避免重复
			var existAudience = audiencesMap.get(user.uuid); 
			if(existAudience){  
				return ;
			}
			if(user.tourist){ 
				this.incrTouriseCount(1);
				console.log("添加游客" + user.uuid + " 游客人数+1");
			}else{
				this.incrUserCount(1);
				var userHtml =
					'<div class="audience" data-id="'+ user.userId + '"  data-uuid="'+ user.uuid +'" >' 
				 +  '<img class="photo" src="'+ (file_server + "/" + user.userPhoto ) +'" /> '
				 +  '<span class="audience_name">'+ user.userNick +'</span>'
				 +  '</div>';
				audienceListDiv.append(userHtml);
				console.log("添加用户" + user.uuid + " 用户人数+1");
			}
			audiencesMap.put(user.uuid,user); 
		}
	}
	
	/**
	 * 移除一个用户
	 */
	this.removeUser = function(user){
		if(user){
			if(user.tourist){
				this.incrTouriseCount(-1);
				console.log("移除游客" + user.uuid + " 游客人数-1");
			}else{
				this.incrUserCount(-1);
				var uuid = user.uuid ;
				audienceListDiv.find("div.audience[data-uuid='"+ uuid +"']").remove();  
				console.log("移除用户" + user.uuid + " 用户人数-1");
			}
			audiencesMap.remove(user.uuid); 
		}
	}
	
	
	this.incrUserCount = function(count){
		var currVal = userCountI.text();
		if(!currVal){
			currVal = 0 ;
		}else{
			currVal = parseInt(currVal);
		}
		currVal += count ;
		userCountI.text(currVal); 
		console.log("用户人数" + currVal);
	}
	
	this.incrTouriseCount = function(count){
		var currVal = touriseCountI.text();
		if(!currVal){
			currVal = 0 ;
		}else{
			currVal = parseInt(currVal);
		}
		currVal += count ;
		touriseCountI.text(currVal); 
		console.log("游客人数" + currVal); 
	}
	
}
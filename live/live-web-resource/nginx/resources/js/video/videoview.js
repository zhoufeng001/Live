var userCountI;
var touriseCountI;
var audienceListDiv;
var praised = false ;
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
	var uuidUserIdMap = new Map();
	
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
					showErrMsg(ajaxResult.msg);  
				}
			},
			error:function(resultData){
				showErrMsg("获取观众列表失败！");  
			}
	    });
	}
	
	/**
	 * 根据roomInfo填充观众列表内容
	 */
	this.fillByRoomInfo = function(roomInfo){
		if(roomInfo){
//			userCountI.text(roomInfo.userCount);
//			this.incrTouriseCount(roomInfo.touristCount);  
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
			audiencesMap.put(user.uuid,user); 
			if(user.tourist){ 
				this.incrTouriseCount(1);
				console.log("添加游客" + user.uuid + " 游客人数+1");
			}else{
				
				/* 如果该用户在房间未退出，则先删除过期用户（兼容退出房间网络延迟 ） */
				var userId = user.userId ;
				if(uuidUserIdMap.get(userId)){
					var oldAudience = audiencesMap.get(uuidUserIdMap.get(userId));
					if(oldAudience){
						this.removeUser(oldAudience);
					}
				}
				
				this.incrUserCount(1);
				var userHtml =
					'<div class="audience" data-id="'+ userId + '"  data-uuid="'+ user.uuid +'" >' 
				 +  '<img class="photo" src="'+ (file_server + "/" + user.userPhoto ) +'" /> '
				 +  '<span class="audience_name">'+ user.userNick +'</span>'
				 +  '</div>';
				audienceListDiv.append(userHtml);
				console.log("添加用户" + user.uuid + " 用户人数+1");
				uuidUserIdMap.put(userId , user.uuid);
			}
		}
	}
	
	/**
	 * 移除一个用户
	 */
	this.removeUser = function(user){
		if(user){
			if(!audiencesMap.get(user.uuid)){
				return; 
			}
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

/**
 * 点赞
 */
function doPraise(){
	if (userToken == null) {
		 showErrMsg("请先登录");
		return;  
	} 
	if(praised){
		showInfoMsg("亲，你已经点过赞了！"); 
	}else{
		var doPraiseUrl =  ctx + "/video/doPraise/" + videoId + ".htm" ;  
		$.ajax({
			url:doPraiseUrl,
			success:function(data){
				showInfoMsg("点赞成功！"); 
				var praiseCountTag = $("#praise_count") ;
				var currentPraiseCount = parseInt(praiseCountTag.text());
				currentPraiseCount++;
				praiseCountTag.hide(); 
				praiseCountTag.text(currentPraiseCount);   
				praiseCountTag.show("slow");  
				praised = true ;
			}
		});
	}
}


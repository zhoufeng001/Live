package com.zf.live.web.control.room;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.room.RoomService;
import com.zf.live.client.vo.room.RoomInfo;
import com.zf.live.client.vo.web.AjaxResult;
import com.zf.live.web.app.util.WebUtil;

/**
 * 聊天室相关操作
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月25日 上午11:12:58
 */
@Controller
@RequestMapping("/chatRoom")
public class ChatRoomController {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(ChatRoomController.class);
	
	@Resource(name="roomService")
	private RoomService roomService ;
	
	/**
	 * 获取房间观众列表，用于进入房间后初始化观众列表用
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/audiences/{videoId}")
	public void audiences(@PathVariable("videoId") String videoId, ServletRequest request, ServletResponse response) {
		RoomInfo roomInfo = roomService.getRoomInfo(videoId);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(roomInfo), response);
	}
	
	
	
}

package com.zf.live.web.control.room;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zf.live.client.room.RoomService;

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
	 * 去登录页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/audience")
	@ResponseBody
	public Map<String, Object> loginView(ServletRequest request, ServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("aa", "bb");
		return data;
	}
	
	
	
}

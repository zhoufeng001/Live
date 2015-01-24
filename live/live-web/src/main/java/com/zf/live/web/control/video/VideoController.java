package com.zf.live.web.control.video;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.room.RoomService;
import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.client.vo.room.RoomInfo;
import com.zf.live.client.vo.video.local.VideoDetailVo;
import com.zf.live.common.ZFSpringPropertyConfigure;
import com.zf.live.web.app.service.video.WebVideoService;

/**
 * 视频播放
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月14日 上午12:42:07
 */
@Controller
@RequestMapping("/video/")
public class VideoController {

	static final Logger log = LoggerFactory.getLogger(VideoController.class);

	@Resource(name="localVideoService")
	private LocalVideoService localVideoService ;

	@Autowired
	private WebVideoService webVideoService ;
	
	@Resource(name="roomService")
	private RoomService roomService ;
	
	@Autowired
	private ZFSpringPropertyConfigure propertyConfigure ;

	/**
	 * 去视频播放页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/view/{videoId}")
	public String videoView(@PathVariable("videoId") Long videoId, ModelMap modelMap){
		VideoDetailVo videoDetailVo =  webVideoService.selectVideoDetailVoWithCache(videoId, true) ;
		modelMap.addAttribute("videoDetailVo", videoDetailVo) ;
		if(videoDetailVo != null && videoDetailVo.getVideo() != null){
			modelMap.addAttribute("category", videoDetailVo.getVideo().getCategory());
		}
		
		/* 聊天服务器 */
		String cometServerUrl = propertyConfigure.getProperties("comet.server.url");
		String cometdHandshake = propertyConfigure.getProperties("comet.server.handshake.url");
		modelMap.addAttribute("cometServerUrl", cometServerUrl) ;
		modelMap.addAttribute("cometdHandshake", cometdHandshake) ;
		
		/* 房间信息 */
		RoomInfo roomInfo = roomService.getRoomInfo(videoId) ;
		modelMap.addAttribute("roomInfo", roomInfo);
		
		return "videoview";
	}


}

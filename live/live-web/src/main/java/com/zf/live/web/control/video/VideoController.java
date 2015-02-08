package com.zf.live.web.control.video;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.room.RoomService;
import com.zf.live.client.vo.room.RoomInfo;
import com.zf.live.client.vo.video.local.LocalVideoDetailVo;
import com.zf.live.client.vo.web.AjaxResult;
import com.zf.live.common.ZFSpringPropertyConfigure;
import com.zf.live.web.WebConst;
import com.zf.live.web.WebConst.Config;
import com.zf.live.web.app.service.video.WebVideoService;
import com.zf.live.web.app.util.WebUtil;

/**
 * 视频播放
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月14日 上午12:42:07
 */
@Controller
@RequestMapping("/video/")
public class VideoController {

	static final Logger log = LoggerFactory.getLogger(VideoController.class);

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
	public String videoView(@PathVariable("videoId") String videoId, HttpServletRequest request, HttpServletResponse response
			,ModelMap modelMap){
		LocalVideoDetailVo videoDetailVo =  webVideoService.selectVideoDetailVoWithCache(videoId, true) ;
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
		
		/**
		 * 设置当前观看视频地址，用户登录成功后跳转地址
		 */
		request.getSession().setAttribute(WebConst.sessionRefreshKey, Config.ctx + "/video/view/" + videoId + ".htm"); 
		
		return "videoview";
	}

	/**
	 * 点赞
	 * @param videoId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doPraise/{videoId}")
	public void doPraise(@PathVariable("videoId") String videoId, ServletRequest request, ServletResponse response){
		long praiseCount = webVideoService.doPraiseVideo(videoId) ;
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(praiseCount), response);  
	}

}

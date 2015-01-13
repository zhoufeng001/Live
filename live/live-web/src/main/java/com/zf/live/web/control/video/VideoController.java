package com.zf.live.web.control.video;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.video.local.VideoDetailVo;
import com.zf.live.web.app.service.LiveWebUtil;

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

	/**
	 * 去分类页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/view/{videoId}")
	public String videoView(@PathVariable("videoId") Long videoId, ModelMap modelMap){
		ServiceResult<VideoDetailVo> result = localVideoService.selectVideoWithDetailInfo(videoId);
		if(result == null){
			return LiveWebUtil.redirectIndexPath() ;
		}
		if(result.isSuccess()){
			VideoDetailVo videoDetailVo = result.getData();
			modelMap.addAttribute("videoDetailVo", videoDetailVo) ;
			return "videoview";
		}else{
			return LiveWebUtil.redirectIndexPath() ;
		}
		
	}


}

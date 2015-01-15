package com.zf.live.web.app.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.web.app.service.video.WebVideoService;
import com.zf.live.web.control.IndexController;

/**
 * 重新缓存分类置顶视频
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月15日 下午11:30:42
 */
@Component("recacheCategoryRecommendTopVideoTask")
public class RecacheCategoryRecommendTopVideoTask {

	@Autowired
	private WebVideoService webVideoService ;
	
	public void reCache(){
		if(IndexController.categorys != null){
			for (String category : IndexController.categorys) {
				webVideoService.reCacheCategoryRecommend(category); 
			}
		}
	}
	
}


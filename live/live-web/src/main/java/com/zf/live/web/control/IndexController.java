package com.zf.live.web.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.web.app.service.video.WebVideoService;
import com.zf.live.web.vo.video.CategoryRecommendVo;

@Controller
@RequestMapping()
public class IndexController {
	
	static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private WebVideoService webVideoService ;
	
	/**
	 * 首页展示的分类集合
	 */
	public static final String categorys[] = {
		"电影","电视剧","综艺","音乐","微电影","网剧","游戏","动漫","娱乐","时尚","搞笑","自拍","汽车","科技","生活"
	};
	
	@RequestMapping("/index")
	public String index(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		List<CategoryRecommendVo> categoryRecomendVoList = new ArrayList<CategoryRecommendVo>();
		for (String category : categorys) {
			CategoryRecommendVo categoryRecommendVo = webVideoService.selectCategoryRecommend(category);
			categoryRecomendVoList.add(categoryRecommendVo);
		}
		modelMap.addAttribute("categoryRecomendVoList", categoryRecomendVoList) ; 
		return "index"; 
	}

}

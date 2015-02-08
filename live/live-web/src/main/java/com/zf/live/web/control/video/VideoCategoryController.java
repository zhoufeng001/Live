package com.zf.live.web.control.video;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.video.local.LocalVideoServiceV2;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchConditionV2;
import com.zf.live.common.assertx.ZFAssert;
import com.zf.live.dao.vo.video.LocalVideoVo;
import com.zf.live.web.WebConst;
import com.zf.live.web.WebConst.Config;
import com.zf.live.web.app.service.video.WebVideoService;
import com.zf.live.web.vo.video.CategoryRecommendVo;

/**
 * 视频分类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月12日 下午11:04:55
 */
@Controller
@RequestMapping("/video/category")
public class VideoCategoryController {

	static final Logger log = LoggerFactory.getLogger(VideoCategoryController.class);

	@Resource(name="localVideoServiceV2")
	private LocalVideoServiceV2 localVideoServiceV2 ;
	
	@Autowired
	private WebVideoService webVideoService ;

	/**
	 * 页大小
	 */
	private static final Integer VIDEO_CATEGORY_PAGE_SIZE = 28 ;

	/**
	 * 根据在线人数排序
	 */
	private static final Integer ORDER_BY_ONLINE_NUM = 1 ;

	/**
	 * 根据赞熟练排序
	 */
	private static final Integer ORDER_BY_PRAISE_NUM = 2;

	/**
	 * 根据赞熟练排序
	 */
	private static final Integer ORDER_BY_UPDATE_TIME = 3;

	/**
	 * 去分类页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/{category}/{page}/{orderby}")
	public String categoryVideoView(
			@PathVariable("category") String category ,
			@PathVariable("page") Integer page ,
			@PathVariable("orderby") Integer orderby,
			HttpServletRequest request, HttpServletResponse response , ModelMap modelMap) {

		//分页查询video列表信息
		{
			ZFAssert.notBlank(category, "category不能为空"); 
			ZFAssert.notBlank(page, "page不能为空"); 
			ZFAssert.notBlank(orderby, "orderby不能为空"); 
			LocalVideoSearchConditionV2 condition = new LocalVideoSearchConditionV2() ;
			condition.setCategory(category);
			if(page == null || page <= 0){
				page = 1;
			}
			condition.setPage(page); 
			condition.setPageSize(VIDEO_CATEGORY_PAGE_SIZE); 
			if(orderby != null){
				if(ORDER_BY_ONLINE_NUM.equals(orderby)){
					condition.setOrderBy(" audience_count desc ");
				}else if(ORDER_BY_PRAISE_NUM.equals(orderby)){
					condition.setOrderBy(" praise desc ,third_praise desc ");
				}else if(ORDER_BY_UPDATE_TIME.equals(orderby)){
					condition.setOrderBy(" publishtime desc ");
				}
			}
			PagedVo<LocalVideoVo> videoPageVo = webVideoService.searchVideos(condition);
			modelMap.put("videoPageVo", videoPageVo) ;
		}


		//根据viewCount排序，取11条数据
		{
			CategoryRecommendVo categoryRecommendVo = webVideoService.selectCategoryRecommend(category) ;
			modelMap.put("categoryRecommendVo", categoryRecommendVo);
		}
		
		/**
		 * 设置当前观看视频地址，用户登录成功后跳转地址
		 */
		try {
			request.getSession().setAttribute(WebConst.sessionRefreshKey,
					Config.ctx + "/video/category/" + URLEncoder.encode(category,"utf-8") + "/" + page + "/" + orderby + ".htm");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage() ,e); 
		} 
		
		modelMap.addAttribute("category", category);
		return "vcategory";
	}


}

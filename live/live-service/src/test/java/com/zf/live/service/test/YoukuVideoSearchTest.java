package com.zf.live.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
import com.zf.live.client.video.youku.response.VideoDetailResponse;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;
import com.zf.live.client.video.youku.vo.Category;
import com.zf.live.client.vo.video.local.VideoDetailVo;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午2:46:40
 */
public class YoukuVideoSearchTest extends ServiceBaseTester{

	@Autowired
	private YoukuVideoSearchService youkuVideoSearchService ;
	
	@Test
	public void testSearchByTag(){
		SearchVideoByCategoryRequest request = new SearchVideoByCategoryRequest() ;
		request.setPage(200);
		request.setCount(20);
		request.setCategory("资讯");
		SearchVideoByCategoryResponse  response = youkuVideoSearchService.searchByCategory(request);
		if(response == null){
			System.out.println("请求结果为空！");
		}else{
			System.out.println(response.toString());
		}
	}
	
	@Test
	public void testSearchVideoDetail(){
		SearchVideoDetailRequest request = new SearchVideoDetailRequest() ;
		request.setVideoId("XODY0NjkwMjcy");
		request.setExt("thumbnails");
		VideoDetailVo response = youkuVideoSearchService.searchVideoDetail(request);
		if(response == null){
			System.out.println("请求结果为空！");
		}else{
			System.out.println(response.toString());
		}
	}
	
	@Test
	public void testAllCategory(){
		List<Category> categories = youkuVideoSearchService.searchAllCategories();
		if(categories == null || categories.size() == 0){
			System.out.println("结果集为空");
		}else{
			System.out.println(categories.size());  
		}
	}
}

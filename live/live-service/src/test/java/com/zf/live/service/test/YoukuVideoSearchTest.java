package com.zf.live.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
import com.zf.live.client.video.youku.response.VideoDetailResponse;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;

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
		request.setVideoId("XODYzMjU4NzIw");
		VideoDetailResponse response = youkuVideoSearchService.searchVideoDetail(request);
		if(response == null){
			System.out.println("请求结果为空！");
		}else{
			System.out.println(response.toString());
		}
	}
	
}

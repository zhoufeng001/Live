package com.zf.live.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.request.SearchVideoListByCategoryRequest;
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
import com.zf.live.client.video.youku.response.SearchVideoListByCategoryResponse;
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
		SearchVideoByCategoryResponse  response = youkuVideoSearchService.searchVideoByCategory(request);
		if(response == null){
			System.out.println("请求结果为空！");
		}else{
			System.out.println(response.toString());
		}
	}
	
	@Test
	public void testSearchVideoDetail(){
		SearchVideoDetailRequest request = new SearchVideoDetailRequest() ;
		request.setVideoId("XODY0NzkyNzk2");
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
		List<Category> categories = youkuVideoSearchService.searchAllVideoCategories();
		if(categories == null || categories.size() == 0){
			System.out.println("结果集为空");
		}else{
			System.out.println(categories.size());  
		}
	}
	
	@Test
	public void testSearchVideoList(){
		SearchVideoListByCategoryRequest request = new SearchVideoListByCategoryRequest();
		request.setPage(3);
		SearchVideoListByCategoryResponse response = youkuVideoSearchService.searchVideoListByCategory(request);
		if(response == null){
			System.out.println("未搜索到内容");
		}else{
			if(response.getPlaylists() == null || response.getPlaylists().size() == 0){
				System.out.println("搜索到内容为空");
			}else{
				System.out.println("搜索到" + response.getPlaylists().size() + "条内容");
			}
		}
	}
}

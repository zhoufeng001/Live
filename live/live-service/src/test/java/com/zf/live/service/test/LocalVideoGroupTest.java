package com.zf.live.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.video.local.LocalVideoGroupService;
import com.zf.live.client.video.youku.request.SearchVideosFromVideoListRequest;
import com.zf.live.client.video.youku.response.SearchVideosFromVideoListResponse;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.video.VideoSite;
import com.zf.live.dao.pojo.VideoGroup;
import com.zf.live.service.impl.video.youku.YoukuFetchVideoListAndSaveServiceImpl;

public class LocalVideoGroupTest extends ServiceBaseTester{

	@Autowired
	private LocalVideoGroupService localVideoGroupService ;

	@Autowired
	private YoukuFetchVideoListAndSaveServiceImpl youkuFetchVideoListAndSaveServiceImpl ;

	@Autowired
	private YoukuVideoSearchService youkuVideoSearchService ;

	@Test
	public void testSave(){
		VideoGroup videoGroup = new VideoGroup() ;
		videoGroup.setCategory("电影");
		videoGroup.setFromid("12312321");
		videoGroup.setGroupName("xxx");
		videoGroup.setPublished(new Date());
		videoGroup.setThumbnail("www.baidu.com");
		videoGroup.setVideofrom(VideoSite.YOUKU.getValue());
		ServiceResult<Long> result = localVideoGroupService.saveVideoGroup(videoGroup);
		if(result.isSuccess()){
			System.out.println("保存成功，id：" + result.getData());
		}else{
			System.out.println("保存失败，" + result.getErrMssage());
		}
	}

	@Test
	public void testSearchVideoListByGroupId(){
		SearchVideosFromVideoListRequest request = new SearchVideosFromVideoListRequest() ;
		request.setPlaylistId("20074663");
		request.setCount(50);
		request.setPage(3);
		SearchVideosFromVideoListResponse  response =youkuVideoSearchService.searchVideoListFromVideoGroup(request);
		if(response == null || response.getVideos() == null || response.getVideos().size() == 0){
			System.out.println("没搜索到内容");
		}else{
			System.out.println("成功搜索到" + response.getVideos().size() + "条记录");
		}
	}

	@Test
	public void testFas(){

		youkuFetchVideoListAndSaveServiceImpl.fetchAndSave(); 

	}

}

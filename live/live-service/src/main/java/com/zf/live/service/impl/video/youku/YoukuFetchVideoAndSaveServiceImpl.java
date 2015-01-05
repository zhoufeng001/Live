package com.zf.live.service.impl.video.youku;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.FetchVideosAndSaveService;
import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
import com.zf.live.client.video.youku.response.VideoResponse;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.video.VideoSite;
import com.zf.live.dao.pojo.Video;

/**
 * 从优酷获取视频并更新到数据库
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 下午11:48:03
 */
@Component("youkuFetchVideoAndSaveService")
public class YoukuFetchVideoAndSaveServiceImpl implements FetchVideosAndSaveService{

	private static final Logger log = LoggerFactory.getLogger(YoukuFetchVideoAndSaveServiceImpl.class);


	/**
	 * 每页大小
	 */
	private static final Integer PAGE_SIZE = 50 ;

	@Autowired
	private YoukuVideoSearchService youkuVideoSearchService;

	@Autowired
	private LocalVideoService localVideoService ;

	@Override
	public void fetchAndSave() {
		SearchVideoByCategoryRequest request = new SearchVideoByCategoryRequest() ;
		request.setCount(PAGE_SIZE);
		int index = 0;
		int errCount = 0 ;
		int pageIndex = 0;
		int totalRecoredCount = 0;
		int totalInsertCount = 0;
		SearchVideoByCategoryResponse response = null ;
		List<VideoResponse> videoResponse = null ;
		do{	

			/* 从Youku搜索  */
			request.setPage(++pageIndex);
			response = youkuVideoSearchService.searchByCategory(request) ;
			if(response == null ){
				errCount++ ;
				System.out.println("第"+ errCount +"次失败");
				continue ;
			}
			videoResponse = response.getVideos();
			if(videoResponse == null || videoResponse.size() == 0 ){
				errCount++ ;
				System.out.println("第"+ errCount +"次失败");
				continue ;
			}

			/* 存储到本地  */
			for (VideoResponse youkuVideo : videoResponse) {

				if(!localVideoService.existVideo(VideoSite.YOUKU.getValue(), youkuVideo.getId())){
					Video video = new Video();
					video.setCategory(youkuVideo.getCategory());
					video.setFromid(youkuVideo.getId());
					video.setThumbnail(youkuVideo.getThumbnail());
					video.setVideofrom(VideoSite.YOUKU.getValue());
					video.setVideoname(youkuVideo.getTitle()); 
					ServiceResult<Long> saveResult = null;
					try {
						saveResult = localVideoService.saveVideo(video);
					}catch(Exception e){
						e.printStackTrace();
						continue ;
					}
					if(saveResult == null){
						System.out.println("保存视频到本地失败");
						continue; 
					}
					if(!saveResult.isSuccess()){
						System.out.println(saveResult.getErrMssage()); 
					}
					totalInsertCount++;
				}
			} 

			System.out.println("第" + ++index + "次搜索到" + videoResponse.size() + "条视频记录");
			totalRecoredCount += videoResponse.size() ;
		}while(errCount < 3);
		System.out.println("搜索完成，共搜索到" + totalRecoredCount + "条记录，共插入" + totalInsertCount + "条记录！");
	}

}

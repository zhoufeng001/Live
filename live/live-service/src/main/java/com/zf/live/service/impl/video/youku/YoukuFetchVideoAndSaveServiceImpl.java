package com.zf.live.service.impl.video.youku;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
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
import com.zf.live.client.video.youku.vo.Category;
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
		int totalRecoredCount = 0;
		int totalInsertCount = 0;
		SearchVideoByCategoryResponse response = null ;
		List<VideoResponse> videoResponse = null ;

		List<Category> categoryList = youkuVideoSearchService.searchAllVideoCategories();
		if(categoryList == null || categoryList.size() == 0){
			log.error("未查询到视频分类！");
		}

		String categoryLable = null ;
		for (Category category : categoryList) {
			categoryLable = category.getLabel();
			log.info("------------------------开始搜索"+ categoryLable +"视频----------------------------");
			request.setCategory(categoryLable); 

			int pageIndex = 0;
			int errCount = 0 ;
			do{	
				/* 从Youku搜索  */
				request.setPage(++pageIndex);
				response = youkuVideoSearchService.searchVideoByCategory(request) ;
				if(response == null ){
					errCount++ ;
					log.info("第"+ errCount +"次失败");
					continue ;
				}
				videoResponse = response.getVideos();
				if(videoResponse == null || videoResponse.size() == 0 ){
					errCount++ ;
					log.info("第"+ errCount +"次失败");
					continue ;
				}

				/* 存储到本地  */
				for (VideoResponse youkuVideo : videoResponse) {

					if(!localVideoService.existVideo(VideoSite.YOUKU.getValue(), youkuVideo.getId())){
						Video video = new Video();
						video.setCategory(youkuVideo.getCategory());
						video.setFromid(youkuVideo.getId());
						video.setThumbnail(youkuVideo.getThumbnail());
						try {
							video.setPublishtime(DateUtils.parseDate(youkuVideo.getPublished(), "yyyy-MM-dd HH:mm:ss"));
						} catch (ParseException e1) {
							log.error(e1.getMessage()); 
						}
						video.setThirdPraise(0L);
						if(youkuVideo.getUp_count() != null){
							video.setThirdPraise(video.getThirdPraise() + Long.valueOf(youkuVideo.getUp_count()));
						}  
						if(youkuVideo.getFavorite_count() != null){
							video.setThirdPraise(video.getThirdPraise() + Long.valueOf(youkuVideo.getFavorite_count()));
						}
						if(youkuVideo.getComment_count() != null){
							video.setThirdPraise(video.getThirdPraise() + Long.valueOf(youkuVideo.getComment_count()));
						}
						if(youkuVideo.getView_count() != null){
							video.setThirdViewCount(Long.valueOf(youkuVideo.getView_count()));  
						}
						video.setVideofrom(VideoSite.YOUKU.getValue());
						video.setVideoname(youkuVideo.getTitle()); 
						ServiceResult<String> saveResult = null;
						try {
							saveResult = localVideoService.saveVideo(video);
						}catch(Exception e){
							log.error("保存视频失败,{}",e.getMessage()); 
							continue ;
						}
						if(saveResult == null){
							log.error("保存视频到本地失败");
							continue; 
						}
						if(!saveResult.isSuccess()){
							log.error(saveResult.getErrMssage()); 
						}
						totalInsertCount++;
					}
				} 

				log.info("第" + ++index + "次搜索到" + videoResponse.size() + "条视频记录");
				totalRecoredCount += videoResponse.size() ;
			}while(errCount < 3);

			log.info("------------------------搜索"+ categoryLable +"视频完成----------------------------\n\n\n");
		}

		log.info("搜索完成，共搜索到" + totalRecoredCount + "条记录，共插入" + totalInsertCount + "条记录！");
	}

}

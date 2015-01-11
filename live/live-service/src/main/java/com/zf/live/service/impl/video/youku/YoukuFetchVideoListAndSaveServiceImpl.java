package com.zf.live.service.impl.video.youku;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.FetchVideosAndSaveService;
import com.zf.live.client.video.local.LocalVideoGroupService;
import com.zf.live.client.video.youku.request.SearchVideoListByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoListDetailByIdsRequest;
import com.zf.live.client.video.youku.response.SearchVideoListByCategoryResponse;
import com.zf.live.client.video.youku.response.SearchVideoListDetailByIdsResponse;
import com.zf.live.client.video.youku.response.VideoListDetailResponse;
import com.zf.live.client.video.youku.response.VideoListResponse;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;
import com.zf.live.client.video.youku.vo.Category;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.video.VideoSite;
import com.zf.live.common.util.TimerUtil;
import com.zf.live.dao.pojo.VideoGroup;

/**
 * 抓去youku专辑信息并存在数据库
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月11日 下午2:52:32
 */
@Component("youkuFetchVideoListAndSaveService")
public class YoukuFetchVideoListAndSaveServiceImpl implements FetchVideosAndSaveService{

	private static final Logger log = LoggerFactory.getLogger(YoukuFetchVideoListAndSaveServiceImpl.class);

	private static final Integer SEARCH_PAGE_SIZE = 50;

	@Autowired
	private YoukuVideoSearchService youkuVideoSearchService ;

	@Autowired
	private LocalVideoGroupService localVideoGroupService ;

	@Override
	public void fetchAndSave() {
		List<Category> groupCategories = youkuVideoSearchService.searchAllVideoListCategories();
		if(groupCategories == null){
			log.warn("没有找到VideoList Categories列表");
			return ;
		}

		int searchCount = 0 ;
		int searchIndex = 0 ;
		int insertCount = 0 ;
		TimerUtil.start("");
		log.info("-----------------------开始从Youku搜索专辑信息--------------------------");
		for (Category category : groupCategories) {
			String categoryName = category.getLabel() ;
			log.info("---------------开始搜索{}专辑-------------",categoryName);
			int videoListPageIndex = 0;
			int failCount = 0 ;

			do{
				SearchVideoListByCategoryRequest videoListRequest = new SearchVideoListByCategoryRequest() ;
				videoListRequest.setCategory(categoryName);
				videoListRequest.setPage(++videoListPageIndex);
				videoListRequest.setCount(SEARCH_PAGE_SIZE);
				SearchVideoListByCategoryResponse videoListResponse = youkuVideoSearchService.searchVideoListByCategory(videoListRequest) ;
				if(videoListResponse == null || videoListResponse.getPlaylists() == null 
						|| videoListResponse.getPlaylists().size() <= 0){
					log.warn("返回数据为空");
					failCount++;
					log.warn("第" + failCount + "次搜索失败");
					continue ;	
				}
				List<VideoListResponse> listResponse = videoListResponse.getPlaylists(); 
				if(listResponse == null || listResponse.size() == 0){
					log.warn("搜索到列表为空！");
					failCount++;
					log.warn("第" + failCount + "次搜索失败");
					continue ;
				}

				searchCount += listResponse.size() ;

				StringBuilder videoListIds = new StringBuilder() ;
				for (VideoListResponse videoListResp : listResponse) {
					videoListIds.append(videoListResp.getId());
					videoListIds.append(",");
				}
				videoListIds.deleteCharAt(videoListIds.length() - 1);

				SearchVideoListDetailByIdsRequest videoListDetailRequest = new SearchVideoListDetailByIdsRequest();
				videoListDetailRequest.setPlaylistIds(videoListIds.toString()); 
				SearchVideoListDetailByIdsResponse videoListDetailResponse = youkuVideoSearchService.searchVideoListDetailByIds(videoListDetailRequest);
				if(videoListDetailResponse == null || videoListDetailResponse.getPlaylists() == null 
						|| videoListDetailResponse.getPlaylists().size() <= 0){
					log.warn("没有搜索到[{}]视频详情" , videoListIds.toString());
					continue ;
				}

				List<VideoListDetailResponse> videoListDetailList = videoListDetailResponse.getPlaylists();
				for (VideoListDetailResponse youkuVideoListDetail : videoListDetailList) {
					//如果已经存在，就跳过
					if(localVideoGroupService.existThirdVideoGroup(VideoSite.YOUKU.getValue(), youkuVideoListDetail.getId())){
						continue ;
					}
					VideoGroup videoGroup = new VideoGroup();
					videoGroup.setCategory(youkuVideoListDetail.getCategory()); 
					videoGroup.setDescription(youkuVideoListDetail.getDescription());
					videoGroup.setFromid(youkuVideoListDetail.getId());
					videoGroup.setGroupName(youkuVideoListDetail.getName());
					try {
						videoGroup.setPublished(DateUtils.parseDate(youkuVideoListDetail.getPublished(), "yyyy-MM-dd HH:mm:ss"));
					} catch (ParseException e) {  
						log.error("解析publishTime失败");
					}
					videoGroup.setThirdViewCount(Long.valueOf(youkuVideoListDetail.getView_count()));
					videoGroup.setThumbnail(youkuVideoListDetail.getThumbnail());
					videoGroup.setUpdatetime(new Date());
					videoGroup.setVideoCount(youkuVideoListDetail.getVideo_count());
					videoGroup.setVideofrom(VideoSite.YOUKU.getValue());
					ServiceResult<Long> insertResult = localVideoGroupService.saveVideoGroup(videoGroup);
					if(insertResult == null || !insertResult.isSuccess() || insertResult.getData() == null){
						log.error("插入失败！");
						continue ;
					}else{
						insertCount++; 
					}
				}
				log.warn("第{}次搜索到{}条记录" , ++searchIndex , videoListDetailList.size());
			}while(failCount < 3) ;

		}
		log.info("-----------------------从Youku搜索专辑信息完成，共搜索到{}条记录，共插入{}条记录，用时{}秒--------------------------"
				,searchCount,insertCount,TimerUtil.timing() / 1000);
	}


}

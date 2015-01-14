package com.zf.live.service.impl.video.youku;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.request.SearchVideoListByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoListDetailByIdsRequest;
import com.zf.live.client.video.youku.request.SearchVideosFromVideoListRequest;
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
import com.zf.live.client.video.youku.response.SearchVideoListByCategoryResponse;
import com.zf.live.client.video.youku.response.SearchVideoListDetailByIdsResponse;
import com.zf.live.client.video.youku.response.SearchVideosFromVideoListResponse;
import com.zf.live.client.video.youku.response.VideoDetailResponse;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;
import com.zf.live.client.video.youku.vo.Category;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.video.VideoSite;
import com.zf.live.client.vo.video.local.VideoDetailVo;
import com.zf.live.common.ZFSpringPropertyConfigure;
import com.zf.live.common.assertx.ZFAssert;
import com.zf.live.common.util.HttpClientUtils;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.pojo.Video;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

/**
 * 优酷视频搜索实现
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:53:51
 */
@Component("youkuVideoSearchService")
public class YoukuVideoSearchServiceImpl implements YoukuVideoSearchService {

	private static final Logger log = LoggerFactory.getLogger(YoukuVideoSearchServiceImpl.class);

	@Autowired
	private LocalVideoService localVideoService ;

	@Autowired
	private ZFSpringPropertyConfigure propertyConfigure ;


	@Override
	public SearchVideoByCategoryResponse searchVideoByCategory(SearchVideoByCategoryRequest request) {
		String apiUrl = propertyConfigure.getProperties("youku.api.search_video_by_category") ;
		ZFAssert.notBlank(apiUrl, "优酷根据标签搜索视频api地址youku.api.search_video_by_category未正确配置"); 
		String clientId = getClientId() ;
		request.setClientId(clientId);
		String requestUrl = YoukuRequestUtil.buildRequestUrl(apiUrl, request) ;
		String responseStr = HttpClientUtils.sendGetRequest(requestUrl);
		if(StringUtils.isBlank(responseStr)){
			log.warn("请求结果为空！"); 
			return null ;
		}
		if(isError(responseStr)){
			log.error("根据分类查询视频列表失败，{}",responseStr);
			return null ;
		}
		SearchVideoByCategoryResponse response = JSON.parseObject(responseStr , SearchVideoByCategoryResponse.class);
		return response;
	}
	
	@Override
	public VideoDetailResponse searchYoukuVideoDetail(
			SearchVideoDetailRequest request) {
		String apiUrl = propertyConfigure.getProperties("youku.api.search_video_detail") ;
		ZFAssert.notBlank(apiUrl, "优酷搜索视频详情api地址youku.api.search_video_detail未正确配置"); 
		String clientId = getClientId() ;
		request.setClientId(clientId);
		request.setExt("thumbnails"); 
		String requestUrl = YoukuRequestUtil.buildRequestUrl(apiUrl, request) ;
		String responseStr = HttpClientUtils.sendGetRequest(requestUrl);
		if(StringUtils.isBlank(responseStr)){
			log.warn("请求结果为空！"); 
			return null ;
		}
		if(isError(responseStr)){
			log.error("查询视频详情失败，{}",responseStr);
			return null ;
		}
		VideoDetailResponse response = JSON.parseObject(responseStr , VideoDetailResponse.class);
		return response;
	}
	

	@Override
	public VideoDetailWithBLOBs searchYoukuVideoDetailAndSave(
			Long localVideoId ,SearchVideoDetailRequest request) {
		VideoDetailResponse youkuVideoDetail = searchYoukuVideoDetail(request) ;
		if(youkuVideoDetail == null){
			log.warn("没有搜索到视频{}", request.getVideoId());
			return null ;
		}
		
		VideoDetailWithBLOBs localVideoDetail = new VideoDetailWithBLOBs();
		localVideoDetail.setId(localVideoId);
		localVideoDetail.setDescription(youkuVideoDetail.getDescription());
		localVideoDetail.setDuration(youkuVideoDetail.getDuration());
		localVideoDetail.setLink(youkuVideoDetail.getLink());
		localVideoDetail.setBigthumbnail(youkuVideoDetail.getBigThumbnail());
		if(youkuVideoDetail.getThumbnails() != null){  
			localVideoDetail.setThumbnails(JSON.toJSONString(youkuVideoDetail.getThumbnails())); 
		}
		ServiceResult<Long> saveDetailResult = localVideoService.saveVideoDetail(localVideoDetail);
		if(saveDetailResult == null){
			log.error("存储视频详情到本地失败！");
			return null ;
		}
		if(!saveDetailResult.isSuccess()){
			log.error(saveDetailResult.getErrMssage());
			return null ;
		}
		return localVideoDetail ;
	}
	
	@Override
	public VideoDetailVo searchVideoDetail(
			@Notnull("videoId") SearchVideoDetailRequest request) {

		Long localVideoDetailId = localVideoService.selectVideoDetailId(VideoSite.YOUKU.getValue(), request.getVideoId());
		if(localVideoDetailId != null && localVideoDetailId > 0){
			ServiceResult<VideoDetailVo>  localVideoDetailVoResult = localVideoService.selectVideoWithDetailInfo(localVideoDetailId,false);
			if(localVideoDetailVoResult != null && localVideoDetailVoResult.isSuccess()){
				return localVideoDetailVoResult.getData() ;
			}
		}

		VideoDetailResponse response = searchYoukuVideoDetail(request);
		if(response == null){
			log.error("没有搜索到视频{}",request.getVideoId());
			return null ;
		}
		/* 存储视频到本地  */
		Video video = localVideoService.selectVideo(VideoSite.YOUKU.getValue(), response.getId());
		if(video == null){
			video = response.toLocalVideo();
			ServiceResult<Long> saveVideoResult =	localVideoService.saveVideo(video);
			if(saveVideoResult == null){
				log.error("存储视频到本地失败！");
				return null ;
			}
			if(!saveVideoResult.isSuccess()){
				log.error(saveVideoResult.getErrMssage());
				return null;
			}
		}
		
		VideoDetailWithBLOBs localVideoDetail = searchYoukuVideoDetailAndSave(video.getId() , request);

		VideoDetailVo videoDetailVo = new VideoDetailVo();
		videoDetailVo.setVideo(video);
		videoDetailVo.setVideoDetail(localVideoDetail);
		return videoDetailVo;

	}
	
	@Override
	public SearchVideoListByCategoryResponse searchVideoListByCategory(
			SearchVideoListByCategoryRequest request) {
		String apiUrl = propertyConfigure.getProperties("youku.api.search_videolist_by_category") ;
		ZFAssert.notBlank(apiUrl, "优酷根据标签搜索视频专辑api地址youku.api.search_videolist_by_category未正确配置"); 
		String clientId = getClientId() ;
		request.setClientId(clientId);
		String requestUrl = YoukuRequestUtil.buildRequestUrl(apiUrl, request) ;
		String responseStr = HttpClientUtils.sendGetRequest(requestUrl);
		if(StringUtils.isBlank(responseStr)){
			log.warn("请求结果为空！"); 
			return null ;
		}
		if(isError(responseStr)){
			log.error("根据分类查询专辑列表失败，{}",responseStr);
			return null ;
		}
		SearchVideoListByCategoryResponse response = JSON.parseObject(responseStr , SearchVideoListByCategoryResponse.class);
		return response;
	}


	@Override
	public SearchVideoListDetailByIdsResponse searchVideoListDetailByIds(
			SearchVideoListDetailByIdsRequest request) {
		String apiUrl = propertyConfigure.getProperties("youku.api.search_videolist_detail_by_ids") ;
		ZFAssert.notBlank(apiUrl, "优酷根据专辑搜索视频api地址youku.api.search_videolist_detail_by_ids未正确配置"); 
		String clientId = getClientId() ;
		request.setClientId(clientId);
		String requestUrl = YoukuRequestUtil.buildRequestUrl(apiUrl, request) ;
		String responseStr = HttpClientUtils.sendGetRequest(requestUrl);
		if(StringUtils.isBlank(responseStr)){
			log.warn("请求结果为空！"); 
			return null ;
		}
		if(isError(responseStr)){
			log.error("根据分类查询专辑详情失败，{}",responseStr);
			return null ;
		}
		SearchVideoListDetailByIdsResponse response = JSON.parseObject(responseStr , SearchVideoListDetailByIdsResponse.class);
		return response;
	}
	
	@Override
	public SearchVideosFromVideoListResponse searchVideoListFromVideoGroup(
			SearchVideosFromVideoListRequest request) {
		String apiUrl = propertyConfigure.getProperties("youku.api.search_videolist_by_groupid") ;
		ZFAssert.notBlank(apiUrl, "优酷根据专辑id搜索视频列表api地址youku.api.search_videolist_by_groupid未正确配置"); 
		String clientId = getClientId() ;
		request.setClientId(clientId);
		String requestUrl = YoukuRequestUtil.buildRequestUrl(apiUrl, request) ;
		String responseStr = HttpClientUtils.sendGetRequest(requestUrl);
		if(StringUtils.isBlank(responseStr)){
			log.warn("请求结果为空！"); 
			return null ;
		}
		if(isError(responseStr)){
			log.error("根据专辑id查询专辑下面视频失败，{}",responseStr);
			return null ;
		}
		SearchVideosFromVideoListResponse response = JSON.parseObject(responseStr , SearchVideosFromVideoListResponse.class);
		return response;
	}
	
	@Override
	public List<Category> searchAllVideoCategories() {
		String categoriesFilepath = propertyConfigure.getProperties("youku.video.categories.filepath");
		ZFAssert.notBlank(categoriesFilepath, "video_categories.json文件路径未配置"); 
		try {
			String fileContent = IOUtils.toString(this.getClass().getResourceAsStream(categoriesFilepath), "UTF-8") ;
			return JSON.parseArray(fileContent, Category.class) ;
		} catch (Exception e) {
			log.error("读取配置文件{}失败" , categoriesFilepath);
		} 
		return null;
	}

	@Override
	public List<Category> searchAllVideoListCategories() {
		String categoriesFilepath = propertyConfigure.getProperties("youku.videolist.categories.filepath");
		ZFAssert.notBlank(categoriesFilepath, "videolist_categories.json文件路径未配置"); 
		try {
			String fileContent = IOUtils.toString(this.getClass().getResourceAsStream(categoriesFilepath), "UTF-8") ;
			return JSON.parseArray(fileContent, Category.class) ;
		} catch (Exception e) {
			log.error("读取配置文件{}失败" , categoriesFilepath);
		} 
		return null;
	}

	/**
	 * 获取clientId
	 * @return
	 */
	private String getClientId(){
		String clientId = propertyConfigure.getProperties("youku.client_id");
		ZFAssert.notBlank(clientId, "优酷client_id未配置");
		return clientId ;
	}


	/**
	 * 判断是否发生错误
	 * @param response
	 * @return
	 */
	private boolean isError(String response){
		return response.matches("\\{\"error\":\\{.*?\\}\\}");
	}

}

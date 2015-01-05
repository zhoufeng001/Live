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
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
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
	public SearchVideoByCategoryResponse searchByCategory(SearchVideoByCategoryRequest request) {
		String apiUrl = propertyConfigure.getProperties("api.search_by_category") ;
		ZFAssert.notBlank(apiUrl, "优酷根据标签搜索视频api地址api.search_by_tag未正确配置"); 
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
	public VideoDetailVo searchVideoDetail(
			@Notnull("videoId") SearchVideoDetailRequest request) {

		Long localVideoDetailId = localVideoService.selectVideoDetailId(VideoSite.YOUKU.getValue(), request.getVideoId());
		if(localVideoDetailId != null && localVideoDetailId > 0){
			ServiceResult<VideoDetailVo>  localVideoDetailVoResult = localVideoService.selectVideoWithDetailInfo(localVideoDetailId);
			if(localVideoDetailVoResult != null && localVideoDetailVoResult.isSuccess()){
				return localVideoDetailVoResult.getData() ;
			}
		}

		String apiUrl = propertyConfigure.getProperties("api.search_video_detail") ;
		ZFAssert.notBlank(apiUrl, "优酷搜索视频详情api地址api.search_video_detail未正确配置"); 
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

		VideoDetailWithBLOBs localVideoDetail = new VideoDetailWithBLOBs();
		localVideoDetail.setId(video.getId());
		localVideoDetail.setDescription(response.getDescription());
		localVideoDetail.setDuration(response.getDuration());
		localVideoDetail.setLink(response.getLink());
		localVideoDetail.setBigthumbnail(response.getBigThumbnail());
		if(response.getThumbnails() != null){  
			localVideoDetail.setThumbnails(JSON.toJSONString(response.getThumbnails())); 
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

		VideoDetailVo videoDetailVo = new VideoDetailVo();
		videoDetailVo.setVideo(video);
		videoDetailVo.setVideoDetail(localVideoDetail);
		return videoDetailVo;

	}

	@Override
	public List<Category> searchAllCategories() {
		String categoriesFilepath = propertyConfigure.getProperties("categories.filepath");
		ZFAssert.notBlank(categoriesFilepath, "categories.json文件路径未配置"); 
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
	 * 获取clientSecret
	 * @return
	 */
	private String getClientSecret(){
		String clientSecret = propertyConfigure.getProperties("youku.client_secret");
		ZFAssert.notBlank(clientSecret, "优酷client_secret未配置");
		return clientSecret ;
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

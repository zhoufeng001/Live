package com.zf.live.service.impl.video.local;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.VideoSite;
import com.zf.live.client.vo.video.local.LocalVideoSearchCondition;
import com.zf.live.client.vo.video.local.VideoDetailVo;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.mapper.VideoDetailMapperExt;
import com.zf.live.dao.mapper.VideoMapperExt;
import com.zf.live.dao.pojo.Video;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;
import com.zf.live.dao.pojo.VideoExample;
import com.zf.live.dao.pojo.VideoExample.Criteria;
import com.zf.live.dao.vo.Page;
import com.zf.live.service.impl.ServiceConst;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月5日 上午12:58:55
 */
@Component("localVideoService")
public class LocalVideoServiceImpl implements LocalVideoService{

	@Autowired
	private VideoMapperExt videoMapper ;

	@Autowired
	private VideoDetailMapperExt videoDetailMapper ;

	@Autowired
	private YoukuVideoSearchService youkuVideoSearchService ;
	
	@Override
	public ServiceResult<Long> saveVideo(
			@Notnull("videofrom") @Notnull("fromid") @Notnull("videoname") @Notnull("category") 
			Video video) {
		ServiceResult<Long> result = new ServiceResult<Long>() ;
		video.setFlag(0);
		video.setCreatetime(new Date());
		if(video.getViewCount() == null){
			video.setViewCount(0L);
		}
		if(video.getPublishtime() == null){
			video.setPublishtime(new Date()); 
		}
		if(video.getPraise() == null){
			video.setPraise(0L);
		}
		int insertResult = videoMapper.insert(video);
		if(insertResult > 0){
			result.setSuccess(true);
			result.setData(video.getId());
			return result;
		}else{
			result.setErrMssage("插入视频信息失败");
			return result;
		}
	}

	@Override
	public ServiceResult<Long> saveVideoDetail(@Notnull("id") VideoDetailWithBLOBs videoDetail) {
		ServiceResult<Long> result = new ServiceResult<Long>() ;
		int insertResult = videoDetailMapper.insert(videoDetail);
		if(insertResult > 0){
			result.setSuccess(true);
			result.setData(videoDetail.getId());
			return result;
		}else{
			result.setErrMssage("插入视频信息详细信息失败");
			return result;
		}
	}

	@Override
	public ServiceResult<Long> saveVideoWithDetail(VideoDetailVo videoDetailVo) {
		ServiceResult<Long> result = null ;
		result = saveVideo(videoDetailVo.getVideo());
		if(!result.isSuccess()){
			return result ;
		}
		videoDetailVo.getVideoDetail().setId(result.getData());
		result = saveVideoDetail(videoDetailVo.getVideoDetail());
		return result;
	}

	@Override
	public Boolean existVideo(@Notnull Byte videofrom, @Notnull String fromId) {
		VideoExample query = new VideoExample() ;
		query.createCriteria().andVideofromEqualTo(videofrom)
		.andFromidEqualTo(fromId);
		query.setPage(new Page(0, 1));
		List<Video> videos = videoMapper.selectByExample(query); 
		return (videos != null && videos.size() > 0); 
	}

	@Override
	public Video selectVideo(@Notnull Byte videofrom, @Notnull String fromId) {
		VideoExample query = new VideoExample() ;
		query.createCriteria().andVideofromEqualTo(videofrom)
		.andFromidEqualTo(fromId);
		query.setPage(new Page(0, 1));
		List<Video> videos = videoMapper.selectByExample(query); 
		if(videos != null && videos.size() > 0){
			return videos.get(0);
		}
		return null ;
	}


	@Override
	public boolean existVideoDetail(Byte videofrom, String fromId) {
		Integer count =videoDetailMapper.countVideoDetail(videofrom, fromId);
		return count == null ? false : (count > 0); 
	}

	@Override
	public Long selectVideoDetailId(Byte videofrom, String fromId) {
		return videoDetailMapper.selectVideoDetailId(videofrom, fromId); 
	}


	@Override
	public ServiceResult<VideoDetailVo> selectVideoWithDetailInfo(@Notnull Long videoId, boolean incrementViewCount) {
		ServiceResult<VideoDetailVo> result = new ServiceResult<VideoDetailVo>() ;
		VideoDetailVo videoDetailVo = new VideoDetailVo();
		Video video = videoMapper.selectByPrimaryKey(videoId);
		if(video == null){
			result.setErrMssage("该视频不存在");
			return result;
		}
		VideoDetailWithBLOBs videoDetail = videoDetailMapper.selectByPrimaryKey(videoId);
		if(videoDetail == null){
			if(video.getVideofrom() == VideoSite.YOUKU.getValue()){
				SearchVideoDetailRequest request = new SearchVideoDetailRequest();
				request.setVideoId(video.getFromid()); 
				videoDetail = youkuVideoSearchService.searchYoukuVideoDetailAndSave(video.getId(), request) ;
			}
		}
		videoDetailVo.setVideo(video);
		videoDetailVo.setVideoDetail(videoDetail);
		result.setSuccess(true);
		result.setData(videoDetailVo);

		//观看次数+1
		if(incrementViewCount){
			Video updateVideo = new Video();
			updateVideo.setId(video.getId()); 
			Long viewCount = video.getViewCount();
			if(viewCount == null){
				viewCount = 0L ;
			}
			viewCount++ ;
			updateVideo.setViewCount(viewCount);
			videoMapper.updateByPrimaryKeySelective(updateVideo) ;
		}

		return result; 
	}

	@Override
	public PagedVo<Video> searchVideos(
			@Notnull("page") @Notnull("pageSize") 
			LocalVideoSearchCondition condition) {
		PagedVo<Video> result = new PagedVo<Video>();

		VideoExample query = new VideoExample() ;
		Criteria criteria = query.createCriteria();
		if(condition != null){
			if(condition.getCategory()!=null && condition.getCategory().size() > 0){ 
				if(condition.getCategory().contains("其他")){
					condition.getCategory().addAll(ServiceConst.CATEGORY_OTHER_INCLUED); 
				}
				criteria.andCategoryIn(condition.getCategory()) ;
			}
			if(StringUtils.isNotBlank(condition.getKeyword())){
				criteria.andVideonameLike("%" + condition.getKeyword() + "%"); 
			}
			if(condition.getPage() != null && condition.getPageSize() != null){
				query.setPage(new Page((condition.getPage() - 1) * condition.getPageSize(), condition.getPageSize()));
			}
			if(StringUtils.isNotBlank(condition.getOrderBy())){
				query.setOrderByClause(condition.getOrderBy());
			}
		}
		List<Video> videos = videoMapper.selectByExample(query);
		int total = videoMapper.countByExample(query) ;

		result.setData(videos);
		result.setPage(condition.getPage()); 
		result.setPageSize(condition.getPageSize()); 
		result.setCount(videos == null ? 0 : videos.size());
		result.setTotalRecored(total); 
		result.setData(videos);
		return result; 
	}

	@Override
	public Video selectVideoById(@Notnull Long localVideoId) {
		return videoMapper.selectByPrimaryKey(localVideoId);
	}

	@Override
	public boolean updateVideoBySelective(@Notnull("id") Video video) {
		return videoMapper.updateByPrimaryKeySelective(video) > 0;
	}

}

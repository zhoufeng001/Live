package com.zf.live.service.impl.video.local;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.local.LocalVideoServiceV2;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.VideoSite;
import com.zf.live.client.vo.video.local.LocalVideoDetailVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchConditionV2;
import com.zf.live.common.util.UUID;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.mapper.LocalVideoMapperExt;
import com.zf.live.dao.mapper.VideoDetailMapperExt;
import com.zf.live.dao.pojo.LocalVideo;
import com.zf.live.dao.pojo.LocalVideoExample.Criteria;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;
import com.zf.live.dao.pojo.vo.video.LocalVideoExampleVo;
import com.zf.live.dao.vo.Page;
import com.zf.live.dao.vo.video.LocalVideoVo;

/**
 * 本地视频服务
 * 分表实现
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月6日 上午12:12:42
 */
@Component("localVideoServiceV2")
public class LocalVideoServiceImplV2 implements LocalVideoServiceV2{

	private static final Logger log = LoggerFactory.getLogger(LocalVideoServiceImplV2.class);

	@Autowired
	private LocalVideoMapperExt localVideoMapper ;

	@Autowired
	private VideoDetailMapperExt videoDetailMapper ;

	@Autowired
	private YoukuVideoSearchService youkuVideoSearchService ;

	/**
	 * 分类表对应关系
	 * key:分类  value:表名
	 */
	public static final Map<String, String> videoTableSectionMap = new HashMap<String, String>();
	static{
		videoTableSectionMap.put("亲子","local_video001");
		videoTableSectionMap.put("体育","local_video002");
		videoTableSectionMap.put("其他","local_video003");
		videoTableSectionMap.put("创意视频","local_video004");
		videoTableSectionMap.put("动漫","local_video005");
		videoTableSectionMap.put("娱乐","local_video006");
		videoTableSectionMap.put("广告","local_video007");
		videoTableSectionMap.put("微电影","local_video008");
		videoTableSectionMap.put("拍客","local_video009");
		videoTableSectionMap.put("搞笑","local_video010");
		videoTableSectionMap.put("教育","local_video011"); 
		videoTableSectionMap.put("旅游","local_video012");
		videoTableSectionMap.put("时尚","local_video013");
		videoTableSectionMap.put("汽车","local_video014");
		videoTableSectionMap.put("游戏","local_video015");
		videoTableSectionMap.put("生活","local_video016");
		videoTableSectionMap.put("电影","local_video017");
		videoTableSectionMap.put("电视剧","local_video018");
		videoTableSectionMap.put("科技","local_video019");
		videoTableSectionMap.put("纪录片","local_video020");
		videoTableSectionMap.put("综艺","local_video021");
		videoTableSectionMap.put("网剧","local_video022");
		videoTableSectionMap.put("自拍","local_video023");
		videoTableSectionMap.put("资讯","local_video024");
		videoTableSectionMap.put("音乐","local_video025");
	}


	@Override
	public ServiceResult<String> saveVideo(@Notnull("videofrom") @Notnull("fromid") @Notnull("videoname") @Notnull("category") 
	LocalVideo video) {
		LocalVideoVo videoVo = new LocalVideoVo() ;
		BeanUtils.copyProperties(video, videoVo);  
		ServiceResult<String> result = new ServiceResult<String>() ;
		String category = videoVo.getCategory() ;
		String table = videoTableSectionMap.get(category);
		if(StringUtils.isBlank(table)){
			result.setErrMssage("没有存储该分类["+ category +"]视频对应的表！"); 
			return result ;
		}
		videoVo.setTable(table); 
		videoVo.setFlag(0);
		videoVo.setCreatetime(new Date());
		if(videoVo.getViewCount() == null){
			videoVo.setViewCount(0L);
		}
		if(videoVo.getPublishtime() == null){
			videoVo.setPublishtime(new Date()); 
		}
		if(videoVo.getPraise() == null){
			videoVo.setPraise(0L);
		}
		String baseId = UUID.newUUID() ;
		String idSuffix = table.replaceAll("local_video(.*)", "$1") ;
		String id = baseId + idSuffix ;
		videoVo.setId(id); 
		int insertResult = localVideoMapper.saveToSpecificTable(videoVo); 
		if(insertResult > 0){
			result.setSuccess(true);
			result.setData(id);
			return result;
		}else{
			result.setErrMssage("插入视频信息失败");
			return result;
		}
	}


	@Override
	public LocalVideoVo selectVideoById(@Notnull String localVideoId) {
		String table = getTableById(localVideoId) ; 
		if(StringUtils.isBlank(table)){
			log.warn("没找到videoId[{}]所对应的表");
			return null ;
		}
		return localVideoMapper.selectFromSpecificTableById(localVideoId, table) ;
	}

	@Override
	public boolean updateVideoBySelective(
			@Notnull("id")
			LocalVideo video) {
		LocalVideoVo videoVo = new LocalVideoVo();
		BeanUtils.copyProperties(video, videoVo); 
		String table = getTableById(videoVo.getId()) ; 
		if(StringUtils.isBlank(table)){
			log.warn("没找到videoId[{}]所对应的表");
			return false ;
		}
		videoVo.setTable(table);
		int updateCount = localVideoMapper.updateByPrimaryKeySelectiveToSpecificTable(videoVo) ;
		return updateCount > 0;
	}
	
	@Override
	public ServiceResult<String> saveVideoDetail(
			VideoDetailWithBLOBs videoDetail) {
		ServiceResult<String> result = new ServiceResult<String>() ;
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
	public ServiceResult<String> saveVideoWithDetail(
			LocalVideoDetailVo localVideoDetailVo) {
		ServiceResult<String> result = null ;
		result = saveVideo(localVideoDetailVo.getVideo()); 
		if(!result.isSuccess()){
			return result ;
		}
		localVideoDetailVo.getVideoDetail().setId(result.getData());
		result = saveVideoDetail(localVideoDetailVo.getVideoDetail());
		return result;
	}

	@Override
	public Boolean existVideo(String category, Byte videofrom, String fromId) {
		String table = videoTableSectionMap.get(category);
		if(StringUtils.isBlank(table)){
			log.warn("没有存储该分类["+ category +"]视频对应的表！"); 
			return false ;
		}
		LocalVideoExampleVo query = new LocalVideoExampleVo() ;
		query.createCriteria().andVideofromEqualTo(videofrom)
		.andFromidEqualTo(fromId);
		query.setPage(new Page(0, 1));
		query.setTable(table); 
		List<LocalVideoVo> videos = localVideoMapper.selectByExampleFromSpecificTable(query) ;
		return (videos != null && videos.size() > 0); 
	}

	@Override
	public LocalVideoVo selectVideo(String category, Byte videofrom, String fromId) {
		String table = videoTableSectionMap.get(category);
		if(StringUtils.isBlank(table)){
			log.warn("没有存储该分类["+ category +"]视频对应的表！"); 
			return null ;
		}
		LocalVideoExampleVo query = new LocalVideoExampleVo() ;
		query.createCriteria().andVideofromEqualTo(videofrom)
		.andFromidEqualTo(fromId);
		query.setPage(new Page(0, 1));
		query.setTable(table); 
		List<LocalVideoVo> videos = localVideoMapper.selectByExampleFromSpecificTable(query); 
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
	public String selectVideoDetailId(String category, Byte videofrom,
			String fromId) {
		return videoDetailMapper.selectVideoDetailId(videofrom, fromId); 
	}

	@Override
	public ServiceResult<LocalVideoDetailVo> selectVideoWithDetailInfo(
			@Notnull String localVideoId, boolean incrementViewCount) {
		ServiceResult<LocalVideoDetailVo> result = new ServiceResult<LocalVideoDetailVo>() ;
		LocalVideoDetailVo localVideoDetailVo = new LocalVideoDetailVo();
		LocalVideoVo localVideo = selectVideoById(localVideoId) ;
		if(localVideo == null){
			result.setErrMssage("该视频不存在");
			return result;
		}
		VideoDetailWithBLOBs videoDetail = videoDetailMapper.selectByPrimaryKey(localVideoId);
		if(videoDetail == null){
			if(localVideo.getVideofrom() == VideoSite.YOUKU.getValue()){
				SearchVideoDetailRequest request = new SearchVideoDetailRequest();
				request.setVideoId(localVideo.getFromid()); 
				videoDetail = youkuVideoSearchService.searchYoukuVideoDetailAndSave(localVideo.getId(), request) ;
			}
		}
		localVideoDetailVo.setVideo(localVideo);
		localVideoDetailVo.setVideoDetail(videoDetail);
		result.setSuccess(true);
		result.setData(localVideoDetailVo);

		//观看次数+1
		if(incrementViewCount){
			LocalVideoVo updateVideo = new LocalVideoVo();
			updateVideo.setId(localVideo.getId()); 
			Long viewCount = localVideo.getViewCount();
			if(viewCount == null){
				viewCount = 0L ;
			}
			viewCount++ ;
			updateVideo.setViewCount(viewCount);
			updateVideoBySelective(updateVideo) ;
		}
		return result; 
	}

	@Override
	public PagedVo<LocalVideoVo> searchVideos(
			@Notnull LocalVideoSearchConditionV2 condition , boolean searchTotalCount) {
		PagedVo<LocalVideoVo> result = new PagedVo<LocalVideoVo>();
		LocalVideoExampleVo query = new LocalVideoExampleVo() ;
		Criteria criteria = query.createCriteria() ;
		String table = null ;
		if(StringUtils.isBlank(condition.getCategory())){
			table = "local_video";
		}else{
			if(condition.getCategory().equals("其他")){
				table = "local_video_others";
			}else{
				table = videoTableSectionMap.get(condition.getCategory());
			}
		}
		if(StringUtils.isBlank(table)){
			log.warn("没有存储该分类["+ condition.getCategory() +"]视频对应的表！"); 
			return result ;
		}
		query.setTable(table);
		if(StringUtils.isNotBlank(condition.getKeyword())){
			criteria.andVideonameLike("%" + condition.getKeyword() + "%"); 
		}
		if(condition.getPage() != null && condition.getPageSize() != null){
			query.setPage(new Page((condition.getPage() - 1) * condition.getPageSize(), condition.getPageSize()));
		}
		if(StringUtils.isNotBlank(condition.getOrderBy())){
			query.setOrderByClause(condition.getOrderBy());
		}
		
		int total = 0 ;
		List<LocalVideoVo> videos = null ;
		if(searchTotalCount){
			LocalVideoExampleVo countQuery = new LocalVideoExampleVo() ;
			countQuery.setTable(table);
			Criteria countCriteria = countQuery.createCriteria() ;
			if(StringUtils.isNotBlank(condition.getKeyword())){
				countCriteria.andVideonameLike("%" + condition.getKeyword() + "%"); 
			}
			total = localVideoMapper.countByExample(query) ;
		}
		
		result.setTotalRecored(total); 
		if(!searchTotalCount || total > 0){
			videos = localVideoMapper.selectByExampleFromSpecificTable(query) ;
		}
		result.setData(videos);
		result.setPage(condition.getPage()); 
		result.setPageSize(condition.getPageSize()); 
		result.setCount(videos == null ? 0 : videos.size());
		result.setData(videos);
		return result; 
	}
	
	
	@Override
	public PagedVo<LocalVideoVo> searchVideosOrderByAudienceCount(
			@Notnull("category") LocalVideoSearchConditionV2 condition) {
		String table = null ;
		if(StringUtils.isBlank(condition.getCategory())){
			table = "local_video";
		}else{
			if(condition.getCategory().equals("其他")){
				table = "local_video_others";
			}else{
				table = videoTableSectionMap.get(condition.getCategory());
			}
		}
		if(StringUtils.isBlank(table)){
			log.warn("没有存储该分类["+ condition.getCategory() +"]视频对应的表！"); 
			return null ;
		}
		
		PagedVo<LocalVideoVo> result = new PagedVo<LocalVideoVo>();
		Integer videoCount = localVideoMapper.countByCategoryFromAudienceCounte(condition.getCategory()) ;
		if(videoCount <= 0){
			result.setCount(0);
			result.setData(null);
			result.setPage(1);
			result.setPageSize(condition.getPageSize());
			result.setTotalRecored(0);
			return result ;
		}
		int begin = (condition.getPage() - 1) * condition.getPageSize();
		int length = condition.getPageSize();
		List<LocalVideoVo> videoList = localVideoMapper.selectByCategoryOrderByAudienceCount(table ,condition.getCategory(), begin, length) ;
		result.setCount(videoList.size());
		result.setData(videoList);
		result.setPage(condition.getPage());
		result.setPageSize(condition.getPageSize());
		result.setTotalRecored(videoCount); 
		return result;
	}
	

	/**
	 * 根据本地视频ID获取存放视频的表名
	 * @param id
	 * @return
	 */
	private String getTableById(String id){
		if(StringUtils.isBlank(id)){
			return null ;
		}
		String tableSuffix = id.substring(32) ;
		String table = "local_video" + tableSuffix ;
		return table ;
	}


}

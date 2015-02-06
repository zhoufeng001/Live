package com.zf.live.service.impl.video.local;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.local.LocalVideoServiceV2;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.local.LocalVideoDetailVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchConditionV2;
import com.zf.live.common.util.UUID;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.mapper.LocalVideoMapperExt;
import com.zf.live.dao.pojo.LocalVideo;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

/**
 * 本地视频服务
 * 分表实现
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月6日 上午12:12:42
 */
@Component("localVideoServiceImplV2")
public class LocalVideoServiceImplV2 implements LocalVideoServiceV2{
	
	private static final Logger log = LoggerFactory.getLogger(LocalVideoServiceImplV2.class);
	
	@Autowired
	private LocalVideoMapperExt localVideoMapper ;
	
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
		ServiceResult<String> result = new ServiceResult<String>() ;
		String category = video.getCategory() ;
		String table = videoTableSectionMap.get(category);
		if(StringUtils.isBlank(table)){
			result.setErrMssage("没有存储该分类["+ category +"]视频对应的表！"); 
			return result ;
		}
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
		String baseId = UUID.newUUID() ;
		String idSuffix = table.replaceAll("local_video(.*)", "$1") ;
		String id = baseId + idSuffix ;
		video.setId(id); 
		int insertResult = localVideoMapper.saveToSpecificTable(video, table); 
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
	public LocalVideo selectVideoById( String localVideoId) {
		if(StringUtils.isBlank(localVideoId)){
			return null ;
		}
		String tableSuffix = localVideoId.substring(32) ;
		String table = "local_video" + tableSuffix ;
		if(StringUtils.isBlank(table)){
			log.warn("没找到videoId[{}]所对应的表");
			return null ;
		}
		return localVideoMapper.selectFromSpecificTableById(localVideoId, table) ;
	}
	@Override
	public boolean updateVideoBySelective(LocalVideo video) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public ServiceResult<String> saveVideoDetail(
			VideoDetailWithBLOBs videoDetail) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ServiceResult<String> saveVideoWithDetail(
			LocalVideoDetailVo localVideoDetailVo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean existVideo(String category, Byte videofrom, String fromId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LocalVideo selectVideo(String category, Byte videofrom, String fromId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean existVideoDetail(Byte videofrom, String fromId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String selectVideoDetailId(String category, Byte videofrom,
			String fromId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ServiceResult<LocalVideoDetailVo> selectVideoWithDetailInfo(
			 String videoId, boolean incrementViewCount) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PagedVo<LocalVideo> searchVideos(
			LocalVideoSearchConditionV2 condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

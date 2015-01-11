package com.zf.live.service.impl.video.local;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.local.LocalVideoGroupService;
import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.mapper.VideoGroupMapperExt;
import com.zf.live.dao.mapper.VideoMapperExt;
import com.zf.live.dao.pojo.Video;
import com.zf.live.dao.pojo.VideoExample;
import com.zf.live.dao.pojo.VideoGroup;
import com.zf.live.dao.pojo.VideoGroupExample;
import com.zf.live.dao.vo.Page;

/**
 * 本地视频专辑服务实现
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 上午11:31:24
 */
@Component("localVideoGroupService")
public class LocalVideoGroupServiceImpl implements LocalVideoGroupService{
	
	@Autowired
	private VideoGroupMapperExt videoGroupMapper ;

	@Autowired
	private VideoMapperExt videoMapper ;
	
	@Autowired
	private LocalVideoService videoService ;
	
	@Override
	public ServiceResult<Long> saveVideoGroup(
			@Notnull("videofrom") @Notnull("fromid") @Notnull("groupName") @Notnull("category")
			VideoGroup videoGroup) {
		ServiceResult<Long> result = new ServiceResult<Long>();
		videoGroup.setCreatetime(new Date());
		if(videoGroup.getPublished() == null){
			videoGroup.setPublished(new Date());
		}
		videoGroup.setViewCount(0L);
		int insertCount = videoGroupMapper.insertSelective(videoGroup); 
		if(insertCount <= 0){
			result.setErrMssage("插入视频失败");
			return result;
		}
		result.setSuccess(true);
		result.setData(videoGroup.getId());
		return result; 
	}
	

	@Override
	public ServiceResult<VideoGroup> selectThirdVideoGroup(@Notnull Byte videoFrom,
			@Notnull String thirdGroupId) {
		ServiceResult<VideoGroup> result = new ServiceResult<VideoGroup>();
		VideoGroupExample query = new VideoGroupExample();
		query.createCriteria().andVideofromEqualTo(videoFrom).andFromidEqualTo(thirdGroupId) ; 
		query.setPage(new Page(0, 1));
		List<VideoGroup> videoList = videoGroupMapper.selectByExample(query);   
		if(videoList == null || videoList.size() <= 0){
			result.setSuccess(true);
			return result;
		}
		result.setSuccess(true);
		result.setData(videoList.get(0));
		return result;
	}

	@Override
	public Boolean existThirdVideoGroup(Byte videoFrom, String thirdGroupId) {
		ServiceResult<VideoGroup> result = selectThirdVideoGroup(videoFrom , thirdGroupId);  
		if(result != null && result.isSuccess()){
			return (result.getData() != null) ;
		}
		return false ;
	}

	@Override
	public VideoGroup selectLocalVideoGroup(@Notnull Long groupId) {
		VideoGroup videoGroup = videoGroupMapper.selectByPrimaryKey(groupId);
		return videoGroup;
	}

	@Override
	public Boolean existLocalVideoGroup(Long groupId) {
		return (selectLocalVideoGroup(groupId) != null);
	}
	
	@Override
	public List<Video> getVideosByGroupId(Long groupId) {
		VideoExample query = new VideoExample();
		query.createCriteria().andGroupIdEqualTo(groupId);
		query.setOrderByClause(" group_seq asc , createtime asc ");
		return videoMapper.selectByExample(query);
	}


}  

package com.zf.live.service.impl.video.local;

import java.util.HashMap;
import java.util.Map;

import com.zf.live.client.video.local.LocalVideoServiceV2;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.local.LocalVideoDetailVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchConditionV2;
import com.zf.live.dao.pojo.LocalVideo;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

/**
 * 本地视频服务
 * 分表实现
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月6日 上午12:12:42
 */
public class LocalVideoServiceImplV2 implements LocalVideoServiceV2{
	
	/**
	 * 分类表对应关系
	 * key:分类  value:表名
	 */
	public static final Map<String, String> videoTableSectionMap = new HashMap<String, String>();
	static{
		videoTableSectionMap.put("local_video001", "亲子");
		videoTableSectionMap.put("local_video002", "体育");
		videoTableSectionMap.put("local_video003", "其他");
		videoTableSectionMap.put("local_video004", "创意视频");
		videoTableSectionMap.put("local_video005", "动漫");
		videoTableSectionMap.put("local_video006", "娱乐");
		videoTableSectionMap.put("local_video007", "广告");
		videoTableSectionMap.put("local_video008", "微电影");
		videoTableSectionMap.put("local_video009", "拍客");
		videoTableSectionMap.put("local_video010", "搞笑");
		videoTableSectionMap.put("local_video011", "教育"); 
		videoTableSectionMap.put("local_video012", "旅游");
		videoTableSectionMap.put("local_video013", "时尚");
		videoTableSectionMap.put("local_video014", "汽车");
		videoTableSectionMap.put("local_video015", "游戏");
		videoTableSectionMap.put("local_video016", "生活");
		videoTableSectionMap.put("local_video017", "电影");
		videoTableSectionMap.put("local_video018", "电视剧");
		videoTableSectionMap.put("local_video019", "科技");
		videoTableSectionMap.put("local_video020", "纪录片");
		videoTableSectionMap.put("local_video021", "综艺");
		videoTableSectionMap.put("local_video022", "网剧");
		videoTableSectionMap.put("local_video023", "自拍");
		videoTableSectionMap.put("local_video024", "资讯");
		videoTableSectionMap.put("local_video025", "音乐");
	}
	@Override
	public ServiceResult<String> saveVideo(LocalVideo video) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LocalVideo selectVideoById(String category, String localVideoId) {
		// TODO Auto-generated method stub
		return null;
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
			String category, String videoId, boolean incrementViewCount) {
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

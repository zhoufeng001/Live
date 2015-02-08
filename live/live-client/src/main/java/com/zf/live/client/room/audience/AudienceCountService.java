package com.zf.live.client.room.audience;

import com.zf.live.client.vo.room.Audience;
import com.zf.live.dao.pojo.AudienceCount;

/**
 * 观众数量服务
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月8日 下午6:58:36
 */
public interface AudienceCountService {
	
	/**
	 * 添加一个观众
	 * @param audience
	 */
	void addAudience(Audience audience);

	/**
	 * 移除一个用户
	 * @param audience
	 */
	void removeAudience(Audience audience);
	
	/**
	 * 获取指定视频的观众信息
	 * @param videoId
	 * @return
	 */
	AudienceCount getByVideoId(String videoId);
	
	/**
	 * 获取指定视频观众人数
	 * @param videoId
	 * @return
	 */
	int getAudienceCount(String videoId);
	
	/**
	 * 根据视频id查询登录人数
	 * @param id
	 * @return
	 */
	int getUserCount(String videoId);
	
	/**
	 * 根据视频id查询游客人数
	 * @param id
	 * @return
	 */
	int getTouristCount(String videoId);
	
}

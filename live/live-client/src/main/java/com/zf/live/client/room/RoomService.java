package com.zf.live.client.room;

import java.util.List;

import com.zf.live.client.vo.room.Audience;

/**
 * 包含用户在视频播放房间的基本操作
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月20日 下午10:14:16
 */
public interface RoomService {
	
	/**
	 * 进入房间
	 * @param videoId
	 * @param audience
	 */
	void comeInRoom(Long videoId , Audience audience);
	
	/**
	 * 退出房间
	 * @param videoId
	 * @param userId
	 */
	void outRoom(Long videoId ,String sessionId);
	
	/**
	 * 获取房间在线人数（登录用户）
	 * @param videoId
	 * @return
	 */
	Integer getRoomUserCount(Long videoId);
	
	/**
	 * 获取房间游客人数（未登录用户）
	 * @return
	 */
	Integer getRoomTouristCount(Long videoId);
	
	/**
	 * 获取房间用户列表
	 * （包含登录用户和未登录用户，未排序，需要自己排序）
	 * @return
	 */
	List<Audience> getRoomUserList(Long videoId);
	
}

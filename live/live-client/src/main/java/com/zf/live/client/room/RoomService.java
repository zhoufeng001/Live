package com.zf.live.client.room;

import java.util.List;

import com.zf.live.client.vo.room.Audience;
import com.zf.live.client.vo.room.RoomInfo;
import com.zf.live.client.vo.room.VideoSessionPair;

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
	 * 批量退出房间（用于聊天服务器关闭时退出该服务器上面所有用户）
	 * @param vsps
	 */
	void batchOutRoom(List<VideoSessionPair> vsps );
	
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
	List<Audience> getRoomAudience(Long videoId);
	
	/**
	 * 根据VideoId和sessionId获取观众信息
	 * @param sessionId
	 * @param videoId
	 * @return
	 */
	Audience getAudienceBySessionId(Long videoId ,String sessionId);
	
	/**
	 * 获取房间信息，包含观众列表、在线人数等信息
	 * @param videoId
	 * @return
	 */
	RoomInfo getRoomInfo(Long videoId);
	
}

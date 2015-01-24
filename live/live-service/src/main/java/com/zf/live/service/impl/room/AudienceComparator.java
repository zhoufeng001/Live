package com.zf.live.service.impl.room;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.zf.live.client.vo.room.Audience;

/**
 * 观众排序器（登录用户在前，游客在后，根据进入房间时间排序）
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月24日 上午1:07:59
 */
@Component("audienceComparator")
public class AudienceComparator implements Comparator<Audience>{
	@Override
	public int compare(Audience o1, Audience o2) {
		return o2.getComeInTime().compareTo(o1.getComeInTime()); 
	}
}
package com.zf.live.service.impl.room.filter;

import java.util.List;

import com.zf.live.client.vo.room.Audience;

/**
 * 观众列表过滤器
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月24日 上午1:16:59
 */
public interface AudienceFilter {

	void filter(List<Audience> audiences);
	
}

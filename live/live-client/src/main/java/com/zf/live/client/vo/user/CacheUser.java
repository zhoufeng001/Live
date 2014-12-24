package com.zf.live.client.vo.user;

import java.io.Serializable;

import com.zf.live.dao.pojo.Lvuser;

/**
 * 登录信息（存于Redis）
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月23日 下午5:22:37
 */
public class CacheUser extends Lvuser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7584456257472614757L;
	
	/**
	 * 登录时间
	 */
	private Long cacheTime ;

	public Long getCacheTime() {
		return cacheTime;
	}

	public void setCacheTime(Long cacheTime) {
		this.cacheTime = cacheTime;
	}
	
}

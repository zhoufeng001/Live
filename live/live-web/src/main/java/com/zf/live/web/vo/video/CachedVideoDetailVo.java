package com.zf.live.web.vo.video;

import com.zf.live.client.vo.video.local.VideoDetailVo;

/**
 * 缓存视频信息对象
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月15日 上午2:39:53
 */
public class CachedVideoDetailVo extends VideoDetailVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6648714542966477484L;
	
	/**
	 * 缓存期间新增赞数量
	 */
	private int incrementPraiseCount ;
	
	/**
	 * 缓存期间新增view数量
	 */
	private int incrementViewCount ;

	public int getIncrementPraiseCount() {
		return incrementPraiseCount;
	}

	public void setIncrementPraiseCount(int incrementPraiseCount) {
		this.incrementPraiseCount = incrementPraiseCount;
	}

	public int getIncrementViewCount() {
		return incrementViewCount;
	}

	public void setIncrementViewCount(int incrementViewCount) {
		this.incrementViewCount = incrementViewCount;
	}

}

package com.zf.live.client.vo.video.local;

import java.io.Serializable;
import java.util.List;

/**
 * 根据本地视频分类搜索本地视频参数
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月4日 上午12:23:06
 */
public class LocalVideoCategorySearchCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5046919579138877105L;
	
	private List<String> categories ;
	
	private String orderBy ;
	
	private int limitFrom ;
	
	private int limitLength ;

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getLimitFrom() {
		return limitFrom;
	}

	public void setLimitFrom(int limitFrom) {
		this.limitFrom = limitFrom;
	}

	public int getLimitLength() {
		return limitLength;
	}

	public void setLimitLength(int limitLength) {
		this.limitLength = limitLength;
	}
	
}

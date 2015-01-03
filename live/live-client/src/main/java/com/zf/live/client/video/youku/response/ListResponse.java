package com.zf.live.client.video.youku.response;

import java.io.Serializable;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:25:52
 */
public abstract class ListResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1833951915335224719L;

	/**
	 * 总条数
	 */
	protected Integer total ;
	
	/**
	 * 当前页
	 */
	protected Integer page ;
	
	
	/**
	 * 结果集数量
	 */
	protected Integer count ;


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}

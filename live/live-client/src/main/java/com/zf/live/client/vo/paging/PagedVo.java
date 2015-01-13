package com.zf.live.client.vo.paging;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月13日 下午9:53:46
 */
public class PagedVo<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1939079189494007376L;

	/**
	 * 数据结果集
	 */
	private List<T> data ;

	/**
	 * 查询出来的结果集数量
	 */
	private int count ;

	/**
	 * 当前页
	 */
	private int page ;

	/**
	 * 页大小
	 */
	private int pageSize ;

	/**
	 * 总条数
	 */
	private int totalRecored ;

	/**
	 * 总页数
	 */
	private int totalPage;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecored() {
		return totalRecored;
	}

	public void setTotalRecored(int totalRecored) {
		this.totalRecored = totalRecored;
	}

	public int getTotalPage() {
		totalPage = totalRecored % pageSize == 0 ? 
				(totalRecored / pageSize )
				: ((totalRecored / pageSize) + 1);
				return totalPage ;
	}

}


package com.zf.live.client.vo.video.local;

import java.io.Serializable;
import java.util.List;

/**
 * 本地视频搜索条件
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月5日 上午12:44:07
 */
public class LocalVideoSearchCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1970789093825625366L;
	
	/**
	 * 关键字
	 */
	private String keyword ;
	
	/**
	 * 分类
	 */
	private List<String> category ; 
	
	/**
	 * 第几页
	 */
	private Integer page ;
	
	/**
	 * 页大小
	 */
	private Integer pageSize ;
	
	private String orderBy ;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	/*
	public void orderByThirdPlaycount(Sort sort){
		if(this.orderBy == null || "".equals(this.orderBy.trim())){
			this.orderBy = " order by ";
		}else{
			this.orderBy += " , " ;
		}
		this.orderBy += " playcount " + sort.getValue();
	}
	
	public void orderByCreatetime(Sort sort){
		if(this.orderBy == null || "".equals(this.orderBy.trim())){
			this.orderBy = " order by ";
		}else{
			this.orderBy += " , " ;
		}
		this.orderBy = " order by createtime " + sort;
	}
	
	public void orderByThirdPraise(Sort sort){
		if(this.orderBy == null || "".equals(this.orderBy.trim())){
			this.orderBy = " order by ";
		}else{
			this.orderBy += " , " ;
		}
		this.orderBy = " order by praise " + sort;
	}
	*/
	
}

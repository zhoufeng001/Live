package com.zf.live.client.video.youku.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 视频分类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 下午11:05:58
 */
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6440249954080156567L;

	/**
	 * 分类ID
	 */
	private Integer id;
	
	/**
	 * 视频分类标识
	 */
	private String term ;
	
	/**
	 * 分类名称
	 */
	private String label;
	
	/**
	 * 该分类下面的所有类型
	 */
	private List<String> genres ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	
}


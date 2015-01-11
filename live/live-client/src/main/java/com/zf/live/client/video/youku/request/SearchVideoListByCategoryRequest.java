package com.zf.live.client.video.youku.request;

import com.zf.live.client.video.youku.RequestField;

/**
 * 根据分类搜索专辑
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 上午10:40:54
 */
public class SearchVideoListByCategoryRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3263438148706277948L;
	
	@RequestField("category")
	private String category ;
	
	@RequestField("period")
	private String period ;
	
	@RequestField("orderby")
	private String orderby;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
}

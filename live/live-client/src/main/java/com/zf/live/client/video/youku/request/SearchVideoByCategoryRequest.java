package com.zf.live.client.video.youku.request;

import com.zf.live.client.video.youku.RequestField;

/**
 * 根据视频分类搜索视频请求参数
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:08:03
 */
public class SearchVideoByCategoryRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5033733281022167890L;

	
	/**
	 * 标签 多个标签逗号分隔
	 */
	@RequestField("tag")
	private String tag ;
	
	/**
	 * 分类 综艺 娱乐 教育 旅游 时尚 母婴 资讯 原创 女性 搞笑 音乐 
	 * 电影 电视剧 体育 游戏 动漫 广告 生活 汽车 科技 其他
	 */
	@RequestField("category")
	private String category ;
	
	/**
	 * 时间范围 today: 今日 week: 本周 month: 本月 history: 历史
	 */
	@RequestField("period")
	private String period ;
	
	
	/**
	 * 排序 published: 发布时间 view-count: 总播放数 comment-count: 总评论数 
	 * reference-count: 总引用数 favorite-count: 总收藏数 relevance: 相关度
	 */
	@RequestField("orderby")
	private String orderby ;


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


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

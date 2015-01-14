package com.zf.live.web;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月15日 上午1:46:26
 */
public interface WebConst {

	/**
	 * Web Ehcache配置文件名
	 */
	public String WebEhcacheFileName = "ehcache-web.xml";
	
	public static interface EhCacheNames {
		
		/**
		 * 缓存分类指定视频
		 */
		public String categoryRecommendTopVideoCache = "categoryRecommendTopVideoCache";
		
		/**
		 * 视频详情缓存
		 */
		public String videoInfoCache = "videoInfoCache";
	}
	
}

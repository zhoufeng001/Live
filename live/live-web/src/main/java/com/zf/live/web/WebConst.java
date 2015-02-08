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
	
	/**
	 * 当前用户在Request中的key
	 */
	public String RequestUserKey = "user";
	
	/**
	 * 用户头像目录
	 */
	public static String userPhotoDir = "userphotos";
	
	/**
	 * session中保存用户登录成功后要跳转到的地址
	 */
	public static String sessionRefreshKey = "refresh";
	
	public static interface EhCacheNames {
		
		/**
		 * 缓存分类指定视频
		 */
		public String categoryRecommendTopVideoCache = "categoryRecommendTopVideoCache";
		
		/**
		 * 视频详情缓存
		 */
		public String videoInfoCache = "videoInfoCache";
		
		/**
		 * 对视频分类的第一页进行缓存
		 */
		public String videoPageCache = "videoPageCache";
	}
	
	/**
	 * 配置信息
	 * @author is_zhoufeng@163.com , QQ:243970446
	 * 2015年2月8日 下午4:45:41
	 */
	public static class Config{
		
		/**
		 * 项目地址
		 */
		public static String ctx  ;
		/**
		 * 文件服务器地址
		 */
		public static String fileServer  ;
		/**
		 * 静态资源服务器地址
		 */
		public static String staticServer  ;
		
	}
	
}

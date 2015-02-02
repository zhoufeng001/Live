package com.zf.live.service.impl;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月15日 上午1:45:51
 */
public interface ServiceConst {

	/**
	 * EhCache文件名
	 */
	public String ServiceEhCacheFileName = "ehcache-service.xml";
	
	/**
	 * 分类“其他”包含的分类
	 */ 
	public List<String> CATEGORY_OTHER_INCLUED = Arrays.asList("教育","纪录片","旅游","亲子","广告","创意视频","拍客");
	
	
}

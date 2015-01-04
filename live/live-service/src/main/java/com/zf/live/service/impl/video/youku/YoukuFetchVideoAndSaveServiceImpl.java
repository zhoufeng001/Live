package com.zf.live.service.impl.video.youku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.FetchVideosAndSaveService;
import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.service.YoukuVideoSearchService;

/**
 * 从优酷获取视频并更新到数据库
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 下午11:48:03
 */
@Component("youkuFetchVideoAndSaveService")
public class YoukuFetchVideoAndSaveServiceImpl implements FetchVideosAndSaveService{

	private static final Logger log = LoggerFactory.getLogger(YoukuFetchVideoAndSaveServiceImpl.class);
	
	
	/**
	 * 每页大小
	 */
	private static final Integer PAGE_SIZE = 50 ;
	
	@Autowired
	private YoukuVideoSearchService youkuVideoSearchService;
	
	@Override
	public void fetchAndSave() {
		SearchVideoByCategoryRequest request = new SearchVideoByCategoryRequest() ;
		request.setCount(PAGE_SIZE);
		youkuVideoSearchService.searchByCategory(request) ;
		
	}

}

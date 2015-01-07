package com.zf.live.fetchandsavevideo.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zf.live.client.video.FetchVideosAndSaveService;
import com.zf.live.service.impl.video.youku.YoukuFetchVideoAndSaveServiceImpl;

public class YoukuFetchAndSaveVideoTask {
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		FetchVideosAndSaveService youkuFAS = applicationContext.getBean(YoukuFetchVideoAndSaveServiceImpl.class);
		
		youkuFAS.fetchAndSave();		
		
	}

}

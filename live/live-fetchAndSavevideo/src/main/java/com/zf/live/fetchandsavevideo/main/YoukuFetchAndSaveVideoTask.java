package com.zf.live.fetchandsavevideo.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zf.live.client.video.FetchVideosAndSaveService;
import com.zf.live.service.impl.video.youku.YoukuFetchVideoAndSaveServiceImpl;

public class YoukuFetchAndSaveVideoTask {

	private static final Logger log = LoggerFactory.getLogger(YoukuFetchAndSaveVideoTask.class);
	
	public static void main(String[] args) {
		
		if(args == null || args.length == 0){
			log.error("参数不能为空，第一个参数为隔多少个小时执行一次！");
			return ;
		}
		Integer hour = Integer.valueOf((args[0]));

		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
		
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		final FetchVideosAndSaveService youkuFAS = applicationContext.getBean(YoukuFetchVideoAndSaveServiceImpl.class);
		
		applicationContext = null ;
		
		log.info("任务启动成功，每{}小时执行一次！" , hour);
		threadPool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					youkuFAS.fetchAndSave();
				} catch (Exception e) {
					log.error("----------抓取优酷视频出错---------------");
					log.error(e.getMessage()); 
				}		
			}
		}, 0 , hour , TimeUnit.HOURS);

		

		
	}

}

package com.zf.live.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.video.FetchVideosAndSaveService;
import com.zf.live.common.util.TimerUtil;

public class TestFetchAndSaveService extends ServiceBaseTester{

	@Autowired
	private FetchVideosAndSaveService fetchVideosAndSaveService ;
	
	@Test
	public void testFetch(){
		TimerUtil.start("");
		fetchVideosAndSaveService.fetchAndSave();
		System.out.println("共耗时：" + TimerUtil.timing());
	}
	
}

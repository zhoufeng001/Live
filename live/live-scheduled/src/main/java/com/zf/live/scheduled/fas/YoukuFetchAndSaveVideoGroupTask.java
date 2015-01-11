package com.zf.live.scheduled.fas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.scheduled.LiveTask;
import com.zf.live.service.impl.video.youku.YoukuFetchVideoListAndSaveServiceImpl;

@Component("youkuFetchAndSaveVideoGroupTask")
public class YoukuFetchAndSaveVideoGroupTask implements LiveTask{

	@Autowired
	private YoukuFetchVideoListAndSaveServiceImpl youkuFetchVideoListAndSaveService;
	
	@Override
	public void execute() {
		youkuFetchVideoListAndSaveService.fetchAndSave();
	}

}

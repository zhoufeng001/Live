package com.zf.live.scheduled.fas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.common.util.TimerUtil;
import com.zf.live.scheduled.LiveTask;
import com.zf.live.service.impl.video.youku.YoukuFetchVideoAndSaveServiceImpl;

/**
 * 定时抓取youku数据
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 上午4:31:10
 */
@Component("youkuFetchAndSaveVideoTask")
public class YoukuFetchAndSaveVideoTask implements LiveTask{

	private static final Logger log = LoggerFactory.getLogger(YoukuFetchAndSaveVideoTask.class);

	@Autowired
	private YoukuFetchVideoAndSaveServiceImpl youkuFetchVideoAndSaveServiceImpl ;

	@Override
	public void execute() {
		TimerUtil.start("");
		log.info("-----------YouKu FAS 开始执行----------------");
		youkuFetchVideoAndSaveServiceImpl.fetchAndSave();	
		log.info("-----------YouKu FAS 执行完毕，耗时{}秒----------------",TimerUtil.timing());
	}

}

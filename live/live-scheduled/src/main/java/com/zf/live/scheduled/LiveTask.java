package com.zf.live.scheduled;

/**
 * 任务超类
 * 只要实现了该类，并且实现类在该类所在的包以及子孙包下面就会自动被搜索到并注册
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 上午2:46:50
 */
public interface LiveTask {

	/**
	 * 具体执行任务
	 */
	void execute();
	
}

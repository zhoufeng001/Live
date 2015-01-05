package com.zf.live.common.util;

import java.util.Stack;

/**
 * 计时工具类
 * 用于给程序中的方法计时，来跟踪那些比较耗时的方法
 * 使用 ThreadLocal实现，可以安全的在多线程当中执行
 * 并支持子任务
 * @author zhoufeng
 */
public class TimerUtil {
	
	private static final ThreadLocal<Stack<TimerEntry>> timerEntry = new ThreadLocal<Stack<TimerEntry>>() ;
	
	/**
	 * 开始一个计时器
	 * @param name  
	 */
	public static void start(String name) {
		Stack<TimerEntry> timerEntries = timerEntry.get() ;
		if(timerEntries == null){
			timerEntries = new Stack<TimerEntry>();
			timerEntry.set(timerEntries) ;
		}
		
		TimerEntry entry = new TimerEntry() ;
		entry.name = name ;
		entry.startTiem = System.currentTimeMillis() ;
		entry.stopTime = -1 ;
		
		timerEntries.push(entry) ;

	}
	
	/**
	 * 结束当前的计时器
	 * @return返回当前计时器花费的时间（毫秒）
	 */
	public static long timing() {
		TimerEntry currentEntry = getCurrentTimerEntry();
		if(currentEntry != null){
			currentEntry.stopTime = System.currentTimeMillis() ;
			long timeConsuming = currentEntry.stopTime - currentEntry.startTiem ;
			timerEntry.get().pop(); 
			return timeConsuming ;
		}
		return -1;
	}
	
	/**
	 * 此方法应该在timing()方法之前调用 ，因为timing()方法操作完之后，会将当前的计时任务pop出栈
	 * @return当前计时器的名字
	 */
	public static String getCurrentNameTimerName(){
		TimerEntry currentEntry = getCurrentTimerEntry();
		if(currentEntry != null){
			return currentEntry.name ;
		}
		return null ;
	}
	
	/**
	 * 清除计时器
	 */
	public static void clear(){
		timerEntry.set(null) ;		
	}
	

	private static TimerEntry getCurrentTimerEntry(){
		Stack<TimerEntry> timerEntries = timerEntry.get() ;
		if(timerEntries != null){
			return timerEntries.peek() ;
		}
		return null ;
	}

	
	private static class TimerEntry{
		public String name ;
		public long startTiem ;
		public long stopTime ;
	}

	
}

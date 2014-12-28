package com.zf.live.common.generator;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.exception.LiveException;
import com.zf.live.client.user.IdxcodeGenerator;
import com.zf.live.client.user.LvuserService;

/**
 * 普通Idxcode生成器
 * 生成规则： [13579]{1}[年份最后一位]{1}[月]{2}[日]{2}[时]{2}[分]{2}[秒][2][1234567]{1}
 * 			  并排除预定义的好的exclude列表中的值
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月21日 上午12:39:53
 */
public class DefaultIdxcodeGenerator implements IdxcodeGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7370399283485018271L;

	@Autowired
	private LvuserService lvuserService ;

	private final String prefixs [] = {"1","3","5","7","9"};
	private final AtomicInteger prefixIndex = new AtomicInteger(0) ;
	private final String suffixs [] = {"1","2","3","4","5","6","7"} ;
	private final AtomicInteger suffixIndex = new AtomicInteger(0) ;

	/**
	 * 如果失败，最多重试3次
	 */
	private final int reTry = 3 ;


	@Override
	public String generate() {
		String idxcode = null ;
		Date currentTime = null;
		for(int i = 0 ; i < reTry ; i++){
			currentTime = new Date();
			idxcode = nextIdxcode(currentTime);
			boolean exist = lvuserService.existIdxcode(idxcode) ;
			if(!exist){
				return idxcode ;
			}
		}
		throw new LiveException("生成idxcode失败！") ;
	}


	@SuppressWarnings("deprecation")
	private String nextIdxcode(Date currentTime){
		StringBuilder idxcode = new StringBuilder() ;
		int prefixLength = prefixs.length ;
		prefixIndex.incrementAndGet() ;
		if(prefixIndex.intValue() >= prefixLength){
			prefixIndex.set(0);
		}
		String prefix = prefixs[prefixIndex.intValue()]; 
		idxcode.append(prefix) ;
		int yearLimit1 = currentTime.getYear() % 10 ;
		idxcode.append(yearLimit1) ;
		idxcode.append(String.format("%02d", currentTime.getMonth())) ;
		idxcode.append(String.format("%02d", currentTime.getDate()));
		idxcode.append(String.format("%02d", currentTime.getHours()));
		idxcode.append(String.format("%02d", currentTime.getMinutes()));
		idxcode.append(String.format("%02d", currentTime.getSeconds()));
		int suffixLength = suffixs.length ;
		if(suffixIndex.incrementAndGet() >= suffixLength){
			suffixIndex.set(0); 
		}
		String suffix = suffixs[suffixIndex.intValue()];
		idxcode.append(suffix) ;
		return idxcode.toString() ;
	}

}

package com.zf.live.service.impl.user;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.exception.LiveException;
import com.zf.live.client.user.LvuserService;

/**
 * 普通Idxcode生成器
 * 生成规则： [13579]{1}[年份最后一位]{1}[月]{2}[日]{2}[时]{2}[分]{2}[秒][2][1234567]{1}
 * 			  并排除预定义的好的exclude列表中的值
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月21日 上午12:39:53
 */
@Component("defaultIdxcodeGenerator")
public class DefaultIdxcodeGenerator implements IdxcodeGenerator{

	@Autowired
	private LvuserService lvuserService ;

	private final String prefixs [] = {"1","3","5","7","9"};
	private final String suffixs [] = {"1","2","3","4","5","6","7"} ;
	
	/**
	 * 如果失败，最多重试3次
	 */
	private final int reTry = 3 ;

	private final Random random = new Random(); 


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
		int randPrefixIndex = random.nextInt(prefixLength) ;
		String prefix = prefixs[randPrefixIndex]; 
		idxcode.append(prefix) ;
		int yearLimit1 = currentTime.getYear() % 10 ;
		idxcode.append(yearLimit1) ;
		idxcode.append(String.format("%02d", currentTime.getMonth())) ;
		idxcode.append(String.format("%02d", currentTime.getDate()));
		idxcode.append(String.format("%02d", currentTime.getHours()));
		idxcode.append(String.format("%02d", currentTime.getMinutes()));
		idxcode.append(String.format("%02d", currentTime.getSeconds()));
		int suffixLength = suffixs.length ;
		int randSuffixIndex = random.nextInt(suffixLength) ;
		String suffix = suffixs[randSuffixIndex];
		idxcode.append(suffix) ;
		return idxcode.toString() ;
	}

}

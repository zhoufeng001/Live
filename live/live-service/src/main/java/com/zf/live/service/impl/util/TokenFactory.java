package com.zf.live.service.impl.util;

import java.util.UUID;

/**
 * 随机token生成器
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月24日 上午12:14:09
 */
public class TokenFactory {
	
	
	public static String newToken(){
		String uuid = UUID.randomUUID().toString() ;
		String token = uuid.replace("-", "");
		return token;
	}

}

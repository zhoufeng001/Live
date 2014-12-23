package com.zf.live.common.assertx;

import com.zf.live.client.exception.ValidateException;


/**
 * 校验工具类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月23日 下午5:43:59
 */
public abstract class ZFAssert {

	public static void notNull(Object target, String... fieldAndMessages) {
		
	}
	
	
	private class FieldAndMessage{
		String field ;
		String value ;
		String message ;
		public FieldAndMessage(Object target ,String fieldAndMessages){
			if(fieldAndMessages == null || fieldAndMessages.trim().equals("")){
				throw new ValidateException("can");
			}
		}
	}

}

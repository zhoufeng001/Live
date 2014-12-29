package com.zf.live.common.validate.handler;

import java.lang.reflect.Method;

import com.zf.live.client.exception.ValidateException;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午4:14:24
 */
public abstract class InvokeMethodHandler<A> { 
	
	/**
	 * 校验单个注解
	 * @param annoationa
	 * @param arg
	 * @throws ValidateException
	 */
	public abstract void validate(A annoationa , Object arg , Method method) throws ValidateException ;
	
	/**
	 * 校验注解组 
	 * @param annoations
	 * @param arg
	 * @throws ValidateException
	 */
	public void validate(A[] annoations , Object arg,Method method) throws ValidateException {
		if(annoations == null){
			return  ;
		}
		for (A ant : annoations) {
			validate(ant, arg,method); 
		}
	}
}

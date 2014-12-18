package com.zf.live.common.validate.handler;

import java.lang.reflect.Method;

import com.zf.live.client.exception.ValidateException;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午4:14:24
 */
public interface InvokeMethodHandler {

	public void validate(Method method , Object[] args) throws ValidateException ;
	
}

package com.zf.live.common.validate.handler;

import java.lang.reflect.Method;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.util.ZFReflectionUtils;
import com.zf.live.common.validate.Regexp;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月20日 上午1:40:46
 */
public class RegexpInvokeMethodHandler extends InvokeMethodHandler<Regexp> {

	@Override
	public void validate(Regexp annoationa, Object arg ,Method method)
			throws ValidateException {
		String fieldName = annoationa.field() ;
		Object fieldValue = ZFReflectionUtils.getFiledValue(arg, fieldName) ;
		String regexp = annoationa.value() ;
		if(fieldValue == null || regexp == null){
			return ;
		}
		if(!fieldValue.toString().matches(regexp)){
			throw new ValidateException(String.format("[%s]字段%s不符合规则%s", method.getName(),fieldName , regexp));  
		}
	}

}

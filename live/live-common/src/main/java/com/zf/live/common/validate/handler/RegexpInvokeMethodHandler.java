package com.zf.live.common.validate.handler;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.validate.Regexp;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月20日 上午1:40:46
 */
public class RegexpInvokeMethodHandler extends InvokeMethodHandler<Regexp> {

	@Override
	public void validate(Regexp annoationa, Object arg)
			throws ValidateException {
		String fieldName = annoationa.field() ;
		Object fieldValue = getFiledValue(arg, fieldName) ;
		String regexp = annoationa.value() ;
		if(fieldValue == null || regexp == null){
			return ;
		}
		if(!fieldValue.toString().matches(regexp)){
			throw new ValidateException(String.format("字段%s不符合规则%s", fieldName , regexp));  
		}
	}

}

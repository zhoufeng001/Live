package com.zf.live.common.validate.handler;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.validate.Notnull;


/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午4:18:02
 */
public class NotnullInvokeMethodHandler implements InvokeMethodHandler<Notnull> {

	@Override
	public void validate(Notnull[] annoations, Object arg)
			throws ValidateException {
		for (Notnull notnull : annoations) {
			validate(notnull, arg); 
		}
	}
	
	private void validate(Notnull annotations , Object arg) throws ValidateException{
		String fieldName = annotations.value() ;
		Object fieldValue = getFiledValue(arg, fieldName) ;
		if(fieldValue == null){
			throw new ValidateException("字段" + fieldName + "的值不能为空");
		}
	}
	
}

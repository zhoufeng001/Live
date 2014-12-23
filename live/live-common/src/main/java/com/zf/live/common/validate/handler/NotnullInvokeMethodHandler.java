package com.zf.live.common.validate.handler;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.util.ZFReflectionUtils;
import com.zf.live.common.validate.Notnull;


/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午4:18:02
 */
public class NotnullInvokeMethodHandler extends InvokeMethodHandler<Notnull> {

	@Override
	public void validate(Notnull annoationa, Object arg)
			throws ValidateException {
		String fieldName = annoationa.value() ;
		Object fieldValue = ZFReflectionUtils.getFiledValue(arg, fieldName) ;
		if(fieldValue == null){
			throw new ValidateException("字段" + fieldName + "的值不能为空");
		}
	}
	
}

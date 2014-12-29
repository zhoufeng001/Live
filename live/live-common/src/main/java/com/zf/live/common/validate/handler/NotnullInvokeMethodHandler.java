package com.zf.live.common.validate.handler;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.util.ZFReflectionUtils;
import com.zf.live.common.validate.Notnull;


/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午4:18:02
 */
public class NotnullInvokeMethodHandler extends InvokeMethodHandler<Notnull> {

	static final Logger log = LoggerFactory.getLogger(NotnullInvokeMethodHandler.class);

	@Override
	public void validate(Notnull annoationa, Object arg , Method method)
			throws ValidateException {
		String fieldName = annoationa.value() ;
		Object fieldValue = ZFReflectionUtils.getFiledValue(arg, fieldName) ;
		if(fieldValue == null){
			throw new ValidateException(String.format("[%s]字段%s的值不能为空",
					method.getName() ,fieldName));
		}
	}

}

package com.zf.live.common.validate.handler;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.util.ZFReflectionUtils;
import com.zf.live.common.validate.IsInteger;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月19日 下午5:25:42
 */
public class IsIntegerInvokerMethodHandler extends InvokeMethodHandler<IsInteger>{
	
	private static final Logger log = LoggerFactory.getLogger(IsIntegerInvokerMethodHandler.class);


	@Override
	public void validate(IsInteger annoationa, Object arg,Method method)
			throws ValidateException {

		String fieldName = annoationa.value() ;
		Object fieldValue = ZFReflectionUtils.getFiledValue(arg, fieldName) ;

		if(fieldValue == null){
			return ;
		}
		
		Integer fieldValueInt = null ;
		try {
			fieldValueInt = Integer.valueOf(fieldValue.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			throw new ValidateException(String.format("[%s]字段%s的值不是数字类型",method.getName(), fieldName));  
		}
		
		boolean validateValue = annoationa.validateValue() ;
		if(validateValue){
			fieldValueInt = Integer.valueOf(fieldValue.toString()) ;
			Integer minValue = annoationa.minValue() ;
			Integer maxValue = annoationa.maxValue() ;
			if(fieldValueInt < minValue){
				throw new ValidateException(String.format("[%s]字段%s的值不能小于%d" , method.getName() , fieldName , minValue));
			}
			if(fieldValueInt > maxValue ){
				throw new ValidateException(String.format("[%s]字段%s的值不能大于%d" ,method.getName() ,  fieldName , maxValue));
			}
		}

	}

}

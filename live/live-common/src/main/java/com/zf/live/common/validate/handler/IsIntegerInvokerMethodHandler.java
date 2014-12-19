package com.zf.live.common.validate.handler;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.validate.IsInteger;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月19日 下午5:25:42
 */
public class IsIntegerInvokerMethodHandler implements InvokeMethodHandler<IsInteger>{

	@Override
	public void validate(IsInteger annoationa, Object arg)
			throws ValidateException {

		String fieldName = annoationa.value() ;
		Object fieldValue = getFiledValue(arg, fieldName) ;

		if(fieldValue == null){
			return ;
		}
		
		Integer fieldValueInt = null ;
		try {
			fieldValueInt = Integer.valueOf(fieldValue.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ValidateException(String.format("字段%s的值不是数字类型", fieldName , fieldName.getClass().getName()));  
		}
		
		boolean validateValue = annoationa.validateValue() ;
		if(validateValue){
			fieldValueInt = Integer.valueOf(fieldValue.toString()) ;
			Integer minValue = annoationa.minValue() ;
			Integer maxValue = annoationa.maxValue() ;
			if(fieldValueInt < minValue){
				throw new ValidateException(String.format("字段%s的值不能小于%d" , fieldName , minValue));
			}
			if(fieldValueInt > maxValue ){
				throw new ValidateException(String.format("字段%s的值不能大于%d" , fieldName , maxValue));
			}
		}

	}

}

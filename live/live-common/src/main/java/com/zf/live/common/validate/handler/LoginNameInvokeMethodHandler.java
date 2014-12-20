package com.zf.live.common.validate.handler;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.Const;
import com.zf.live.common.validate.LoginName;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月20日 下午11:50:28
 */
public class LoginNameInvokeMethodHandler extends InvokeMethodHandler<LoginName>{
	
	@Override
	public void validate(LoginName annoationa, Object arg)
			throws ValidateException {
		String fieldName = annoationa.value() ;
		Object fieldValue = getFiledValue(arg, fieldName) ;
		if(fieldValue == null){
			return ;
		}
		if(!fieldValue.toString().matches(Const.UserConst.LOGINNAME_REGEXP)){
			throw new ValidateException("登录名不符合规范！");
		}
	}

}

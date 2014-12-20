package com.zf.live.common.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月20日 下午11:48:35
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.PARAMETER)
@Repeatable(LoginNameGroup.class)
public @interface LoginName {
	
	/**
	 * 登录名字段
	 * @return
	 */
	public String value();

}

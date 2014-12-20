package com.zf.live.common.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月20日 下午4:07:35
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.PARAMETER)
@Repeatable(NotnullGroup.class)
public @interface Notnull {

	/**
	 * 要校验的字段
	 * @return
	 */
	public String value() default "" ;
	
}

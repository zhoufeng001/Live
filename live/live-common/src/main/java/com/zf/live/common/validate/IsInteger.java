package com.zf.live.common.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月20日 下午4:07:23
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.PARAMETER)
@Repeatable(IsIntegerGroup.class)
public @interface IsInteger {
	
	/**
	 * 要校验的字段
	 * @return
	 */
	public String value() default "" ;
	
	/**
	 * 是否要校验校验字段的值
	 * @return
	 */
	public boolean validateValue() default false ;
	
	/**
	 * 最大值
	 * @return
	 */
	public int maxValue() default 0 ;
	
	/**
	 * 最小值
	 * @return
	 */
	public int minValue() default 0 ;
	
}

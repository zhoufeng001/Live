package com.zf.live.common.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.PARAMETER)
@Repeatable(IsIntegerGroup.class)
public @interface IsInteger {
	
	public String value() default "" ;
	
	public boolean validateValue() default false ;
	
	public int maxValue() default 0 ;
	
	public int minValue() default 0 ;
	
}

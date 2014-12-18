package com.zf.live.common.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.PARAMETER)
@Repeatable(NotnullGroup.class)
public @interface Notnull {

	public String value() default "" ;
	
}

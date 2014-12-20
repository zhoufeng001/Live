package com.zf.live.common.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月20日 下午4:08:03
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.PARAMETER)
public @interface RegexpGroup {

	public Regexp[] value();
	
}

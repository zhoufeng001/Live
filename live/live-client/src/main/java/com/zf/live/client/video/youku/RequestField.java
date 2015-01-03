package com.zf.live.client.video.youku;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 如果Field加上了该注解，在自动装配请求参数时，才会加上该参数， 否则不加
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:17:01
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.FIELD)
public @interface RequestField {

	/**
	 * json序列化时的key名称，默认使用Field名
	 * @return
	 */
	String value() default "";
	
}

package com.zf.live.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常用反射工具类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月23日 下午5:52:59
 */
public abstract class ZFReflectionUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ZFReflectionUtils.class);
	
	/**
	 * 获取指定对象中的属性，属性可级联，比如  target = "userA"  field = "classes.name"
	 * @param target
	 * @param field
	 * @return
	 */
	public static final Object getFiledValue(Object target , String filedName){
		if(filedName == null || "".equals(filedName.trim())){
			return target ;
		}
		String[] sp = filedName.split("\\.");
		Object finalObj = target ;
		for (String s : sp) {
			if(finalObj == null){
				return null ;
			}
			Class<?> clazz = finalObj.getClass() ;
			try {
				PropertyDescriptor pd = new PropertyDescriptor(s, clazz) ;
				Method readMethod = pd.getReadMethod() ;
				if(readMethod == null){
					return null ;
				}
				readMethod.setAccessible(true);
				finalObj = readMethod.invoke(finalObj) ;
				if(finalObj == null){
					return null ;
				}
			}catch (IllegalAccessException e) {
				log.error(e.getMessage());
				return null ;
			} catch (IntrospectionException e) {
				log.error(e.getMessage());
				return null ;
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
				return null ;
			}
		}
		return finalObj ;
	}
	
	
}

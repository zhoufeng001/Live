package com.zf.live.common.validate.handler;

import java.lang.reflect.Field;

import com.zf.live.client.exception.ValidateException;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午4:14:24
 */
public interface InvokeMethodHandler<A> { 

	public void validate(A[] annoations , Object arg) throws ValidateException ;
	
	/**
	 * 获取指定对象中的属性，属性可级联，比如  target = "userA"  field = "classes.name"
	 * @param target
	 * @param field
	 * @return
	 */
	default Object getFiledValue(Object target , String filedName){
		if(filedName == null || "".equals(filedName.trim())){
			return target ;
		}
		String[] sp = filedName.split("\\.");
		Object finalObj = target ;
		for (String s : sp) {
			Class<?> clazz = finalObj.getClass() ;
			try {
				Field fld = clazz.getField(s);
				fld.setAccessible(true); 
				finalObj = fld.get(finalObj) ;
				if(finalObj == null){
					return null ;
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				throw new ValidateException(clazz.getName() + "类中不存在属性" + s);
			} catch (SecurityException e) {
				e.printStackTrace();
				throw new ValidateException("获取" + clazz.getName() + "中的属性" + s + "失败");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new ValidateException("获取" + clazz.getName() + "中的属性" + s + "失败");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new ValidateException("获取" + clazz.getName() + "中的属性" + s + "失败");
			}
		}
		return finalObj ;
	}
	
}

package com.zf.live.common.validate.handler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.zf.live.client.exception.ValidateException;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午4:14:24
 */
public abstract class InvokeMethodHandler<A> { 
	
	/**
	 * 校验单个注解
	 * @param annoationa
	 * @param arg
	 * @throws ValidateException
	 */
	public abstract void validate(A annoationa , Object arg) throws ValidateException ;
	
	/**
	 * 校验注解组 
	 * @param annoations
	 * @param arg
	 * @throws ValidateException
	 */
	public void validate(A[] annoations , Object arg) throws ValidateException {
		if(annoations == null){
			return  ;
		}
		for (A ant : annoations) {
			validate(ant, arg); 
		}
	}
	
	/**
	 * 获取指定对象中的属性，属性可级联，比如  target = "userA"  field = "classes.name"
	 * @param target
	 * @param field
	 * @return
	 */
	protected Object getFiledValue(Object target , String filedName){
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
					throw new ValidateException(clazz.getName() + "类中不存在属性" + s + ",或该属性的getter方法");
				}
				readMethod.setAccessible(true);
				finalObj = readMethod.invoke(finalObj) ;
				if(finalObj == null){
					return null ;
				}
			}catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new ValidateException("获取" + clazz.getName() + "中的属性" + s + "失败");
			} catch (IntrospectionException e) {
				e.printStackTrace();
				throw new ValidateException("获取" + clazz.getName() + "中的属性" + s + "失败");
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw new ValidateException("获取" + clazz.getName() + "中的属性" + s + "失败");
			}
		}
		return finalObj ;
	}
	
}

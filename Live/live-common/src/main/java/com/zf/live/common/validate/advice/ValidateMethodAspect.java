package com.zf.live.common.validate.advice;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;

import com.zf.live.common.validate.IsInteger;
import com.zf.live.common.validate.Notnull;
import com.zf.live.common.validate.Regexp;


/**
 * 代理使用了校验注解方法
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午5:17:07
 */
public class ValidateMethodAspect {

	
	public void validate(ProceedingJoinPoint joinPoint) 
			throws Throwable {
		String methodName = joinPoint.getSignature().getName() ;
		Object[] args = joinPoint.getArgs() ;
		Class<?>[] argsType = getArgsType(args);
		Object target = joinPoint.getTarget() ;
		Method method = null ;
		if(argsType == null){
			method = joinPoint.getTarget().getClass().getMethod(methodName) ;
		}else{
			method = joinPoint.getTarget().getClass().getMethod(methodName , argsType) ;
		}

		Parameter[] parameters = method.getParameters() ;
		if(parameters != null){
			for (Parameter parameter : parameters) {  
				Notnull[] notnulls = parameter.getAnnotationsByType(Notnull.class) ;
				notnulls[0].value();
				IsInteger[] integers = parameter.getAnnotationsByType(IsInteger.class) ;
				Regexp[] regexps = parameter.getAnnotationsByType(Regexp.class) ;
			}
		}
		
		System.out.println("ValidateMethodAdvice..." + method.getName() + "   " + target.toString());
	}

	/**
	 * 获取参数类型
	 * @param args
	 * @return
	 */
	public Class<?>[] getArgsType(Object[] args){
		if(args == null || args.length <= 0){
			return null ; 
		}
		Class<?>[] clazz = new Class[args.length] ;
		for (int i = 0; i < args.length; i++) {
			clazz[i] = args[i].getClass() ;
		}
		return clazz ;
	}

}

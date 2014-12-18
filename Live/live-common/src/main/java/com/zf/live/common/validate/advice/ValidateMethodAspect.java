package com.zf.live.common.validate.advice;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.validate.IsInteger;
import com.zf.live.common.validate.Notnull;
import com.zf.live.common.validate.Regexp;
import com.zf.live.common.validate.handler.NotnullInvokeMethodHandler;


/**
 * 代理使用了校验注解方法
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午5:17:07
 */
public class ValidateMethodAspect {
	
	private NotnullInvokeMethodHandler notnullInvokeMethodHandler ;
	
	public Object validate(ProceedingJoinPoint joinPoint) 
			throws Throwable {
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Object[] args = joinPoint.getArgs() ;
		Object target = joinPoint.getTarget() ;
		Parameter[] parameters = method.getParameters() ;
		
		
		if(args != null && parameters != null && args.length != parameters.length){
			throw new ValidateException("method[" + method.getName() + "]方法定义的参数数量与传入的数量不一致，定义：" + args.length + "，传入：" + parameters.length);
		}
		if(parameters != null && args != null){
			for (int i = 0; i < parameters.length; i++) {
				Parameter parameter = parameters[i];
				Notnull[] notnulls = parameter.getAnnotationsByType(Notnull.class) ;
				IsInteger[] integers = parameter.getAnnotationsByType(IsInteger.class) ;
				Regexp[] regexps = parameter.getAnnotationsByType(Regexp.class) ;
				Object arg = args[i];
				
				if(notnullInvokeMethodHandler == null){
					throw new ValidateException("notnullInvokeMethodHandler不能为空");
				}
				
				notnullInvokeMethodHandler.validate(notnulls, arg); ;
			}
		}
		
		System.out.println("ValidateMethodAdvice..." + method.getName() + "   " + target.toString());
		
		return joinPoint.proceed(args) ;
	}

	public NotnullInvokeMethodHandler getNotnullInvokeMethodHandler() {
		return notnullInvokeMethodHandler;
	}

	public void setNotnullInvokeMethodHandler(
			NotnullInvokeMethodHandler notnullInvokeMethodHandler) {
		this.notnullInvokeMethodHandler = notnullInvokeMethodHandler;
	}

}

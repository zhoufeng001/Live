package com.zf.live.common.validate.advice;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.validate.handler.InvokeMethodHandler;


/**
 * 代理使用了校验注解方法
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午5:17:07
 */
public class ValidateMethodAspect {

	private Map<String, String> handlers = new HashMap<String, String>() ;
	private List<AnnotationHandlerPair> annotaionHandlers = new LinkedList<AnnotationHandlerPair>() ; 

	public void init(){
		if(handlers == null || handlers.size() <= 0){
			return ;
		}
		Iterator<Entry<String, String>> handlersSet = handlers.entrySet().iterator(); 
		while(handlersSet.hasNext()){
			Entry<String, String> handlerEntry = handlersSet.next() ;
			String annotationClassName = handlerEntry.getKey() ;
			String handlerClassName = handlerEntry.getValue() ;
			Class<?> annotationClass = null ;
			Class<?> handlerClass = null ;
			InvokeMethodHandler<?> handler = null ;
			try {
				annotationClass = Class.forName(annotationClassName) ;
				if(!annotationClass.isAnnotation()){
					throw new ValidateException(annotationClassName + "不是注解类型");
				}
				handlerClass = Class.forName(handlerClassName) ;
				Object handlerObj = handlerClass.newInstance();
				if(handlerObj == null){
					throw new ValidateException("实例化handler " +  handlerClassName + "失败");
				}
				if(!(handlerObj instanceof InvokeMethodHandler)){
					throw new ValidateException(handlerClassName + "必须实现" + InvokeMethodHandler.class.getName() + "接口" );
				}
				handler = (InvokeMethodHandler<?>)handlerObj ;
				annotaionHandlers.add(new AnnotationHandlerPair(annotationClass, handler)) ;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new ValidateException(e.getMessage());
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new ValidateException(e.getMessage());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new ValidateException(e.getMessage());
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object validate(ProceedingJoinPoint joinPoint) 
			throws Throwable {
		String methodName = joinPoint.getSignature().getName() ;
		Object[] args = joinPoint.getArgs() ;
		Method method = joinPoint.getTarget().getClass().getMethod(methodName, ((MethodSignature) joinPoint.getSignature()).getParameterTypes() ) ;

		Parameter[] parameters = method.getParameters() ;
		if(args != null && parameters != null && args.length != parameters.length){
			throw new ValidateException("method[" + method.getName() + "]方法定义的参数数量与传入的数量不一致，定义：" + args.length + "，传入：" + parameters.length);
		}
		if(parameters != null && args != null){
			for (int i = 0; i < parameters.length; i++) {
				Parameter parameter = parameters[i];
				Object arg = args[i];
				int paramAnnotationLength = parameter.getAnnotations().length ;
				if(paramAnnotationLength <= 0){
					continue ;
				}
				for (AnnotationHandlerPair annotation : annotaionHandlers) {
					Class annocationClass = annotation.annotation ;
					InvokeMethodHandler handler = annotation.handler ;
					Object[] annocations = parameter.getAnnotationsByType(annocationClass) ;
					if(annocations != null && annocations.length > 0){
						handler.validate(annocations, arg,method); 
					}
				}
			}
		}
		return joinPoint.proceed(args) ;
	}

	public Map<String, String> getHandlers() {
		return handlers;
	}

	public void setHandlers(Map<String, String> handlers) {
		this.handlers = handlers;
	}


	private class AnnotationHandlerPair {
		Class<?> annotation ;
		InvokeMethodHandler<?> handler ;

		public AnnotationHandlerPair(Class<?> annotation,
				InvokeMethodHandler<?> handler) {
			this.annotation = annotation;
			this.handler = handler;
		}

	}

}

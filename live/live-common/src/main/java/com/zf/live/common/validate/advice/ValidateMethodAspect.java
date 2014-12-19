package com.zf.live.common.validate.advice;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.validate.IsInteger;
import com.zf.live.common.validate.Notnull;
import com.zf.live.common.validate.Regexp;
import com.zf.live.common.validate.handler.InvokeMethodHandler;
import com.zf.live.common.validate.handler.IsIntegerInvokerMethodHandler;
import com.zf.live.common.validate.handler.NotnullInvokeMethodHandler;


/**
 * 代理使用了校验注解方法
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午5:17:07
 */
public class ValidateMethodAspect {

	private Map<String, String> handlers = new HashMap<String, String>() ;
	private Map<Class<?>, InvokeMethodHandler<?> > handlersMap = new HashMap<Class<?>, InvokeMethodHandler<?> >() ; 

	private NotnullInvokeMethodHandler notnullInvokeMethodHandler ;
	private IsIntegerInvokerMethodHandler isIntegerInvokerMethodHandler ;

	public ValidateMethodAspect(){
		if(handlers != null && handlers.size() > 0){
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
					handlerClass = Class.forName(handlerClassName) ;
					Object handlerObj = handlerClass.newInstance();
					if(handlerObj == null){
						throw new ValidateException("实例化handler " +  handlerClassName + "失败");
					}
					if(!(handlerObj instanceof InvokeMethodHandler)){
						throw new ValidateException(handlerClassName + "必须实现" + InvokeMethodHandler.class.getName() + "接口" );
					}
					handler = (InvokeMethodHandler<?>)handlerObj ;
					
					handlersMap.put(annotationClass, handler) ;
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
		notnullInvokeMethodHandler = new NotnullInvokeMethodHandler() ;
		isIntegerInvokerMethodHandler = new IsIntegerInvokerMethodHandler() ;
	}

	public Object validate(ProceedingJoinPoint joinPoint) 
			throws Throwable {

		String methodName = joinPoint.getSignature().getName() ;
		Object[] args = joinPoint.getArgs() ;
		Object target = joinPoint.getTarget() ;
		Method method = joinPoint.getTarget().getClass().getMethod(methodName, ((MethodSignature) joinPoint.getSignature()).getParameterTypes() ) ;

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

				notnullInvokeMethodHandler.validate(notnulls, arg); 
				isIntegerInvokerMethodHandler.validate(integers, arg); 
			}
		}

		System.out.println("ValidateMethodAdvice..." + method.getName() + "   " + target.toString());

		return joinPoint.proceed(args) ;
	}

	public Map<String, String> getHandlers() {
		return handlers;
	}

	public void setHandlers(Map<String, String> handlers) {
		this.handlers = handlers;
	}

}

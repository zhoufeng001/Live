package com.zf.live.service.impl.video.youku;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zf.live.client.video.youku.RequestField;

/**
 * 优酷请求工具类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:59:58
 */
public abstract class YoukuRequestUtil {

	private static final Logger log = LoggerFactory.getLogger(YoukuRequestUtil.class);
	
	
	/**
	 * 生成请求URL
	 * @param apiUrl
	 * @param params
	 * @return
	 */
	public static String buildRequestUrl(String apiUrl,Object request){
		if(request == null){
			return apiUrl ;
		}
		StringBuilder url = new StringBuilder(apiUrl).append("?") ;
		Class<?> requestClass =  request.getClass();
		Field[] fields = requestClass.getDeclaredFields() ;
		if(fields == null || fields.length <= 0){
			return apiUrl ;
		}
		Class<?> supperClass = requestClass.getSuperclass();
		if(supperClass != null){
			Field[] supperFields = supperClass.getDeclaredFields() ;
			if(supperFields != null && supperFields.length > 0	){
				fields = ArrayUtils.addAll(fields, supperFields) ;
			}
		}
		RequestField requestField = null ;
		String requestFieldName = null ;
		Object fieldValue = null ;
		for (Field field : fields) {
			requestField = field.getAnnotation(RequestField.class); 
			if(requestField == null){
				continue ;
			}
			field.setAccessible(true);
			try {
				fieldValue = field.get(request);
			} catch (Exception e) {
				log.error(e.getMessage()); 
				continue ;
			} 
			if(fieldValue == null){
				continue ;
			}
			requestFieldName = requestField.value();
			if(StringUtils.isBlank(requestFieldName)){
				requestFieldName = field.getName() ;
			}
			url.append(requestFieldName).append("=").append(fieldValue).append("&") ;
		}
		return url.toString() ;
	}

}

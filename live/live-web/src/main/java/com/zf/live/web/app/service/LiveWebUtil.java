package com.zf.live.web.app.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月26日 下午1:55:45
 */
public abstract class LiveWebUtil {

	public static final String REDIRECT_PREFIX = "redirect:";
	public static final String SYSTEM_REDIRECT_FLAG_KEY = "_sys_rid_";
	public static final String SYSTEM_REDIRECT_FLAG_VALUE = "1";

	public static String redirectIndexPath(){
		return buildPathWithParams("/index.htm" , null);
	}
	
	public static String redirectIndexPath(Map<String, String> params){
		return buildPathWithParams("/index.htm" , params);
	}

	public static String redirectLoginPath(){
		return buildPathWithParams("/user/loginView.htm" ,null);
	}
	
	public static String redirectLoginPath(Map<String, String> params){
		return buildPathWithParams("/user/loginView.htm" ,params);
	}

	public static String redirectPath(String path){
		return buildPathWithParams(path ,null);
	}
	
	public static String redirectPath(String path , Map<String, String> params){
		return buildPathWithParams(path ,params);
	}
	
	/**
	 * 根据basePath和查询参数拼接redirect路径
	 * @param path 相对路径，例如 /user/loginView.htm 
	 * @param params
	 * @return
	 */
	private static String buildPathWithParams(String basePath , Map<String , String> params){
		if(StringUtils.isBlank(basePath)){
			return basePath; 
		}
		basePath = REDIRECT_PREFIX + basePath ;
		StringBuilder db = new StringBuilder(basePath);
		db.append("?");
		db.append(SYSTEM_REDIRECT_FLAG_KEY).append("=").append(SYSTEM_REDIRECT_FLAG_VALUE);
		if(params == null || params.size() == 0){
			return db.toString() ;
		}
		Iterator<Entry<String, String>> paramsIt = params.entrySet().iterator() ;
		Entry<String , String> paramEntry ;
		while(paramsIt.hasNext()){
			paramEntry = paramsIt.next();
			if(StringUtils.isAnyBlank(paramEntry.getKey() , paramEntry.getValue())){
				continue ;
			}
			db.append("&").append(paramEntry.getKey()).append("=").append(paramEntry.getValue()); 
		}
		return db.toString() ;
	}

}

package com.zf.live.common.util;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http请求工具
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午2:34:44
 */
public class HttpClientUtils {

	private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

	
	/**
	 * 发送Get请求，并返回String类型的结果集
	 * @param url
	 * @return
	 */
	public static String sendGetRequest(String url){
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			String stringResult = entityToString(response.getEntity());
			return stringResult; 
		} catch (Exception e) {
			log.error(e.getMessage());
			return null ;
		}finally{
			if(httpGet != null){
				httpGet.releaseConnection(); 
			}
		}
	}
	
	/** 
     * 获取输入流
     * @param url 
     * @param params 
     * @return   
     * @throws Exception 
     */  
    public static InputStream getInputStream(String url){  
    	HttpClient httpClient = new DefaultHttpClient();  
    	HttpGet httpGet = new HttpGet(url);  
        try {
			HttpResponse response = httpClient.execute(httpGet);  
			if(response != null){
				return response.getEntity().getContent();
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}finally{
			if(httpGet != null){
				httpGet.releaseConnection(); 
			}
		}
        return null ;
    }  
	
	/**
	 * 将HttpEntity对象转换为字符串
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private static String entityToString(HttpEntity entity) throws Exception{
		return entity == null ? 
				null:EntityUtils.toString(entity , "utf-8"); 
	}


}       

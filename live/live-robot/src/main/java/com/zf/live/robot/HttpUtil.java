package com.zf.live.robot;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月12日 下午11:20:57
 */
public class HttpUtil {
	
	static final Logger log = org.slf4j.LoggerFactory.getLogger(HttpUtil.class);

	
	private static final String loginUrl = Config.getInstance().getProperty("test.login.url");
	private static final String registUrl = Config.getInstance().getProperty("test.regist.url");
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public static String doLogin(String username , String password){
		HttpClient hc = new HttpClient() ;
		PostMethod postMethod = new PostMethod(loginUrl);
		postMethod.addParameter("userkey", username); 
		postMethod.addParameter("secret", password); 
		try {
			hc.executeMethod(postMethod) ;
			String responseString = postMethod.getResponseBodyAsString() ;
			JSONObject jsonResponse = (JSONObject) JSON.parse(responseString);
			int success = jsonResponse.getInteger("success");
			if(success == 1){
				String token = jsonResponse.getString("data");
				log.info("用户[{}]登录成功，token[{}]" , username , token);  
				return token ;
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e); 
		}
		return null ;
	}
	
	public static void doRegist(String username , String password , String nick){
		HttpClient hc = new HttpClient() ;
		PostMethod postMethod = new PostMethod(registUrl);
		HttpMethodParams params = new HttpMethodParams() ;
		postMethod.addParameter("userkey", username); 
		postMethod.addParameter("secret", password); 
		postMethod.addParameter("nick", nick); 
		postMethod.setParams(params);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		try {
			hc.executeMethod(postMethod) ;
			log.info("用户[{}]注册成功！",username);  
		} catch (Exception e) {
			log.error(e.getMessage(), e); 
		}
		
	}

}

package com.zf.live.web.app.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.zf.live.client.vo.web.AjaxResult;
import com.zf.live.client.vo.web.AjaxResultType;
import com.zf.live.common.util.CharUtil;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月25日 下午5:06:04
 */
public class WebUtil {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(WebUtil.class);

	/**
	 * 输出Json内容到客户端，并关闭连接
	 * @param result
	 * @param response
	 */
	public static void ajaxOutput(AjaxResult result , ServletResponse response){
		PrintWriter out = null ;
		try {
			if(result == null){
				result = new AjaxResult() ;
				result.setSuccess(AjaxResultType.FAIL.getValue());
			}
			String jsonResult = JSON.toJSONString(result) ;
			out = response.getWriter();
			out.write(jsonResult);
		} catch (IOException e) {  
			log.error(e.getMessage() , e);
		}finally{
			if(out != null){
				out.close();
			}
		}
	}

	/**
	 * 将URL中的中文字符Encode
	 * @param url
	 */
	public static String urlEncode(String url){
		if(StringUtils.isBlank(url)){
			return url ;
		}
		StringBuilder sb = new StringBuilder() ;
		for (int i = 0; i < url.length() ; i++) {
			char c = url.charAt(i);
			if(CharUtil.isChinese(c)){
				try {
					sb.append(URLEncoder.encode(String.valueOf(c),"utf-8")) ;
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage() ,e); 
				} 
			}else{
				sb.append(c); 
			}
		}
		return sb.toString() ;
	}

}

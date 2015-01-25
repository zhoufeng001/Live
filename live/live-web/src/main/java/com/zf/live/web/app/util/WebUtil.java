package com.zf.live.web.app.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.zf.live.client.vo.web.AjaxResult;
import com.zf.live.client.vo.web.AjaxResultType;

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


}

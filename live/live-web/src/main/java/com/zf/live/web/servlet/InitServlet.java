package com.zf.live.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zf.live.client.exception.LiveException;
import com.zf.live.common.ZFSpringPropertyConfigure;
import com.zf.live.web.WebConst.Config;

public class InitServlet extends HttpServlet{
	
	static final Logger log = LoggerFactory.getLogger(InitServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 7250795734588042858L;

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ZFSpringPropertyConfigure propertyConfigure = applicationContext.getBean(ZFSpringPropertyConfigure.class);
		if(propertyConfigure == null){
			log.error("初始化ContextPath、StaticServer失败！，没找到" + ZFSpringPropertyConfigure.class.getName() + "对象");
		}
		String appContextPath = propertyConfigure.getProperties("app.context.path");
		String appStaticserverPath = propertyConfigure.getProperties("app.staticserver.path");
		String fileServerPath = propertyConfigure.getProperties("app.fileserver.path");
		if(StringUtils.isBlank(appContextPath)){ 
			log.error("没找到app.context.path属性");
		}
		if(StringUtils.isBlank(appStaticserverPath)){
			log.error("没找到app.staticserver.path属性");
		}
		if(StringUtils.isBlank(fileServerPath)){
			log.error("没找到app.fileserver.path属性");
		}
		Config.ctx = appContextPath ;
		Config.staticServer = appStaticserverPath ;
		Config.fileServer = fileServerPath ; 
		getServletContext().setAttribute("ctx", appContextPath); 
		getServletContext().setAttribute("static_server", appStaticserverPath); 
		getServletContext().setAttribute("file_server", fileServerPath);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		throw new LiveException("不能访问该地址！");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		throw new LiveException("不能访问该地址！");
	}
	
}

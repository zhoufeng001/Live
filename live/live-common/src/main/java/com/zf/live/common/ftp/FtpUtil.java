package com.zf.live.common.ftp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月2日 下午11:52:34
 */
public class FtpUtil {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(FtpUtil.class);
	
	/** 本地字符编码 */
	private static String LOCAL_CHARSET = "GBK";
	 
	// FTP协议里面，规定文件名编码为iso-8859-1
	private static String SERVER_CHARSET = "ISO-8859-1";
	
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input){
		boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
	        // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
	        if (FTPReply.isPositiveCompletion(ftp.sendCommand(
	        		"OPTS UTF8", "ON"))) {
	        	LOCAL_CHARSET = "UTF-8";
	        }
	        ftp.setControlEncoding(LOCAL_CHARSET);
	        ftp.enterLocalPassiveMode(); // 设置被动模式
	        ftp.changeWorkingDirectory(path);  
	        filename = new String(filename.getBytes(LOCAL_CHARSET),SERVER_CHARSET); 
	        ftp.storeFile(filename, input);           
	        input.close();  
	        ftp.logout();  
	        success = true;  
	    } catch (IOException e) {  
	    	log.error(e.getMessage() , e); 
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException e) {  
	            	log.error(e.getMessage() , e); 
	            }  
	        }  
	    }  
	    return success;  
	}
	
}

package com.zf.live.robot;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月13日 下午2:37:55
 */
public class RegistTest {
	
	static final Logger log = org.slf4j.LoggerFactory.getLogger(RegistTest.class);

	@Test
	public void testRegist() throws Exception{
		String userPrefix = "zzzz";
		int userFrom = 1 ;
		int userTo = 1000 ;
		String password = "111111";
		String[] nicks = loadNicks() ;
		int nickLength = nicks.length;
		int nickIndex = 0 ;
		for (int i = userFrom; i <= userTo; i++) {
			String username = userPrefix + i ;
			HttpUtil.doRegist(username, password, nicks[nickIndex++]); 
			if(nickIndex == nickLength){
				nickIndex = 0 ;
			}
		}
	}
	
	private String[] loadNicks() throws Exception{
		File nickFile = new File(RegistTest.class.getResource("/nicks.txt").getFile());
		String files = FileUtils.readFileToString(nickFile, "utf-8");
		return files.split("\r\n") ;
	}
	
	@Test
	public void formatNicks()throws Exception{
		File file = new File("C:/Users/zf/Desktop/网游昵称大全.txt");
		File toFile = new File("C:/Users/zf/Desktop/nicks.txt");
		String text = FileUtils.readFileToString(file,"gbk") ;
		text = new String(text.getBytes(),"utf-8");  
		String[] nicks = text.split("\n| ");
		String dh = "\r\n";
		StringBuilder sb = new StringBuilder() ;
		for (String nick : nicks) { 
			if(StringUtils.isNotBlank(nick.trim())){
				sb.append(nick).append(dh) ;
			}
		}
		sb.deleteCharAt(sb.length() - 1) ; 
		FileUtils.writeStringToFile(toFile, sb.toString(),"utf-8"); 
	}
}

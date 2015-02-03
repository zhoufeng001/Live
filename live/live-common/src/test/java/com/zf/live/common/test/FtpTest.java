package com.zf.live.common.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

public class FtpTest {
	
	@Test
	public void testUpload() throws FileNotFoundException{
		 
		FileInputStream fis = new FileInputStream(new File("C:/Users/zf/Desktop/性能.txt")) ;
		
//		FtpUtil.uploadFile("114.215.83.209", 21, 
//				"ftpuser", "is_zhoufeng", "/", "性能.txt", fis) ;
		
	}

}

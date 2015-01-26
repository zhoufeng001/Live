package com.zf.live.common.util;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月27日 上午12:40:48
 */
public class UUID {

	public static String newUUID(){
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(newUUID());
	}
	
}

package com.zf.live.common.util;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月1日 下午11:27:01
 */
public class CharUtil {

	 
	/**
	 * 判断字符是否为中文字符
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
	    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
	            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
	            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
	            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	        return true;
	    }
	    return false;
	}
	
}

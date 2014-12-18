package com.zf.live.common;

/**
 * 常量配置
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午3:18:55
 */
public interface Const {

	/**
	 * 用户常量
	 * @author is_zhoufeng@163.com , QQ:243970446
	 * 2014年12月18日 下午3:19:36
	 */
	public static interface UserConst {
		
		/**
		 * 登录名规则（正则）
		 */
		String LOGINNAME_REGEXP = "[a-zA-Z]{1}[a-zA-Z0-9]{4,12}";
		
		/**
		 * 昵称规则（正则）
		 */
		String NICK_REGEXP = ".{1,12}";
		
		/**
		 * 密码规则（正则）
		 */
		String password_regexp = ".{6,12}" ;
		
	}
	
}

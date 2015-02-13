package com.zf.live.common;

/**
 * 常量配置
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午3:18:55
 */
public interface Const {
	
	/**
	 * 有效的
	 */
	 static Byte USEFUL = 1 ;
	 
	 /**
	  * 无效的
	  */
	 static Byte UN_USEFAL = 0;
	 

	/**
	 * 用户常量
	 * @author is_zhoufeng@163.com , QQ:243970446
	 * 2014年12月18日 下午3:19:36
	 */
	public static interface UserConst {
		
		/**
		 * 登录名规则（正则）
		 */
		String LOGINNAME_REGEXP = "[a-zA-Z]{1}[a-zA-Z0-9_]{4,12}";
		
		/**
		 * 昵称规则（正则）
		 */
		String NICK_REGEXP = ".{1,12}";
		
		/**
		 * 密码规则（正则）
		 */
		String password_regexp = ".{6,12}" ;
		
		/**
		 * 本平台用户
		 */
		Byte USER_FROM_PLATEFORM = 1 ;
		
		/**
		 * QQ注册用户
		 */
		Byte USER_FROM_QQ = 2 ;
		
		
		/**
		 * SINA注册用户
		 */
		Byte USER_FROM_SINA = 3 ;
		
		
		/**
		 * 用户状态【启用】
		 */
		Byte USER_STATUS_ENABLE = 1 ;
		
		/**
		 * 用户状态【禁用】
		 */
		Byte USER_STATUS_DISABLE = 2;
		
		
		/**
		 * 是第三方用户
		 */
		Byte THIRD_TRUE = 1;
		
		/**
		 * 不是第三方用户
		 */
		Byte THIRD_FALSE = 0;
		
		/**
		 * 用户标位
		 * @author is_zhoufeng@163.com , QQ:243970446
		 * 2015年1月1日 上午3:42:48
		 */
		public static interface UserFlag{
			
			/**
			 * 未设置用户名和密码，用于判断第三方登录用户是否设置过平台帐号的用户名和密码
			 */
			Integer notSetLoginnameAndPasswordFlag = 1 ;
			
		}
		
	}
	
}

package com.zf.live.client.vo.web;

/**
 * Ajax返回内容类型
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月25日 下午5:02:05
 */
public enum AjaxResultType {
	/**
	 * 成功
	 */
	SUCCESS(1),
	/**
	 * 失败
	 */
	FAIL(0),
	/**
	 * 错误
	 */
	ERROR(-1)
	;
	
	private int value ;
	
	private AjaxResultType(int value) {
		this.value = value ;
	}

	public int getValue() {
		return value;
	}
	
}

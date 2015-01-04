package com.zf.live.client.vo;

/**
 * 排序规则
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月5日 上午12:50:43
 */
public enum Sort {

	ASC("asc") ,
	DESC("desc")
	;

	private String value ;

	Sort(String value){
		this.value = value ;
	}


	public String getValue() {
		return value;
	}

}

package com.zf.live.client.vo.video;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月5日 下午10:21:24
 */
public enum VideoSite {
	
	YOUKU((byte)1)
	,
	IQIYI((byte)2)
	;
	private VideoSite(Byte value) {
		this.value = value ;
	}
	
	private Byte value;

	public Byte getValue() {
		return value;
	}
	
}

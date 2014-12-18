package com.zf.live.client.vo;

import java.io.Serializable;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午3:08:17
 */
public class LiveResult<T extends Serializable> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4832049780908868635L;

	private T data ;

	private boolean success ;

	private String errMssage ;
	
	public LiveResult() {
		this.success = false ;
	}
	
	public LiveResult(T data, boolean success, String errMssage) {
		this.data = data;
		this.success = success;
		this.errMssage = errMssage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrMssage() {
		return errMssage;
	}

	public void setErrMssage(String errMssage) {
		this.errMssage = errMssage;
	}
	
}

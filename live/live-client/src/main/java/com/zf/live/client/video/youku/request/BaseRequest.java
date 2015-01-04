package com.zf.live.client.video.youku.request;

import java.io.Serializable;

import com.zf.live.client.video.youku.RequestField;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:09:02
 */
public class BaseRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4385906876617621656L;

	@RequestField("client_id")
	protected String clientId ;
	
	@RequestField("page")
	protected Integer page ;
	
	@RequestField("count")
	protected Integer count ;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}

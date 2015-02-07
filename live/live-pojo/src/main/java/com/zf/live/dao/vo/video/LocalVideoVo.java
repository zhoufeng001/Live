package com.zf.live.dao.vo.video;

import com.zf.live.dao.pojo.LocalVideo;

/**
 * 本地视频扩展
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月7日 下午2:10:27
 */
public class LocalVideoVo extends LocalVideo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1147916622940117715L;
	
	private String table ;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
}

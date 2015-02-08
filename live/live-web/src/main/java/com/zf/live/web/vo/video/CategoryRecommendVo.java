package com.zf.live.web.vo.video;

import java.io.Serializable;
import java.util.List;

import com.zf.live.client.vo.video.local.LocalVideoDetailVo;
import com.zf.live.dao.vo.video.LocalVideoVo;

/**
 * 分类推荐视频
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月14日 下午11:51:39
 */
public class CategoryRecommendVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6169870878436726759L;

	private LocalVideoDetailVo topVideoDetailVo ;
	
	private List<LocalVideoVo> recommendVideoList ;

	public LocalVideoDetailVo getTopVideoDetailVo() {
		return topVideoDetailVo;
	}

	public void setTopVideoDetailVo(LocalVideoDetailVo topVideoDetailVo) {
		this.topVideoDetailVo = topVideoDetailVo;
	}

	public List<LocalVideoVo> getRecommendVideoList() {
		return recommendVideoList;
	}

	public void setRecommendVideoList(List<LocalVideoVo> recommendVideoList) {
		this.recommendVideoList = recommendVideoList;
	}

}

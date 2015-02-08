package com.zf.live.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.video.local.LocalVideoServiceV2;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.local.LocalVideoDetailVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchConditionV2;
import com.zf.live.dao.pojo.LocalVideo;
import com.zf.live.dao.vo.video.LocalVideoVo;

public class LocalVideoV2Test extends ServiceBaseTester{

	@Autowired
	private LocalVideoServiceV2 localVideoServiceV2 ;

	@Test
	public void testSave(){
		LocalVideo video = new LocalVideo() ;
		video.setVideoname("实拍河北蒙面歹徒持铁锤抢银行 柜台玻璃被砸破");
		video.setCategory("拍客");
		video.setFromid("XODYzMzQwNTI4");
		video.setThumbnail("http://r1.ykimg.com/0100641F4654A7725A7D63093818C444003A86-C7D3-DE52-1A7C-5C3E73826DAE");
		video.setVideofrom((byte)1);
		ServiceResult<String> result = localVideoServiceV2.saveVideo(video);
		if(result.isSuccess()){
			System.out.println("保存成功");
		}else{
			System.out.println("保存失败"); 
		}
	}
	
	@Test
	public void testSearchById(){
		LocalVideo localVideo = localVideoServiceV2.selectVideoById("00682eecacff11e4ba363417ebbccb7e001");
		System.out.println(localVideo);
	}

	@Test
	public void testUpdate(){
		LocalVideo video = new LocalVideoVo() ;
		video.setId("46e87b53ad0411e4ba363417ebbccb7e025");
		video.setThirdPraise(31L);
		boolean updateResult = localVideoServiceV2.updateVideoBySelective(video) ;
		System.out.println(updateResult); 
	}

	@Test
	public void testSearchWithDetail(){
		ServiceResult<LocalVideoDetailVo> video = localVideoServiceV2.selectVideoWithDetailInfo("3e99523bacfe11e4ba363417ebbccb7e011", true) ;
		System.out.println(video);
	}
	
	@Test
	public void testSearchPage(){
		LocalVideoSearchConditionV2 condition = new LocalVideoSearchConditionV2() ;
		condition.setCategory("其他");
		condition.setPage(5);
		condition.setPageSize(100);
		condition.setOrderBy(" audience_count desc "); 
//		condition.setOrderBy(" view_count desc, third_view_count desc "); 
		PagedVo<LocalVideoVo> videoPage = localVideoServiceV2.searchVideos(condition, true) ;
		System.out.println(videoPage); 
	}
	
}

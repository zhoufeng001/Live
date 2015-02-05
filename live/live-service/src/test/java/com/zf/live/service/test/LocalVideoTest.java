package com.zf.live.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchCondition;
import com.zf.live.client.vo.video.local.VideoDetailVo;
import com.zf.live.common.util.TimerUtil;
import com.zf.live.dao.pojo.Video;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

public class LocalVideoTest extends ServiceBaseTester{

	@Autowired
	private LocalVideoService localVideoService ;

	@Test
	public void testSave(){
		Video video = new Video() ;
		video.setVideoname("实拍河北蒙面歹徒持铁锤抢银行 柜台玻璃被砸破");
		video.setCategory("拍客");
		video.setFromid("XODYzMzQwNTI4");
		video.setThumbnail("http://r1.ykimg.com/0100641F4654A7725A7D63093818C444003A86-C7D3-DE52-1A7C-5C3E73826DAE");
		video.setVideofrom((byte)1);
		ServiceResult<String> result = localVideoService.saveVideo(video);
		if(result.isSuccess()){
			System.out.println("保存成功");
		}else{
			System.out.println("保存失败"); 
		}
	}

	@Test
	public void testSaveDetail(){
		VideoDetailWithBLOBs videoDetail = new VideoDetailWithBLOBs() ;
		videoDetail.setId("");
		videoDetail.setBigthumbnail("http://g1.ykimg.com/1100641F4654A7725A7D63093818C444003A86-C7D3-DE52-1A7C-5C3E73826DAE");
		videoDetail.setDescription("实拍河北蒙面歹徒持铁锤抢银行 柜台玻璃被砸破");
		videoDetail.setDuration("95.43");
		videoDetail.setLink("http://v.youku.com/v_show/id_XODYzMzQwNTI4.html");
		ServiceResult<String> result = localVideoService.saveVideoDetail(videoDetail);
		if(result.isSuccess()){
			System.out.println("保存成功");
		}else{
			System.out.println("保存失败"); 
		}
	}

	@Test
	public void testSearchById(){
		ServiceResult<VideoDetailVo> result = localVideoService.selectVideoWithDetailInfo("",true);
		if(result.isSuccess()){
			System.out.println(result.getData());
		}else{
			System.out.println("获取失败！");
		}
	}

	@Test
	public void testSearch(){
		LocalVideoSearchCondition condition = new LocalVideoSearchCondition() ;
		List<String> category = new ArrayList<String>();
		category.add("拍客");
		condition.setCategory(category);
		condition.setKeyword(null);
		condition.setPage(1);
		condition.setPageSize(20);
		TimerUtil.start("");
		PagedVo<Video> result = localVideoService.searchVideos(condition);
		Long time  = TimerUtil.timing();
		System.out.println("time : " + time );
		System.out.println(result.getData().size());
	}

}

package com.zf.live.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zf.live.client.video.local.LocalVideoServiceV2;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.dao.pojo.LocalVideo;

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


}

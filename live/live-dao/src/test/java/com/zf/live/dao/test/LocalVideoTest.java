package com.zf.live.dao.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zf.live.client.vo.video.local.LocalVideoCategorySearchCondition;
import com.zf.live.dao.mapper.VideoMapperExt;
import com.zf.live.dao.pojo.Video;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月14日 上午3:27:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"classpath*:test-dao-beans.xml"}		
)
public class LocalVideoTest {
	
	@Autowired
	VideoMapperExt videoMapperExt ;
	
	@Test
	public void testSearchByCategory(){
		LocalVideoCategorySearchCondition condition = new LocalVideoCategorySearchCondition() ;
		List<String> categories = new ArrayList<String>();
		categories.addAll(Arrays.asList("教育","纪录片","旅游","亲子","广告","创意视频","拍客"));
		condition.setCategories(categories);
		condition.setLimitFrom(10);
		condition.setLimitLength(20);
		condition.setOrderBy("view_count desc , third_view_count desc , praise desc , third_praise desc , publishtime");
		List<Video> videos = videoMapperExt.selectByCategory(condition); 
		System.out.println(videos);
 	}
	
	
	@Test
	public void testSearchCountByCategory(){
		LocalVideoCategorySearchCondition condition = new LocalVideoCategorySearchCondition() ;
		List<String> categories = new ArrayList<String>();
		categories.addAll(Arrays.asList("教育","纪录片","旅游","亲子","广告","创意视频","拍客"));
		condition.setCategories(categories);
		condition.setLimitFrom(10);
		condition.setLimitLength(20);
		condition.setOrderBy("view_count desc , third_view_count desc , praise desc , third_praise desc , publishtime");
		Integer count = videoMapperExt.selectCountByCategory(condition); 
		System.out.println(count);
	}
	

}

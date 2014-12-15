package com.zf.live.dao.test;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zf.live.dao.mapper.LvuserMapperExt;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.dao.pojo.LvuserExample;
import com.zf.live.dao.vo.Page;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月14日 上午3:27:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"classpath*:spring-dao-beans.xml"}		
)
public class MyBatisTest {
	
	@Autowired
	LvuserMapperExt lvuserMapper ;
	
	@Test
	public void testInsert(){
		Lvuser lvuser = new Lvuser() ;
		lvuser.setLoginname((UUID.randomUUID().toString().substring(0, 5)));
		int count = lvuserMapper.insert(lvuser) ;
		System.out.println("insert count : " + count);
	}
	
	@Test
	public void testSelect(){
		Lvuser lvuser = lvuserMapper.selectByPrimaryKey(1L) ;
		System.out.println("lvuser.name : " + lvuser.getLoginname());
	}
	
	@Test
	public void pageQuery(){
		LvuserExample example = new LvuserExample() ;
		example.createCriteria().andIdGreaterThan(1l) ;
		example.setPage(new Page(2, 4)); 
		List<Lvuser> users = lvuserMapper.selectByExample(example) ;
		System.out.println("size:" + users.size());
	}
	
	@Test
	public void testExt(){
		Lvuser lvuser = lvuserMapper.selectRandom() ;
		System.out.println("username:" + lvuser.getLoginname());
	}

}

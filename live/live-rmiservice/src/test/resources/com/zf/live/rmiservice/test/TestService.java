package com.zf.live.rmiservice.test;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zf.live.dao.mapper.LvuserMapperExt;
import com.zf.live.dao.pojo.Lvuser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	{"classpath*:applicationContext.xml"}		
	)
public class TestService {

    @Autowired
    LvuserMapperExt lvuserMapper ;

    @Test
    public void testInsert(){
	Lvuser lvuser = new Lvuser() ;
	lvuser.setLoginname((UUID.randomUUID().toString().substring(0, 5))) ;
	int count = lvuserMapper.insert(lvuser) ;
	System.out.println("insert count : " + count);
    }


}

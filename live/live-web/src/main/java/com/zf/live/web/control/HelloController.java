package com.zf.live.web.control;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.user.LvuserService;
import com.zf.live.dao.pojo.Lvuser;

@Controller
@RequestMapping("/hello/*")
public class HelloController {
	
	static final Logger log = LoggerFactory.getLogger(HelloController.class);

	@Resource(name = "lvuserService")
	private LvuserService lvuserService ; 

	public HelloController() {
		log.info("HelloController............");
	}

	@RequestMapping("/world")
	public String helloUser(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		Lvuser lvuser = lvuserService.selectByIdWithCache(3L) ;
		if(lvuser != null){
			modelMap.put("username", "is_zhoufeng " + lvuser.getLoginname());
		}
		return "hello";
	}

}

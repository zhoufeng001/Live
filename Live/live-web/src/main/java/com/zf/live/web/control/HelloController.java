package com.zf.live.web.control;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.user.LvuserService;
import com.zf.live.dao.pojo.Lvuser;

@Controller
@RequestMapping("/hello/*")
public class HelloController {

	@Resource(name = "lvuserService")
	private LvuserService lvuserService ; 

	public HelloController() {
		System.out.println("HelloController............");
	}

	@RequestMapping("/world")
	public String helloUser(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		Lvuser lvuser = lvuserService.selectById(1) ;
		modelMap.put("username", "is_zhoufeng " + lvuser.getLoginname());
		return "hello";
	}

}

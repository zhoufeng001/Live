package com.zf.live.web.control;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.web.app.service.WebUserService;

@Controller
@RequestMapping()
public class IndexController {
	
	@RequestMapping("/index")
	public String index(@CookieValue(required = false ,value="_tc_k_") String token , ServletRequest request, ServletResponse response , ModelMap modelMap) {
		Lvuser currentUser = WebUserService.getCurrentUser();
		modelMap.put("user", currentUser); 
		return "index";
	}

}

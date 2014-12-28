package com.zf.live.web.control;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IndexController {
	
	@RequestMapping("/index")
	public String index(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		return "index";
	}

}

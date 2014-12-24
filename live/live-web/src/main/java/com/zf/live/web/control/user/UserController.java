package com.zf.live.web.control.user;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月24日 下午5:54:23
 */
@Controller
@RequestMapping("/user/*")
public class UserController {

	@Resource(name = "lvuserService")
	private LvuserService lvuserService ; 
	
	/**
	 * 去登录页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/loginView")
	public String helloUser(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		return "user/loginView";
	}
	
	/**
	 * 执行登录操作
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(String userkey , String secret , 
			ServletRequest request, ServletResponse response , ModelMap modelMap) {
		ServiceResult<String> result = lvuserService.login4Platform(userkey, secret) ;
		if(result == null){
			return "redirect:/user/loginView.htm";
		}else{
			if(result.isSuccess()){
				
				// TODO 为浏览器添加cookie
				
				return "index";
			}else{
				return "redirect:/user/loginView.htm";
			}
		}
	}
}

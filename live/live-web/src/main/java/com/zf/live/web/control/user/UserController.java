package com.zf.live.web.control.user;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.common.generator.DefaultIdxcodeGenerator;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.web.app.service.LiveWebUtil;
import com.zf.live.web.app.util.WebTokenUtil;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月24日 下午5:54:23
 */
@Controller
@RequestMapping("/user/*")
public class UserController {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

	@Resource(name = "lvuserService")
	private LvuserService lvuserService ; 

	@Autowired
	private WebTokenUtil webTokenUtil;

	@Autowired
	private DefaultIdxcodeGenerator defaultIdxcodeGenerator ;
	
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

	/**710002373100
	 * 执行登录操作
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(String userkey , String secret , 
			HttpServletRequest request, HttpServletResponse response , ModelMap modelMap) {
		ServiceResult<String> result = lvuserService.login4Platform(userkey, secret) ;
		if(result == null){
			return LiveWebUtil.redirectLoginPath();
		}else{
			if(result.isSuccess()){
				String token = result.getData() ;
				webTokenUtil.createTokenCookie(request, response, token); 
				return LiveWebUtil.redirectIndexPath() ;
			}else{
				return LiveWebUtil.redirectLoginPath();
			}
		}
	}

	/**
	 * 执行注册操作
	 * @param userkey
	 * @param secret
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/doRegist")
	public String doRegist(String userkey , String secret , String nick,
			HttpServletRequest request, HttpServletResponse response , ModelMap modelMap){
		Lvuser user = new Lvuser();
		user.setLoginname(userkey);
		user.setPassword(secret);
		user.setNick(nick);
		ServiceResult<Long> result = lvuserService.regist(user, defaultIdxcodeGenerator);
		if(request == null){
			return LiveWebUtil.redirectIndexPath() ;
		}
		if(result.isSuccess()){
			return LiveWebUtil.redirectIndexPath() ;
		}else{
			return LiveWebUtil.redirectLoginPath() ;
		}
	}

}

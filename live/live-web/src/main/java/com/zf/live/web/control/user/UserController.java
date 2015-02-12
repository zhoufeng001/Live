package com.zf.live.web.control.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zf.live.client.user.IdxcodeGenerator;
import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.web.AjaxResult;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.web.WebConst;
import com.zf.live.web.app.RequestContext;
import com.zf.live.web.app.service.LiveWebUtil;
import com.zf.live.web.app.service.user.WebUserService;
import com.zf.live.web.app.util.WebTokenUtil;
import com.zf.live.web.app.util.WebUtil;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月24日 下午5:54:23
 */
@Controller
@RequestMapping("/user")
public class UserController {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "lvuserService")
	private LvuserService lvuserService ; 

	@Autowired
	private WebTokenUtil webTokenUtil;

	@Autowired
	private IdxcodeGenerator defaultIdxcodeGenerator ;

	@Autowired
	private WebUserService webUserService ;
	
	/**
	 * 去登录页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/loginView")
	public String loginView(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		String errMessage = RequestContext.getErrTipMessage() ;
		log.error(errMessage); 
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
			HttpServletRequest request, HttpServletResponse response , ModelMap modelMap) {
		ServiceResult<String> result = lvuserService.login4Platform(userkey, secret) ;
		if(result == null){
			return LiveWebUtil.redirectLoginPath();
		}else{
			if(result.isSuccess()){
				String token = result.getData() ;
				Lvuser user = lvuserService.getUserByToken(token);
				webTokenUtil.createTokenCookie(request, response, token , user); 
				
				Object refresh = request.getSession().getAttribute(WebConst.sessionRefreshKey); 
				if(refresh != null){
					return LiveWebUtil.redirectPath(refresh.toString()); 
				}
				return LiveWebUtil.redirectIndexPath() ;
			}else{
				RequestContext.setErrTipMessage(result.getErrMssage());
				return LiveWebUtil.redirectLoginPath();
			}
		}
	}
	
	/**
	 * 执行登录操作（Ajax）
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/doLoginAjax")
	public void doLoginAjax(String userkey , String secret , 
			HttpServletRequest request, HttpServletResponse response , ModelMap modelMap) {
		ServiceResult<String> result = lvuserService.login4Platform(userkey, secret) ;
		if(result == null){
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "登录失败，获取用户信息失败！", -1), response);
			return ;
		}else{
			if(result.isSuccess()){
				String token = result.getData() ;
				WebUtil.ajaxOutput(AjaxResult.newSuccessResult(token), response);
				return ;
			}else{
				WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "登录失败，获取用户信息失败！", -2), response);
				return ;
			}
		}
	}
	

	@RequestMapping("/registView")
	public String registView(HttpServletRequest request, HttpServletResponse response , ModelMap modelMap){
		return "user/registView";
	}

	/**
	 * 执行平台用户注册操作
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
		user.setIdxcode(defaultIdxcodeGenerator.generate());
		ServiceResult<Long> result = lvuserService.regist(user);
		if(request == null){
			return LiveWebUtil.redirectIndexPath() ;
		}
		if(result.isSuccess()){
			//自动登录
			Map<String, String> params = new HashMap<String, String>();
			params.put("userkey", userkey);
			params.put("secret", secret);
			return LiveWebUtil.redirectPath("/user/doLogin.htm", params) ;
		}else{
			return LiveWebUtil.redirectLoginPath() ;
		}
	}

	/**
	 * 执行用户退出操作
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/doLogout")
	public String doLogout(HttpServletRequest request, HttpServletResponse response , ModelMap modelMap){
		String token = webTokenUtil.getTokenFromCookie(request) ;
		if(StringUtils.isBlank(token)){
			return LiveWebUtil.redirectIndexPath() ;
		}
		lvuserService.logoutByToken(token); 
		webTokenUtil.deleteTokenCookiee(request, response); 
		
		Object refresh = request.getSession().getAttribute(WebConst.sessionRefreshKey); 
		if(refresh != null){
			return LiveWebUtil.redirectPath(refresh.toString()); 
		}
		return LiveWebUtil.redirectIndexPath() ;
	}

}

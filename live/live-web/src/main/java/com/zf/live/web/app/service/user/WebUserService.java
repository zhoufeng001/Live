package com.zf.live.web.app.service.user;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.user.LvuserService;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.web.WebConst;
import com.zf.live.web.app.util.WebTokenUtil;

@Component("webUserService")
public class WebUserService {

	static final Logger log = LoggerFactory.getLogger(WebUserService.class);

	@Autowired
	private WebTokenUtil webTokenUtil ;

	@Resource(name="lvuserService")
	private LvuserService lvuserService ;


	/**
	 * 获取当前登陆用户
	 * @param request
	 * @param response
	 * @return
	 */
	public Lvuser getCurrentUser(ServletRequest request){
		String token = webTokenUtil.getTokenFromCookie((HttpServletRequest)request);
		if(StringUtils.isBlank(token)){
			return null ;
		}
		return lvuserService.getUserByToken(token); 
	}

	/**
	 * 将当前登录用户放入Request
	 * @param request
	 */
	public void putCurrentUserToRequest(ServletRequest request){ 
		Lvuser user = getCurrentUser(request) ;
		if(user != null){
			((HttpServletRequest)request).setAttribute(WebConst.RequestUserKey,user ); 
		}
	}

}

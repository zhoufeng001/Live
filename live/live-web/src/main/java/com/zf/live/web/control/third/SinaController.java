package com.zf.live.web.control.third;

import java.io.InputStream;

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

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.zf.live.client.user.IdxcodeGenerator;
import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.common.Const;
import com.zf.live.common.util.HttpClientUtils;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.dao.pojo.Thirduser;
import com.zf.live.web.app.service.LiveWebUtil;
import com.zf.live.web.app.util.UploadUtil;
import com.zf.live.web.app.util.WebTokenUtil;

/**
 * sina微博登录
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月1日 下午10:05:54
 */
@Controller
@RequestMapping("/sina")
public class SinaController {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(SinaController.class);

	@Autowired
	private LvuserService lvuserService ;

	@Autowired
	private IdxcodeGenerator defaultIdxcodeGenerator ;

	@Autowired
	private WebTokenUtil webTokenUtil;
	
	@Autowired
	private UploadUtil uploadUtil;

	/**
	 * 去到SINA登录页
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/gotoLogin")
	public String gotoLogin(ServletRequest request, ServletResponse response , ModelMap modelMap){
		try {
			Oauth oauth = new Oauth();
			return LiveWebUtil.redirectPath(oauth.authorize("code",""))  ;
		} catch (WeiboException e) {
			log.error("去到sina登录页面失败！");
			log.error(e.getMessage());
		}
		return LiveWebUtil.redirectIndexPath() ;
	}

	/**
	 * sina登录成功后回调地址
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/callback")
	public String callback(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		try {
			Oauth oauth = new Oauth();
			String code = request.getParameter("code") ;
			if(StringUtils.isBlank(code)){
				log.warn("sina login code is empty~");
				return LiveWebUtil.redirectIndexPath() ;
			}
			AccessToken accessToken = oauth.getAccessTokenByCode(code);
			if(accessToken == null){
				log.warn("accesstoken is null ");
				return LiveWebUtil.redirectIndexPath() ;
			}
			
			String accessTokenValue = accessToken.getAccessToken();
			if(StringUtils.isBlank(accessTokenValue)){
				log.warn("accessTokenValue is null");
				return LiveWebUtil.redirectIndexPath() ;
			}
			
			Users um = new Users();
			um.setToken(accessTokenValue);
			User user = um.showUserById(accessToken.getUid());
			if(user == null){
				log.warn("sina 获取用户数据失败！");
				return LiveWebUtil.redirectIndexPath() ;
			}
			
			String nick = user.getScreenName() ;
			String openId = user.getIdstr() ;
			String photo = user.getProfileImageUrl() ;
			
			Lvuser lvuser = lvuserService.selectByThirdInfo(Const.UserConst.USER_FROM_SINA, openId);
			if(lvuser == null){
				//注册
				lvuser = new Lvuser() ;
				lvuser.setNick(nick);
				lvuser.setIdxcode(defaultIdxcodeGenerator.generate());
				if(StringUtils.isNotBlank(photo)){
					InputStream photoIs = HttpClientUtils.getInputStream(photo);
					if(photoIs != null){
						String photoPath = uploadUtil.uploadUserPhoto(photoIs);
						lvuser.setPhoto(photoPath); 
					}
				}
				
				Thirduser thirduser = new Thirduser();
				thirduser.setUserfrom(Const.UserConst.USER_FROM_SINA);
				thirduser.setOpenid(openId);
				
				ServiceResult<Long> registResult = lvuserService.regist4Third(lvuser , thirduser) ;
				if(registResult.isSuccess()){
					//登录
					ServiceResult<String> loginResult = lvuserService.login4Third(Const.UserConst.USER_FROM_SINA, openId) ;
					if(loginResult.isSuccess()){
						String token = loginResult.getData() ;
						Lvuser loginUser = lvuserService.getUserByToken(token);
						webTokenUtil.createTokenCookie((HttpServletRequest)request,(HttpServletResponse) response, token , loginUser); 
					}else{
						log.warn("SINA用户{}登录失败", openId);
					}
				}else{
					log.error("SINA用户注册失败{},{}" , openId , nick);
				}
			}else{
				//登录
				ServiceResult<String> loginResult = lvuserService.login4Third(Const.UserConst.USER_FROM_SINA, openId) ;
				if(loginResult.isSuccess()){
					String token = loginResult.getData() ;
					Lvuser loginUser = lvuserService.getUserByToken(token);
					webTokenUtil.createTokenCookie((HttpServletRequest)request,(HttpServletResponse) response, token ,loginUser); 
				}else{
					log.warn("SINA用户{}登录失败", openId);
				}
			}
			
		} catch (WeiboException e) {
			log.error(e.getMessage()); 
		}
		return LiveWebUtil.redirectIndexPath();
	}


}

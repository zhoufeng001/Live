package com.zf.live.web.control.third;

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

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.zf.live.client.user.IdxcodeGenerator;
import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.common.Const;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.dao.pojo.Thirduser;
import com.zf.live.web.app.service.LiveWebUtil;
import com.zf.live.web.app.util.WebTokenUtil;

/**
 * QQ登录
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月1日 下午10:05:43
 */
@Controller
@RequestMapping("/qq")
public class QQController {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(QQController.class);
	
	@Autowired
	private LvuserService lvuserService ;
	
	@Autowired
	private IdxcodeGenerator defaultIdxcodeGenerator ;

	@Autowired
	private WebTokenUtil webTokenUtil;
	
	/**
	 * 去到qq登录页
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/gotoLogin")
	public String gotoLogin(ServletRequest request, ServletResponse response , ModelMap modelMap){
		response.setContentType("text/html;charset=utf-8");
		try {
			return LiveWebUtil.redirectPath(new Oauth().getAuthorizeURL(request));
		} catch (QQConnectException e) {
			log.error("去到qq登录页面失败，" + e.getMessage()); 
			return LiveWebUtil.redirectIndexPath() ;
		}
	}

	/**
	 * QQ登录回调
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/callback")
	public String callback(ServletRequest request, ServletResponse response , ModelMap modelMap) {

		try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			String accessToken  = null,openID = null;
			if (accessTokenObj.getAccessToken().equals("")) {
				log.warn("没有获取到响应参数");
			}else{
				accessToken = accessTokenObj.getAccessToken();

				OpenID openIDObj =  new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();

				if(StringUtils.isBlank(openID)){
					log.warn("获取用户openID失败");
					return LiveWebUtil.redirectIndexPath() ;
				}
				
				log.info("用户openid：" + openID);
				
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();

				if (userInfoBean.getRet() == 0) {
					//获取用户qq昵称
					String userNick = userInfoBean.getNickname() ;
					String photo = userInfoBean.getAvatar().getAvatarURL30() ;
					log.info("用户nick{},photo{},openid{}登录成功" , userNick , photo , openID);
					
					Lvuser lvuser = lvuserService.selectByThirdInfo(Const.UserConst.USER_FROM_QQ, openID);
					if(lvuser == null){
						//注册
						lvuser = new Lvuser() ;
						lvuser.setNick(userNick);
						lvuser.setPhoto(photo); 
						lvuser.setIdxcode(defaultIdxcodeGenerator.generate());
						
						Thirduser thirduser = new Thirduser() ;
						thirduser.setOpenid(openID);
						thirduser.setUserfrom(Const.UserConst.USER_FROM_QQ);
						
						ServiceResult<Long> registResult = lvuserService.regist4Third(lvuser , thirduser) ;
						if(registResult.isSuccess()){
							//登录
							ServiceResult<String> loginResult = lvuserService.login4Third(Const.UserConst.USER_FROM_QQ, openID) ;
							if(loginResult.isSuccess()){
								webTokenUtil.createTokenCookie((HttpServletRequest)request,(HttpServletResponse) response, loginResult.getData()); 
							}else{
								log.warn("QQ用户{}登录失败", openID);
							}
						}else{
							log.error("QQ用户注册失败{},{}" , openID , userNick);
						}
					}else{
						//登录
						ServiceResult<String> loginResult = lvuserService.login4Third(Const.UserConst.USER_FROM_QQ, openID) ;
						if(loginResult.isSuccess()){
							webTokenUtil.createTokenCookie((HttpServletRequest)request,(HttpServletResponse) response, loginResult.getData()); 
						}else{
							log.warn("QQ用户{}登录失败", openID);
						}
					}
					return LiveWebUtil.redirectIndexPath() ;
				}else{
					log.warn("获取用户qq信息失败！");
					return LiveWebUtil.redirectIndexPath() ;
				}
			}
		} catch (QQConnectException e) {
			log.error("QQ登录失败");
			log.error(e.getMessage());
		}
		return LiveWebUtil.redirectIndexPath();
	}

}

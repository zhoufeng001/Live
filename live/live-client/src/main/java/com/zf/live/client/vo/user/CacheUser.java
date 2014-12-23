package com.zf.live.client.vo.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录信息（存于Redis）
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月23日 下午5:22:37
 */
public class CacheUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7584456257472614757L;
	
	private Long id ;
	private String loginname ;
	private String nick ;
	private String mail ;
	private String idxcode ;
	private Integer flag ;
	private Long coin ;
	private String photo ;
	private String qq ;
	private Integer praise ;
	private Byte userfrom ;
	private String token  ;
	private Date loginTime ;
	/**
	 * 1:已登录   0：未登录
	 */
	private Integer isLogin ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getIdxcode() {
		return idxcode;
	}
	public void setIdxcode(String idxcode) {
		this.idxcode = idxcode;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Long getCoin() {
		return coin;
	}
	public void setCoin(Long coin) {
		this.coin = coin;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public Byte getUserfrom() {
		return userfrom;
	}
	public void setUserfrom(Byte userfrom) {
		this.userfrom = userfrom;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Integer isLogin) {
		this.isLogin = isLogin;
	}
}

package com.zf.live.client.user;

import com.zf.live.client.vo.ServiceResult;
import com.zf.live.dao.pojo.Lvuser;

/**
 * 对User基本操作
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月18日 下午2:42:22
 */
public interface LvuserService {

	/**
	 * 根据userid查询用户
	 * @param id
	 * @return
	 */
   public Lvuser selectByIdWithCache(Long id);
   
   /**
    * 根据loginname查询用户信息
    * @param loginname
    * @return
    */
   public Lvuser selectByLoginname(String loginname) ;
   
   /**
    * 根据邮箱查找用户信息
    * @param mail
    * @return
    */
   public Lvuser selectByMail(String mail);
   
   /**
    * 根据手机号查询用户信息
    * @param phone
    * @return
    */
   public Lvuser selectByPhone(String phone);
   
   /**
    * 根据Idxcode查询指定用户
    * @param idxcode
    * @return
    */
   public Lvuser selectByIdxcode(String idxcode);
   
   /**
    * 判断根据登录名指定的用户是否存在
    * @param loginname
    * @return
    */
   public boolean existLoginname(String loginname) ;
   
   /**
    * 查询指定的邮箱是否存在
    * @param mail
    * @return
    */
   public boolean existMail(String mail);
   
   
   /**
    * 判断根据idxcode指定的用户是否存在
    * @param loginname
    * @return
    */
   public boolean existIdxcode(String idxcode) ;
   
   
   /**
    * 用户注册
    * @param user
    * @return
    */
   public ServiceResult<Long> regist(Lvuser user,IdxcodeGenerator idxcodeGenerator) ;
   
   
   /**
    * 平台用户登录
    * @param userKey 登录名/邮箱/手机号
    * @param secret 密码
    * @return 登录成功返回token
    */
   public ServiceResult<String> login4Platform(String userKey , String secret);
   
   /**
    * 根据token获取已登录用户的信息
    * @param token
    * @return
    */
   public Lvuser getUserByToken(String token);
   
   /**
    * 用户退出（根据用户id）
    * @param userid
    */
   public void logoutByUserid(Long userid);
   
   /**
    * 用户退出（根据token）
    * @param token
    */
   public void logoutByToken(String token);
   
   /**
    * 根据userid判断用户是否已登录
    * @param userid
    * @return
    */
   public boolean isLogin(Long userid);
}

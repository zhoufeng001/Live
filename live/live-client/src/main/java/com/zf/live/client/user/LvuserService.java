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
   public Lvuser selectById(Integer id);
   
   /**
    * 根据loginname查询用户信息
    * @param loginname
    * @return
    */
   public Lvuser selectByLoginname(String loginname) ;
   
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
   
   
}

package com.zf.live.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * tableName{lvuserinfo}
 * by is_zhoufeng@163.com 2014-12-15 17:32:50
 */
public class Lvuserinfo implements Serializable {
    /**
     * 对应userid  
     * column{id},jdbcType{BIGINT}
     */
    private Long id;

    /**
     * 生日  
     * column{birthday},jdbcType{DATE}
     */
    private Date birthday;

    /**
     * 个性签名  
     * column{sign},jdbcType{VARCHAR}
     */
    private String sign;

    /**
     * 创建时间  
     * column{createtime},jdbcType{TIMESTAMP}
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lvuserinfo
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * 对应userid  
     * column{id},jdbcType{BIGINT}
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 生日  
     * column{birthday},jdbcType{DATE}
     */
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 个性签名  
     * column{sign},jdbcType{VARCHAR}
     */
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 创建时间  
     * column{createtime},jdbcType{TIMESTAMP}
     */
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
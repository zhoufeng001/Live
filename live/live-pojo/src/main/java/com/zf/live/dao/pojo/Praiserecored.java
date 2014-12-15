package com.zf.live.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * tableName{praiserecored}
 * by is_zhoufeng@163.com 2014-12-15 17:32:50
 */
public class Praiserecored implements Serializable {
    /**
     *   
     * column{id},jdbcType{BIGINT}
     */
    private Long id;

    /**
     * 点赞用户id  
     * column{userid},jdbcType{BIGINT}
     */
    private Long userid;

    /**
     * 被赞用户id  
     * column{ownerid},jdbcType{BIGINT}
     */
    private Long ownerid;

    /**
     * 被赞视频id  
     * column{videoid},jdbcType{BIGINT}
     */
    private Long videoid;

    /**
     * 创建时间  
     * column{createtime},jdbcType{TIMESTAMP}
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table praiserecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     *   
     * column{id},jdbcType{BIGINT}
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 点赞用户id  
     * column{userid},jdbcType{BIGINT}
     */
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 被赞用户id  
     * column{ownerid},jdbcType{BIGINT}
     */
    public Long getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Long ownerid) {
        this.ownerid = ownerid;
    }

    /**
     * 被赞视频id  
     * column{videoid},jdbcType{BIGINT}
     */
    public Long getVideoid() {
        return videoid;
    }

    public void setVideoid(Long videoid) {
        this.videoid = videoid;
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
package com.zf.live.dao.pojo;

import java.io.Serializable;

/**
 * tableName{audience_count}
 * by is_zhoufeng@163.com 2015-02-08 22:22:39
 */
public class AudienceCount implements Serializable {
    /**
     * 视频id  
     * column{vid},jdbcType{VARCHAR}
     */
    private String vid;

    /**
     * 视频分类  
     * column{video_category},jdbcType{VARCHAR}
     */
    private String videoCategory;

    /**
     * 观众人数（游客人数 + 登录人数）  
     * column{audience_count},jdbcType{INTEGER}
     */
    private Integer audienceCount;

    /**
     * 游客人数  
     * column{tourist_count},jdbcType{INTEGER}
     */
    private Integer touristCount;

    /**
     * 登录用户人数  
     * column{user_count},jdbcType{INTEGER}
     */
    private Integer userCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table audience_count
     *
     * @mbggenerated Sun Feb 08 22:22:39 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * 视频id  
     * column{vid},jdbcType{VARCHAR}
     */
    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    /**
     * 视频分类  
     * column{video_category},jdbcType{VARCHAR}
     */
    public String getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(String videoCategory) {
        this.videoCategory = videoCategory;
    }

    /**
     * 观众人数（游客人数 + 登录人数）  
     * column{audience_count},jdbcType{INTEGER}
     */
    public Integer getAudienceCount() {
        return audienceCount;
    }

    public void setAudienceCount(Integer audienceCount) {
        this.audienceCount = audienceCount;
    }

    /**
     * 游客人数  
     * column{tourist_count},jdbcType{INTEGER}
     */
    public Integer getTouristCount() {
        return touristCount;
    }

    public void setTouristCount(Integer touristCount) {
        this.touristCount = touristCount;
    }

    /**
     * 登录用户人数  
     * column{user_count},jdbcType{INTEGER}
     */
    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
}
package com.zf.live.dao.pojo;

import java.io.Serializable;

public class VideoDetailWithBLOBs extends VideoDetail implements Serializable {
    /**
     * 多张截图（JSON格式对象）  
     * column{thumbnails},jdbcType{LONGVARCHAR}
     */
    private String thumbnails;

    /**
     * 视频描述  
     * column{description},jdbcType{LONGVARCHAR}
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table video_detail
     *
     * @mbggenerated Thu Feb 05 22:36:26 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * 多张截图（JSON格式对象）  
     * column{thumbnails},jdbcType{LONGVARCHAR}
     */
    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * 视频描述  
     * column{description},jdbcType{LONGVARCHAR}
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
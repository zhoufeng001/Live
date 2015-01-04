package com.zf.live.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * tableName{video}
 * by is_zhoufeng@163.com 2015-01-05 00:14:43
 */
public class Video implements Serializable {
    /**
     *   
     * column{id},jdbcType{BIGINT}
     */
    private Long id;

    /**
     * 视频来源，1：优酷  
     * column{videofrom},jdbcType{TINYINT}
     */
    private Byte videofrom;

    /**
     * 视频来源网站ID  
     * column{fromid},jdbcType{VARCHAR}
     */
    private String fromid;

    /**
     * 视频名称  
     * column{videoname},jdbcType{VARCHAR}
     */
    private String videoname;

    /**
     * 视频图片（小图)  
     * column{thumbnail},jdbcType{VARCHAR}
     */
    private String thumbnail;

    /**
     * 视频分类  
     * column{category},jdbcType{VARCHAR}
     */
    private String category;

    /**
     * 视频被赞次数  
     * column{praise},jdbcType{BIGINT}
     */
    private Long praise;

    /**
     * 播放次数  
     * column{playcount},jdbcType{INTEGER}
     */
    private Integer playcount;

    /**
     * 扩展标位  
     * column{flag},jdbcType{INTEGER}
     */
    private Integer flag;

    /**
     * 创建时间  
     * column{createtime},jdbcType{TIMESTAMP}
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table video
     *
     * @mbggenerated Mon Jan 05 00:14:43 CST 2015
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
     * 视频来源，1：优酷  
     * column{videofrom},jdbcType{TINYINT}
     */
    public Byte getVideofrom() {
        return videofrom;
    }

    public void setVideofrom(Byte videofrom) {
        this.videofrom = videofrom;
    }

    /**
     * 视频来源网站ID  
     * column{fromid},jdbcType{VARCHAR}
     */
    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    /**
     * 视频名称  
     * column{videoname},jdbcType{VARCHAR}
     */
    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    /**
     * 视频图片（小图)  
     * column{thumbnail},jdbcType{VARCHAR}
     */
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 视频分类  
     * column{category},jdbcType{VARCHAR}
     */
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 视频被赞次数  
     * column{praise},jdbcType{BIGINT}
     */
    public Long getPraise() {
        return praise;
    }

    public void setPraise(Long praise) {
        this.praise = praise;
    }

    /**
     * 播放次数  
     * column{playcount},jdbcType{INTEGER}
     */
    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    /**
     * 扩展标位  
     * column{flag},jdbcType{INTEGER}
     */
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
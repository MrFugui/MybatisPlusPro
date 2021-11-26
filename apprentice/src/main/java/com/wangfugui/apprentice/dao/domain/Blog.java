package com.wangfugui.apprentice.dao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 博客表
 * </p>
 *
 * @author MrFugui
 * @since 2021-11-25
 */
@ApiModel(value = "Blog对象", description = "博客表")
public class Blog implements Serializable {

    private Integer id;

    @ApiModelProperty("博客标题")
    private String blogTitle;

    @ApiModelProperty("博客内容")
    private String blogText;

    @ApiModelProperty("图片id")
    private Integer fileId;

    @ApiModelProperty("点赞数量")
    private Integer goodNumber;

    @ApiModelProperty("评论数量")
    private Integer talkNumber;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("是否删除")
    private Boolean deleteFlag;

    @ApiModelProperty("创建人")
    private Integer createUser;

    @ApiModelProperty("浏览量")
    private Integer lookNumber;

    @ApiModelProperty("收藏量")
    private Integer starNumber;

    @ApiModelProperty("0原创1转载")
    private Boolean creation;

    @ApiModelProperty("0未审核1审核中2审核失败3已发布")
    private Integer blogStatus;

    @ApiModelProperty("版权地址")
    private String copyrightLink;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public Integer getTalkNumber() {
        return talkNumber;
    }

    public void setTalkNumber(Integer talkNumber) {
        this.talkNumber = talkNumber;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getLookNumber() {
        return lookNumber;
    }

    public void setLookNumber(Integer lookNumber) {
        this.lookNumber = lookNumber;
    }

    public Integer getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(Integer starNumber) {
        this.starNumber = starNumber;
    }

    public Boolean getCreation() {
        return creation;
    }

    public void setCreation(Boolean creation) {
        this.creation = creation;
    }

    public Integer getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Integer blogStatus) {
        this.blogStatus = blogStatus;
    }

    public String getCopyrightLink() {
        return copyrightLink;
    }

    public void setCopyrightLink(String copyrightLink) {
        this.copyrightLink = copyrightLink;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blogTitle=" + blogTitle +
                ", blogText=" + blogText +
                ", fileId=" + fileId +
                ", goodNumber=" + goodNumber +
                ", talkNumber=" + talkNumber +
                ", createTime=" + createTime +
                ", deleteFlag=" + deleteFlag +
                ", createUser=" + createUser +
                ", lookNumber=" + lookNumber +
                ", starNumber=" + starNumber +
                ", creation=" + creation +
                ", blogStatus=" + blogStatus +
                ", copyrightLink=" + copyrightLink +
                "}";
    }
}

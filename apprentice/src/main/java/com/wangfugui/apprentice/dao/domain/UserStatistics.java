package com.wangfugui.apprentice.dao.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户统计表
 * </p>
 *
 * @author MrFugui
 * @since 2021-11-25
 */
@TableName("user_statistics")
@ApiModel(value = "UserStatistics对象", description = "用户统计表")
public class UserStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
      private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("粉丝数量")
    private Integer followers;

    @ApiModelProperty("关注数量")
    private Integer following;

    @ApiModelProperty("点赞数量")
    private Integer goodNumber;

    @ApiModelProperty("访问数量")
    private Integer watchNumber;

    @ApiModelProperty("师傅数量")
    private Integer masterNumber;

    @ApiModelProperty("徒弟数量")
    private Integer apprenticeNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public Integer getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(Integer watchNumber) {
        this.watchNumber = watchNumber;
    }

    public Integer getMasterNumber() {
        return masterNumber;
    }

    public void setMasterNumber(Integer masterNumber) {
        this.masterNumber = masterNumber;
    }

    public Integer getApprenticeNumber() {
        return apprenticeNumber;
    }

    public void setApprenticeNumber(Integer apprenticeNumber) {
        this.apprenticeNumber = apprenticeNumber;
    }

    @Override
    public String toString() {
        return "UserStatistics{" +
        "id=" + id +
        ", userId=" + userId +
        ", followers=" + followers +
        ", following=" + following +
        ", goodNumber=" + goodNumber +
        ", watchNumber=" + watchNumber +
        ", masterNumber=" + masterNumber +
        ", apprenticeNumber=" + apprenticeNumber +
        "}";
    }
}

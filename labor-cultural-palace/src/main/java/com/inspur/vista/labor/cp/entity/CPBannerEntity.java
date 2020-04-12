package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPBannerEntity
 * @Description: 文化宫banner图
 * @authur: wangxueying01
 * @CreatDate: 2020/3/27 9:35
 */
@ApiModel(value = "banner图", description = "banner图")
public class CPBannerEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", required = true, example = "123", position = 2)
    private String cpId;

    /**
     * banner名称
     */
    @ApiModelProperty(value = "banner名称", required = true, example = "123", position = 3)
    private String bannerName;

    /**
     * banner图片地址
     */
    @ApiModelProperty(value = "banner图片地址", example = "", position = 4)
    private String bannerImageUrl;

    /**
     * banner外链地址
     */
    @ApiModelProperty(value = "banner外链地址", example = "", position = 5)
    private String bannerLink;

    /**
     * bannner顺序
     */
    @ApiModelProperty(value = "bannner顺序（新增默认顺序为最大）", example = "", position = 6)
    private Integer bannerOrder;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述",  example = "123", position = 7)
    private String bannerDesc;

    /**
     * 是否展示：1 展示 0 不展示
     */
    @ApiModelProperty(value = "是否展示：1 展示 0 不展示(新增默认不展示)", example = "1", position = 8)
    private Integer isShow;

    /**
     * 状态 1：有效；0：无效
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer state;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", hidden = true)
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }

    public Integer getBannerOrder() {
        return bannerOrder;
    }

    public void setBannerOrder(Integer bannerOrder) {
        this.bannerOrder = bannerOrder;
    }

    public String getBannerDesc() {
        return bannerDesc;
    }

    public void setBannerDesc(String bannerDesc) {
        this.bannerDesc = bannerDesc;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CPBannerEntity{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", bannerName='" + bannerName + '\'' +
                ", bannerImageUrl='" + bannerImageUrl + '\'' +
                ", bannerLink='" + bannerLink + '\'' +
                ", bannerOrder=" + bannerOrder +
                ", bannerDesc='" + bannerDesc + '\'' +
                ", isShow=" + isShow +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", modifier='" + modifier + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

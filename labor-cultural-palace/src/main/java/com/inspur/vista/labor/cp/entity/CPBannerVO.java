package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPBannerVO
 * @Description: 文化宫banner图VO
 * @authur: wangxueying01
 * @CreatDate: 2020/3/27 9:36
 */
@ApiModel(value = "banner图VO", description = "banner图VO")
public class CPBannerVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", position = 2)
    private String cpId;

    /**
     * banner名称
     */
    @ApiModelProperty(value = "banner名称", position = 3)
    private String bannerName;

    /**
     * banner图片地址
     */
    @ApiModelProperty(value = "banner图片地址", position = 4)
    private String bannerImageUrl;

    /**
     * banner外链地址
     */
    @ApiModelProperty(value = "banner外链地址", position = 5)
    private String bannerLink;

    /**
     * bannner顺序
     */
    @ApiModelProperty(value = "bannner顺序", position = 6)
    private Integer bannerOrder;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", position = 7)
    private String bannerDesc;

    /**
     * 是否展示：1 展示 0 不展示
     */
    @ApiModelProperty(value = "是否展示：1 展示 0 不展示",  position = 8)
    private Integer isShow;

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

    @Override
    public String toString() {
        return "CPBannerVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", bannerName='" + bannerName + '\'' +
                ", bannerImageUrl='" + bannerImageUrl + '\'' +
                ", bannerLink='" + bannerLink + '\'' +
                ", bannerOrder=" + bannerOrder +
                ", bannerDesc='" + bannerDesc + '\'' +
                ", isShow=" + isShow +
                '}';
    }
}

package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPEvaluationEntity
 * @Description: 文化宫评价记录
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@ApiModel(value = "文化宫评价记录", description = "文化宫评价记录")
public class CPEvaluationEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id，如果评价文化宫的时候传，预约评价不传", required = false, example = "", position = 2)
    private String cpId;

    /**
     * 预约记录id
     */
    @ApiModelProperty(value = "预约记录id，预约评价传，文化宫评价不传", required = false, example = "", position = 3)
    private String reserveId;

    /**
     * 整体星级，1到5，表示1星到5星
     */
    @ApiModelProperty(value = "整体星级，1到5，表示1星到5星", required = true, example = "5", position = 4)
    private Integer star;

    /**
     * 配套设施星级
     */
    @ApiModelProperty(value = "配套设施星级", example = "5", position = 5)
    private Integer supportingFacilityStar;

    /**
     * 服务态度星级
     */
    @ApiModelProperty(value = "服务态度星级", example = "5", position = 6)
    private Integer serviceAttitudeStar;

    /**
     * 卫生情况星级
     */
    @ApiModelProperty(value = "卫生情况星级", example = "5", position = 7)
    private Integer hygienicConditionStar;

    /**
     * 评价标签，逗号分隔（字典：EVALUATION_LABEL）
     */
    @ApiModelProperty(value = "评价标签，逗号分隔（字典：EVALUATION_LABEL）", example = "GOOD", position = 8)
    private String label;

    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容", example = "好", position = 9)
    private String content;

    /**
     * 评价的用户编码
     */
    @ApiModelProperty(value = "用户编码", hidden = true, position = 10)
    private String userCode;

    /**
     * 评价的用户昵称
     */
    @ApiModelProperty(value = "用户昵称，通过jssdk获取", example = "嘻嘻哈哈", position = 11)
    private String userNickName;

    /**
     * 评价的用户头像
     */
    @ApiModelProperty(value = "评价的用户头像url", example = "", position = 12)
    private String userIcon;

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

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getSupportingFacilityStar() {
        return supportingFacilityStar;
    }

    public void setSupportingFacilityStar(Integer supportingFacilityStar) {
        this.supportingFacilityStar = supportingFacilityStar;
    }

    public Integer getServiceAttitudeStar() {
        return serviceAttitudeStar;
    }

    public void setServiceAttitudeStar(Integer serviceAttitudeStar) {
        this.serviceAttitudeStar = serviceAttitudeStar;
    }

    public Integer getHygienicConditionStar() {
        return hygienicConditionStar;
    }

    public void setHygienicConditionStar(Integer hygienicConditionStar) {
        this.hygienicConditionStar = hygienicConditionStar;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "CPEvaluationEntity{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", reserveId='" + reserveId + '\'' +
                ", star=" + star +
                ", supportingFacilityStar=" + supportingFacilityStar +
                ", serviceAttitudeStar=" + serviceAttitudeStar +
                ", hygienicConditionStar=" + hygienicConditionStar +
                ", label='" + label + '\'' +
                ", content='" + content + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
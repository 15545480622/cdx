package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: CPEvaluationListVO
 * @Description: 文化宫评价记录
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@ApiModel(value = "文化宫评价列表记录", description = "文化宫评价列表记录")
public class CPEvaluationListVO implements Serializable {

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
     * 评价的用户昵称
     */
    @ApiModelProperty(value = "评价的用户昵称", position = 3)
    private String userNickName;

    /**
     * 评价的用户头像
     */
    @ApiModelProperty(value = "评价的用户头像", position = 4)
    private String userIcon;

    /**
     * 整体星级，1到5，表示1星到5星
     */
    @ApiModelProperty(value = "整体星级，1到5，表示1星到5星", position = 5)
    private Integer star;

    /**
     * 配套设施星级
     */
    @ApiModelProperty(value = "配套设施星级", position = 6)
    private Integer supportingFacilityStar;

    /**
     * 服务态度星级
     */
    @ApiModelProperty(value = "服务态度星级", position = 7)
    private Integer serviceAttitudeStar;

    /**
     * 卫生情况星级
     */
    @ApiModelProperty(value = "卫生情况星级", position = 8)
    private Integer hygienicConditionStar;

    /**
     * 评价标签，逗号分隔（字典：EVALUATION_LABEL）
     */
    @ApiModelProperty(value = "评价标签，逗号分隔（字典：EVALUATION_LABEL）", position = 9)
    private String label;

    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容", position = 10)
    private String content;

    /**
     * 评价时间
     */
    @ApiModelProperty(value = "评价时间", position = 11)
    private String createTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CPEvaluationListVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", star=" + star +
                ", supportingFacilityStar=" + supportingFacilityStar +
                ", serviceAttitudeStar=" + serviceAttitudeStar +
                ", hygienicConditionStar=" + hygienicConditionStar +
                ", label='" + label + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
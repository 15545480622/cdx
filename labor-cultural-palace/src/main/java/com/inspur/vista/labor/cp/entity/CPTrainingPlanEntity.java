package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPTrainingPlan
 * @Description: 人才培养计划
 * @Author: gengpeng
 * @CreateDate: 2019/12/27 8:56
 * @Version: 1.0
 */
@ApiModel(value = "人才培养计划", description = "人才培养计划")
public class CPTrainingPlanEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", required = true, example = "", position = 2)
    private String cpId;

    /**
     * 培养年度
     */
    @ApiModelProperty(value = "培养年度", required = true, example = "2020", position = 3)
    private String year;

    /**
     * 培养目标
     */
    @ApiModelProperty(value = "培养目标", required = true, example = "", position = 4)
    private String target;

    /**
     * 计划内容
     */
    @ApiModelProperty(value = "计划内容", required = true, example = "", position = 5)
    private String content;

    /**
     * 状态:1.有效;0.无效
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private int state;

    /**
     * 计划制定人
     */
    @ApiModelProperty(value = "计划制定人", hidden = true)
    private String creator;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 计划制定时间
     */
    @ApiModelProperty(value = "计划制定时间", hidden = true)
    private Date createTime;

    /**
     * 更新时间
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "CPTrainingPlanEntity{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", year='" + year + '\'' +
                ", target='" + target + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

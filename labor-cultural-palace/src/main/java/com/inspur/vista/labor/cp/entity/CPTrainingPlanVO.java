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
@ApiModel(value = "人才培养计划信息", description = "人才培养计划信息")
public class CPTrainingPlanVO implements Serializable {

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
     * 培养年度
     */
    @ApiModelProperty(value = "培养年度", position = 3)
    private String year;

    /**
     * 培养目标
     */
    @ApiModelProperty(value = "培养目标", position = 4)
    private String target;

    /**
     * 计划内容
     */
    @ApiModelProperty(value = "计划内容", position = 5)
    private String content;

    /**
     * 计划制定人
     */
    @ApiModelProperty(value = "计划制定人", position = 6)
    private String creator;

    /**
     * 计划制定时间
     */
    @ApiModelProperty(value = "计划制定时间", position = 7)
    private Date createTime;

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

    @Override
    public String toString() {
        return "CPTrainingPlanVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", year='" + year + '\'' +
                ", target='" + target + '\'' +
                ", content='" + content + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

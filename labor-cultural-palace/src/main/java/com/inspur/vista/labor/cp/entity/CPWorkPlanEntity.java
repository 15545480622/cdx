package com.inspur.vista.labor.cp.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inspur.vista.labor.cp.config.DateDeserializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPWorkPlanEntity
 * @Description: 文化宫工作计划实体类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 15:43
 */
public class CPWorkPlanEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 文化宫id
     */
    @NotNull(message = "文化宫id不能为空")
    private Long cpId;

    /**
     * 计划类型
     */
    @NotNull(message = "计划类型不能为空")
    private Integer planType;

    /**
     * 计划标题
     */
    @NotBlank(message = "计划标题不能为空")
    private String title;

    /**
     * 计划开始时间
     */
    @NotNull(message = "计划开始时间不能为空")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date planStartTime;

    /**
     * 计划结束时间
     */
    @NotNull(message = "计划结束时间不能为空")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date planEndTime;

    /**
     * 计划内容
     */
    @NotBlank(message = "计划内容不能为空")
    private String planContent;

    /**
     * 组织形式
     */
    @NotBlank(message = "组织形式不能为空")
    private String organizationalForm;

    /**
     * 预算
     */
    private Double budget;

    /**
     * 责任人
     */
    @NotBlank(message = "责任人不能为空")
    private String responsiblePerson;

    /**
     * 计划制定人
     */
    @NotBlank(message = "计划制定人不能为空")
    private String planner;

    /**
     * 总结
     */
    private String conclusion;

    /**
     * 总结人
     */
    private String conclusionPeople;

    /**
     * 总结时间
     */
    @JsonDeserialize(using = DateDeserializer.class)
    private Date conclusionTime;

    /**
     * 实际费用
     */
    private Double actualMoney;

    /**
     * 状态:1.有效;0.无效
     */
    private Integer state;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getOrganizationalForm() {
        return organizationalForm;
    }

    public void setOrganizationalForm(String organizationalForm) {
        this.organizationalForm = organizationalForm;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getPlanner() {
        return planner;
    }

    public void setPlanner(String planner) {
        this.planner = planner;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getConclusionPeople() {
        return conclusionPeople;
    }

    public void setConclusionPeople(String conclusionPeople) {
        this.conclusionPeople = conclusionPeople;
    }

    public java.util.Date getConclusionTime() {
        return conclusionTime;
    }

    public void setConclusionTime(java.util.Date conclusionTime) {
        this.conclusionTime = conclusionTime;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
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
        return "CPWorkPlanEntity{" +
                "id=" + id +
                ", cpId=" + cpId +
                ", planType=" + planType +
                ", title='" + title + '\'' +
                ", planStartTime=" + planStartTime +
                ", planEndTime=" + planEndTime +
                ", planContent='" + planContent + '\'' +
                ", organizationalForm='" + organizationalForm + '\'' +
                ", budget=" + budget +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                ", planner='" + planner + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", conclusionPeople='" + conclusionPeople + '\'' +
                ", conclusionTime=" + conclusionTime +
                ", actualMoney=" + actualMoney +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

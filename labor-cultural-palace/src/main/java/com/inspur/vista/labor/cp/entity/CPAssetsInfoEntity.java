package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPAssetsEntity
 * @Description: 文化宫资产
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
@ApiModel(value = "资产信息", description = "资产信息")
public class CPAssetsInfoEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 年度
     */
    @ApiModelProperty(value = "年度", required = true, example = "2020", position = 2)
    private String year;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", required = true, example = "", position = 3)
    private String cpId;


    /**
     * 资产情况-资产类
     */
    @ApiModelProperty(value = "资产情况-资产类，单位：万元", required = true, position = 4)
    private Double assets;

    /**
     * 资产情况-净资产类
     */
    @ApiModelProperty(value = "资产情况-净资产类，单位：万元", required = true, position = 5)
    private Double netAssets;

    /**
     * 资产情况-负债类
     */
    @ApiModelProperty(value = "资产情况-净资产类，单位：万元", required = true, position = 6)
    private Double debt;

    /**
     * 资产情况-收入类
     */
    @ApiModelProperty(value = "资产情况-收入类，单位：万元", required = true, position = 7)
    private Double income;

    /**
     * 资产情况-支出类
     */
    @ApiModelProperty(value = "资产情况-支出类，单位：万元", required = true, position = 8)
    private Double expenditure;

    /**
     * 状态:1.有效;0.无效
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer state;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public Double getAssets() {
        return assets;
    }

    public void setAssets(Double assets) {
        this.assets = assets;
    }

    public Double getNetAssets() {
        return netAssets;
    }

    public void setNetAssets(Double netAssets) {
        this.netAssets = netAssets;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
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
        return "CPAssetsInfoEntity{" +
                "id='" + id + '\'' +
                ", year='" + year + '\'' +
                ", cpId='" + cpId + '\'' +
                ", assets=" + assets +
                ", netAssets=" + netAssets +
                ", debt=" + debt +
                ", income=" + income +
                ", expenditure=" + expenditure +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
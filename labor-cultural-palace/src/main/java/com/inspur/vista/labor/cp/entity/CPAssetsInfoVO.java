package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPAssetsDetailVO
 * @Description: 文化宫资产VO
 * @authur: wangxueying01
 * @CreatDate: 2019/12/17 9:53
 */
@ApiModel(value = "资产详细信息", description = "资产详细信息")
public class CPAssetsInfoVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 年度
     */
    @ApiModelProperty(value = "年度", required = true, position = 2)
    private String year;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", required = true, position = 3)
    private String cpId;


    /**
     * 资产情况-资产类
     */
    @ApiModelProperty(value = "资产情况-资产类，单位：万元", position = 4)
    private Double assets;

    /**
     * 资产情况-净资产类
     */
    @ApiModelProperty(value = "资产情况-净资产类，单位：万元", position = 5)
    private Double netAssets;

    /**
     * 资产情况-负债类
     */
    @ApiModelProperty(value = "资产情况-净资产类，单位：万元", position = 6)
    private Double debt;

    /**
     * 资产情况-收入类
     */
    @ApiModelProperty(value = "资产情况-收入类，单位：万元", position = 7)
    private Double income;

    /**
     * 资产情况-支出类
     */
    @ApiModelProperty(value = "资产情况-支出类，单位：万元", position = 8)
    private Double expenditure;

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

    @Override
    public String toString() {
        return "CPAssetsInfoVO{" +
                "id='" + id + '\'' +
                ", year='" + year + '\'' +
                ", cpId='" + cpId + '\'' +
                ", assets=" + assets +
                ", netAssets=" + netAssets +
                ", debt=" + debt +
                ", income=" + income +
                ", expenditure=" + expenditure +
                '}';
    }
}

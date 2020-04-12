package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;

/**
 * @ClassName: CPKpisumMonthListVO
 * @Description: 文化宫月度统计VO
 * @authur: wangxueying01
 * @CreatDate: 2020/1/8 11:36
 */
public class CPKpisumMonthListVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 文化宫id
     */
    private Long cpId;

    /**
     * 用水能耗
     */
    private Double energyConsumWater;

    /**
     * 用电能耗
     */
    private Double energyConsumElectric;

    /**
     * 人流量
     */
    private Integer visitorsFlowrate;

    /**
     * 人流量饱和度
     */
    private Double visitorsFlowratePercent;

    /**
     * 活动数量
     */
    private Integer activityNum;

    /**
     * 场馆利用率
     */
    private Double venueUtilization;

    /**
     * 制定的计划数
     */
    private Integer planSumNum;

    /**
     * 计划已完成数
     */
    private Integer planDownNum;

    /**
     * 统计时间
     */
    private String statTime;

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

    public Double getEnergyConsumWater() {
        return energyConsumWater;
    }

    public void setEnergyConsumWater(Double energyConsumWater) {
        this.energyConsumWater = energyConsumWater;
    }

    public Double getEnergyConsumElectric() {
        return energyConsumElectric;
    }

    public void setEnergyConsumElectric(Double energyConsumElectric) {
        this.energyConsumElectric = energyConsumElectric;
    }

    public Integer getVisitorsFlowrate() {
        return visitorsFlowrate;
    }

    public void setVisitorsFlowrate(Integer visitorsFlowrate) {
        this.visitorsFlowrate = visitorsFlowrate;
    }

    public Double getVisitorsFlowratePercent() {
        return visitorsFlowratePercent;
    }

    public void setVisitorsFlowratePercent(Double visitorsFlowratePercent) {
        this.visitorsFlowratePercent = visitorsFlowratePercent;
    }

    public Integer getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(Integer activityNum) {
        this.activityNum = activityNum;
    }

    public Double getVenueUtilization() {
        return venueUtilization;
    }

    public void setVenueUtilization(Double venueUtilization) {
        this.venueUtilization = venueUtilization;
    }

    public Integer getPlanSumNum() {
        return planSumNum;
    }

    public void setPlanSumNum(Integer planSumNum) {
        this.planSumNum = planSumNum;
    }

    public Integer getPlanDownNum() {
        return planDownNum;
    }

    public void setPlanDownNum(Integer planDownNum) {
        this.planDownNum = planDownNum;
    }

    public String getStatTime() {
        return statTime;
    }

    public void setStatTime(String statTime) {
        this.statTime = statTime;
    }

    @Override
    public String toString() {
        return "CPKpisumMonthListVO{" +
                "id=" + id +
                ", cpId=" + cpId +
                ", energyConsumWater=" + energyConsumWater +
                ", energyConsumElectric=" + energyConsumElectric +
                ", visitorsFlowrate=" + visitorsFlowrate +
                ", visitorsFlowratePercent=" + visitorsFlowratePercent +
                ", activityNum=" + activityNum +
                ", venueUtilization=" + venueUtilization +
                ", planSumNum=" + planSumNum +
                ", planDownNum=" + planDownNum +
                ", statTime='" + statTime + '\'' +
                '}';
    }
}

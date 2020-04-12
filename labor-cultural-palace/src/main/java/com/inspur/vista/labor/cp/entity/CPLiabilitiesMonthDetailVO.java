package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;

/**
 * @ClassName: CPLiabilitiesMonthDetailVO
 * @Description: 月度资产负债VO
 * @authur: wangxueying01
 * @CreatDate: 2020/1/9 15:57
 */
public class CPLiabilitiesMonthDetailVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 文化宫id
     */
    private Long cpId;

    /**
     * 月份
     */
    private String month;

    /**
     * 资产填报项
     */
    private Long item;

    /**
     * 年初数
     */
    private Double earlyAmount;

    /**
     * 期末数
     */
    private Double endAmount;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public Double getEarlyAmount() {
        return earlyAmount;
    }

    public void setEarlyAmount(Double earlyAmount) {
        this.earlyAmount = earlyAmount;
    }

    public Double getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(Double endAmount) {
        this.endAmount = endAmount;
    }

    @Override
    public String toString() {
        return "CPLiabilitiesMonthDetailVO{" +
                "id=" + id +
                ", cpId=" + cpId +
                ", month='" + month + '\'' +
                ", item=" + item +
                ", earlyAmount=" + earlyAmount +
                ", endAmount=" + endAmount +
                '}';
    }
}

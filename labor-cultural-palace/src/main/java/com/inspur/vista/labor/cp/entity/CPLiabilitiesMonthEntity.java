package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPLiabilitiesMonthEntity
 * @Description: 月度资产负债实体类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/9 15:56
 */
public class CPLiabilitiesMonthEntity implements Serializable {

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
     * 资产填报项，字典项
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

    /**
     * 创建人
     */
    private String creator;

    /**
     * 最后更新人
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
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
        return "CPLiabilitiesMonthEntity{" +
                "id=" + id +
                ", cpId=" + cpId +
                ", month='" + month + '\'' +
                ", item=" + item +
                ", earlyAmount=" + earlyAmount +
                ", endAmount=" + endAmount +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

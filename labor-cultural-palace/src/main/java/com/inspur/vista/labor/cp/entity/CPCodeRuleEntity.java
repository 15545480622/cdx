package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Title: CPCodeRuleEntity
 * @Description: 文化宫组织编码
 * @Author: gengpeng
 * @CreateDate: 2020/4/7 13:35
 * @Version: 1.0
 */
public class CPCodeRuleEntity {

    /**
     * 区划编码
     */
    private String cantCode;

    /**
     * 文化宫编码前缀
     */
    private String prefixCpCode;

    /**
     * 当前值
     */
    private int currentValue;

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
     * 最后更新人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", hidden = true)
    private String remark;

    public String getCantCode() {
        return cantCode;
    }

    public void setCantCode(String cantCode) {
        this.cantCode = cantCode;
    }

    public String getPrefixCpCode() {
        return prefixCpCode;
    }

    public void setPrefixCpCode(String prefixCpCode) {
        this.prefixCpCode = prefixCpCode;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
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

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
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
        return "CPCodeRuleEntity{" +
                "cantCode='" + cantCode + '\'' +
                ", prefixCpCode='" + prefixCpCode + '\'' +
                ", currentValue=" + currentValue +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", modifier='" + modifier + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;

/**
 * @Title: CantVO
 * @Description: 行政区划VO
 * @Author: gengpeng
 * @CreateDate: 2018/11/28 17:38
 * @Version: 1.0
 */
public class CantVO implements Serializable {

    /**
     * 区划编码
     */
    private String cantCode;

    /**
     * 区划名称
     */
    private String cantName;

    /**
     * 区划简称
     */
    private String shortName;

    /**
     * 区划类型
     */
    private String cantType;

    /**
     * 上级区划编码
     */
    private String superCode;

    public String getCantCode() {
        return cantCode;
    }

    public void setCantCode(String cantCode) {
        this.cantCode = cantCode;
    }

    public String getCantName() {
        return cantName;
    }

    public void setCantName(String cantName) {
        this.cantName = cantName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCantType() {
        return cantType;
    }

    public void setCantType(String cantType) {
        this.cantType = cantType;
    }

    public String getSuperCode() {
        return superCode;
    }

    public void setSuperCode(String superCode) {
        this.superCode = superCode;
    }

    @Override
    public String toString() {
        return "CantVO{" +
                "cantCode='" + cantCode + '\'' +
                ", cantName='" + cantName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", cantType='" + cantType + '\'' +
                ", superCode='" + superCode + '\'' +
                '}';
    }
}

package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: OrganEntity
 * @Description: 工会组织实体类
 * @Author: gengpeng
 * @CreateDate: 2019/9/14 12:13
 * @Version: 1.0
 */
public class OrganEntity implements Serializable {

    /**
     * 工会Id
     */
    private String organId;

    /**
     * 工会名称
     */
    private String organName;

    /**
     * 工会编码
     */
    private String organCode;

    /**
     * 工会Id
     */
    private String struId;

    /**
     * 工会路径
     */
    private String struPath;

    /**
     * 工会层级
     */
    private int struLevel;

    /**
     * 工会排序
     */
    private int struOrder;

    /**
     * 工会类型
     */
    private String type;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否有效
     */
    private String inUse;

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getStruId() {
        return struId;
    }

    public void setStruId(String struId) {
        this.struId = struId;
    }

    public String getStruPath() {
        return struPath;
    }

    public void setStruPath(String struPath) {
        this.struPath = struPath;
    }

    public int getStruLevel() {
        return struLevel;
    }

    public void setStruLevel(int struLevel) {
        this.struLevel = struLevel;
    }

    public int getStruOrder() {
        return struOrder;
    }

    public void setStruOrder(int struOrder) {
        this.struOrder = struOrder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getInUse() {
        return inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse;
    }

    @Override
    public String toString() {
        return "OrganEntity{" +
                "organId='" + organId + '\'' +
                ", organName='" + organName + '\'' +
                ", organCode='" + organCode + '\'' +
                ", struId='" + struId + '\'' +
                ", struPath='" + struPath + '\'' +
                ", struLevel=" + struLevel +
                ", struOrder=" + struOrder +
                ", type='" + type + '\'' +
                ", updateTime=" + updateTime +
                ", inUse='" + inUse + '\'' +
                '}';
    }
}

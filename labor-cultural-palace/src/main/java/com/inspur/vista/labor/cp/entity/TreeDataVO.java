package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;

/**
 * @Title: TreeDataVO
 * @Description: 组织树
 * @Author: gengpeng
 * @CreateDate: 2019/2/20 12:57
 * @Version: 1.0
 */
public class TreeDataVO implements Serializable {

    /**
     * 工会Id
     */
    private String organId;

    /**
     * 工会组织名称
     */
    private String organName;

    /**
     * 组织类型
     */
    private String organType;

    /**
     * 上级组织Id
     */
    private String parentOrganId;

    /**
     * struId
     */
    private String struId;

    /**
     * 排序
     */
    private Integer struOrder;

    /**
     * 层级
     */
    private Integer struLevel;

    /**
     * 是否叶子节点
     */
    private String isLeaf;

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

    public String getOrganType() {
        return organType;
    }

    public void setOrganType(String organType) {
        this.organType = organType;
    }

    public String getStruId() {
        return struId;
    }

    public void setStruId(String struId) {
        this.struId = struId;
    }

    public String getParentOrganId() {
        return parentOrganId;
    }

    public void setParentOrganId(String parentOrganId) {
        this.parentOrganId = parentOrganId;
    }

    public Integer getStruOrder() {
        return struOrder;
    }

    public void setStruOrder(Integer struOrder) {
        this.struOrder = struOrder;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getStruLevel() {
        return struLevel;
    }

    public void setStruLevel(Integer struLevel) {
        this.struLevel = struLevel;
    }

    @Override
    public String toString() {
        return "TreeDataVO{" +
                "organId='" + organId + '\'' +
                ", organName='" + organName + '\'' +
                ", organType='" + organType + '\'' +
                ", parentOrganId='" + parentOrganId + '\'' +
                ", struId='" + struId + '\'' +
                ", struOrder=" + struOrder +
                ", struLevel=" + struLevel +
                ", isLeaf='" + isLeaf + '\'' +
                '}';
    }
}

package com.inspur.vista.labor.cp.entity;

/**
 * @Title: OrganCantEntity
 * @Description: 组织区划对应关系
 * @Author: gengpeng
 * @CreateDate: 2020/3/18 14:54
 * @Version: 1.0
 */
public class OrganCantEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 区划编码
     */
    private String cantCode;

    /**
     * 区划名称
     */
    private String cantName;

    /**
     * 组织编码
     */
    private String organCode;

    /**
     * 组织id
     */
    private String organId;

    /**
     * 组织名称
     */
    private String organName;

    /**
     * 是否有效
     */
    private String inUse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

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

    public String getInUse() {
        return inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse;
    }

    @Override
    public String toString() {
        return "OrganCantEntity{" +
                "id=" + id +
                ", cantCode='" + cantCode + '\'' +
                ", cantName='" + cantName + '\'' +
                ", organCode='" + organCode + '\'' +
                ", organId='" + organId + '\'' +
                ", organName='" + organName + '\'' +
                ", inUse='" + inUse + '\'' +
                '}';
    }
}

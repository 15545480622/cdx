package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 * 系统管理员信息
 */
public class UserAdminInfo implements Serializable {

    /**
     * 管理员Id
     */
    private String userId;
    /**
     * 管理员姓名
     */
    private String userName;
    /**
     * 管理员所在工会Id
     */
    private String laborId;
    /**
     * 管理员所在工会Code
     */
    private String laborCode;
    /**
     * 管理员所在工会名称
     */
    private String laborName;
    /**
     * 管理员所在工会类型
     * 20	虚拟部门
     * 21	省总工会
     * 22	地市总工会
     * 24	县市区总工会
     * 26	乡镇街道工会
     * 28	基层工会
     */
    private String laborType;
    /**
     * 管理员 管辖范围
     */
    private List<String> organIds;
    /**
     * 数据权限集合
     */
    private List<String> dataPermissionList;
    /**
     * 管理员功能权限
     */
    private Set<String> operations;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLaborId() {
        return laborId;
    }

    public void setLaborId(String laborId) {
        this.laborId = laborId;
    }

    public String getLaborCode() {
        return laborCode;
    }

    public void setLaborCode(String laborCode) {
        this.laborCode = laborCode;
    }

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public List<String> getOrganIds() {
        return organIds;
    }

    public void setOrganIds(List<String> organIds) {
        this.organIds = organIds;
    }

    public Set<String> getOperations() {
        return operations;
    }

    public void setOperations(Set<String> operations) {
        this.operations = operations;
    }

    public List<String> getDataPermissionList() {
        return dataPermissionList;
    }

    public void setDataPermissionList(List<String> dataPermissionList) {
        this.dataPermissionList = dataPermissionList;
    }

    public String getLaborType() {
        return laborType;
    }

    public void setLaborType(String laborType) {
        this.laborType = laborType;
    }

    @Override
    public String toString() {
        return "UserAdminInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", laborId='" + laborId + '\'' +
                ", laborCode='" + laborCode + '\'' +
                ", laborName='" + laborName + '\'' +
                ", laborType='" + laborType + '\'' +
                ", organIds=" + organIds +
                ", dataPermissionList=" + dataPermissionList +
                ", operations=" + operations +
                '}';
    }
}
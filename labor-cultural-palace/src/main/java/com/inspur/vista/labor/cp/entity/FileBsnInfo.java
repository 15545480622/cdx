package com.inspur.vista.labor.cp.entity;

/**
 * @Title: BsnInfoVO
 * @Description: 文件业务信息
 * @Author: gengpeng
 * @CreateDate: 2020/3/18 19:45
 * @Version: 1.0
 */
public class FileBsnInfo {

    /**
     * 业务类型
     */
    private String bsnType;

    /**
     * 业务id
     */
    private String bsnId;

    /**
     * 业务描述
     */
    private String bsnDesc;

    public String getBsnType() {
        return bsnType;
    }

    public void setBsnType(String bsnType) {
        this.bsnType = bsnType;
    }

    public String getBsnId() {
        return bsnId;
    }

    public void setBsnId(String bsnId) {
        this.bsnId = bsnId;
    }

    public String getBsnDesc() {
        return bsnDesc;
    }

    public void setBsnDesc(String bsnDesc) {
        this.bsnDesc = bsnDesc;
    }

    @Override
    public String toString() {
        return "BsnInfo{" +
                ", bsnType='" + bsnType + '\'' +
                ", bsnId='" + bsnId + '\'' +
                ", bsnDesc='" + bsnDesc + '\'' +
                '}';
    }
}

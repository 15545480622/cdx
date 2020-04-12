package com.inspur.vista.labor.cp.entity;

/**
 * @Title: AppUserInfo
 * @Description: app端用户信息
 * @Author: gengpeng
 * @CreateDate: 2020/3/30 14:08
 * @Version: 1.0
 */
public class AppUserInfo {

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 用户真实姓名
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 所属工会id
     */
    private String laborId;

    /**
     * 所属工会名称
     */
    private String laborName;

    /**
     * 工会所在区划
     */
    private String district;

    /**
     * 脱敏身份证
     */
    private String maskingIdNo;

    /**
     * 是否认证会员
     */
    private Boolean isAuth;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLaborId() {
        return laborId;
    }

    public void setLaborId(String laborId) {
        this.laborId = laborId;
    }

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMaskingIdNo() {
        return maskingIdNo;
    }

    public void setMaskingIdNo(String maskingIdNo) {
        this.maskingIdNo = maskingIdNo;
    }

    public Boolean getAuth() {
        return isAuth;
    }

    public void setAuth(Boolean auth) {
        isAuth = auth;
    }

    @Override
    public String toString() {
        return "AppUserInfo{" +
                "userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", laborId='" + laborId + '\'' +
                ", laborName='" + laborName + '\'' +
                ", district='" + district + '\'' +
                ", maskingIdNo='" + maskingIdNo + '\'' +
                ", isAuth=" + isAuth +
                '}';
    }
}

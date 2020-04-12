package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: CPReserveInfoEntity
 * @Description: 场地预约记录
 * @Author: gengpeng
 * @CreateDate: 2020/03/06 09:28
 * @Version: 1.0
 */
@ApiModel(value = "预定信息", description = "预定信息")
public class CPReserveAdd implements Serializable {


    /**
     * 用户编码，现场预约不需要填
     */
    @ApiModelProperty(value = "用户编码", hidden = true, position = 1)
    private String userCode;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", hidden = true, position = 2)
    private String userName;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户手机号", hidden = true, position = 3)
    private String userPhone;

    /**
     * 同伴电话
     */
    @ApiModelProperty(value = "同伴手机号(多个手机号用逗号分隔)", example = "18200000033", position = 4)
    private String companionPhone;

    /**
     * 所属工会id
     */
    @ApiModelProperty(value = "所属工会id，h5通过app获取，后台通过组织树获取", required = true, example = "O0000000000000000514", position = 5)
    private String laborId;

    /**
     * 所属工会名称
     */
    @ApiModelProperty(value = "所属工会名称", required = true, example = "PA工会", position = 6)
    private String laborName;

    /**
     * 预约方式，1.App；2.现场
     */
    @ApiModelProperty(value = "预约方式，1.App；2.现场", required = true, example = "1", position = 7)
    private String type;

    /**
     * 使用人数
     */
    @ApiModelProperty(value = "使用人数", required = true, example = "5", position = 8)
    private Integer personNum;

    /**
     * 预定的场地id
     */
    @ApiModelProperty(value = "预定的场地id", required = true, example = "5", position = 9)
    private String courtId;

    /**
     * 预定的场次
     */
    @ApiModelProperty(value = "预定的场次，格式为：yyyy-MM-dd HH:mm:ss_yyyy-MM-dd HH:mm:ss,开始时间和结束时间中间用下划线分隔，多个场次用逗号分隔", required = true, position = 10)
    private String scenes;

    /**
     * 签名
     */
    @ApiModelProperty(value = "签名，针对app端", required = true, position = 13)
    private String sign;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCompanionPhone() {
        return companionPhone;
    }

    public void setCompanionPhone(String companionPhone) {
        this.companionPhone = companionPhone;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getScenes() {
        return scenes;
    }

    public void setScenes(String scenes) {
        this.scenes = scenes;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "CPReserveAdd{" +
                "userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", companionPhone='" + companionPhone + '\'' +
                ", laborId='" + laborId + '\'' +
                ", laborName='" + laborName + '\'' +
                ", type='" + type + '\'' +
                ", personNum=" + personNum +
                ", courtId='" + courtId + '\'' +
                ", scenes='" + scenes + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
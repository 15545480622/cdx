package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPSecurityInfoVO
 * @Description: 安全管理详细信息
 * @authur: wangxueying01
 * @CreatDate: 2020/4/7 19:53
 */
@ApiModel(value = "安全管理详细信息", description = "安全管理详细信息")
public class CPSecurityInfoVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", position = 2)
    private String cpId;

    /**
     * 安全管理机构
     */
    @ApiModelProperty(value = "安全管理机构", position = 3)
    private String securityOrgan;

    /**
     * 安全管理人员
     */
    @ApiModelProperty(value = "安全管理人员", position = 4)
    private String securityUser;

    /**
     * 是否有应急预案:1.是;2.否
     */
    @ApiModelProperty(value = "是否有应急预案:1.是;2.否", position = 5)
    private Integer emergencyPlan;

    /**
     * 是否有组织演练:1.是;2.否
     */
    @ApiModelProperty(value = "是否有组织演练:1.是;2.否", position = 6)
    private Integer drill;

    /**
     * 是否有安全教育:1.是;2.否
     */
    @ApiModelProperty(value = "是否有安全教育:1.是;2.否", position = 7)
    private Integer securityEducation;

    /**
     * 是否有安全设施:1.是;2.否
     */
    @ApiModelProperty(value = "是否有安全设施:1.是;2.否", position = 8)
    private Integer securityFacilities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getSecurityOrgan() {
        return securityOrgan;
    }

    public void setSecurityOrgan(String securityOrgan) {
        this.securityOrgan = securityOrgan;
    }

    public String getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(String securityUser) {
        this.securityUser = securityUser;
    }

    public Integer getEmergencyPlan() {
        return emergencyPlan;
    }

    public void setEmergencyPlan(Integer emergencyPlan) {
        this.emergencyPlan = emergencyPlan;
    }

    public Integer getDrill() {
        return drill;
    }

    public void setDrill(Integer drill) {
        this.drill = drill;
    }

    public Integer getSecurityEducation() {
        return securityEducation;
    }

    public void setSecurityEducation(Integer securityEducation) {
        this.securityEducation = securityEducation;
    }

    public Integer getSecurityFacilities() {
        return securityFacilities;
    }

    public void setSecurityFacilities(Integer securityFacilities) {
        this.securityFacilities = securityFacilities;
    }

    @Override
    public String toString() {
        return "CPSecurityInfoVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", securityOrgan='" + securityOrgan + '\'' +
                ", securityUser='" + securityUser + '\'' +
                ", emergencyPlan=" + emergencyPlan +
                ", drill=" + drill +
                ", securityEducation=" + securityEducation +
                ", securityFacilities=" + securityFacilities +
                '}';
    }
}

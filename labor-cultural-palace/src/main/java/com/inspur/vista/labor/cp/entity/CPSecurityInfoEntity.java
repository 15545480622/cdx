package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPSecurityInfoEntity
 * @Description: 安全管理
 * @authur: wangxueying01
 * @CreatDate: 2020/4/7 19:53
 */
@ApiModel(value = "文化宫安全管理", description = "文化宫安全管理")
public class CPSecurityInfoEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", required = true, example = "a28f691d470441f88bb1f2ec2ecb965c", position = 2)
    private String cpId;

    /**
     * 安全管理机构
     */
    @ApiModelProperty(value = "安全管理机构", required = true, example = "", position = 3)
    private String securityOrgan;

    /**
     * 安全管理人员
     */
    @ApiModelProperty(value = "安全管理人员", required = true, example = "", position = 4)
    private String securityUser;

    /**
     * 是否有应急预案:1.是;2.否
     */
    @ApiModelProperty(value = "是否有应急预案:1.是;2.否", required = true, example = "1", position = 5)
    private Integer emergencyPlan;

    /**
     * 是否有组织演练:1.是;2.否
     */
    @ApiModelProperty(value = "是否有组织演练:1.是;2.否", required = true, example = "1", position = 6)
    private Integer drill;

    /**
     * 是否有安全教育:1.是;2.否
     */
    @ApiModelProperty(value = "是否有安全教育:1.是;2.否", required = true, example = "1", position = 7)
    private Integer securityEducation;

    /**
     * 是否有安全设施:1.是;2.否
     */
    @ApiModelProperty(value = "是否有安全设施:1.是;2.否", required = true, example = "1", position = 8)
    private Integer securityFacilities;

    /**
     * 状态:1.有效;0.无效
     */
    @ApiModelProperty(value = "状态:1.有效;0.无效", hidden = true)
    private Integer state;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", hidden = true)
    private String remark;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "CPSecurityInfoEntity{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", securityOrgan='" + securityOrgan + '\'' +
                ", securityUser='" + securityUser + '\'' +
                ", emergencyPlan=" + emergencyPlan +
                ", drill=" + drill +
                ", securityEducation=" + securityEducation +
                ", securityFacilities=" + securityFacilities +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

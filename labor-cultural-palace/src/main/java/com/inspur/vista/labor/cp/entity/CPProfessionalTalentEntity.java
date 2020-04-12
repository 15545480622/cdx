package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPProfessionalTalentEntity
 * @Description: 专业人才库
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
@ApiModel(value = "专业人才库", description = "专业人才库")
public class CPProfessionalTalentEntity implements Serializable {

    /**
     * 专业人才id
     */
    @ApiModelProperty(value = "id", required = true, position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", required = true, position = 2)
    private String cpId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true, position = 3)
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", required = true, position = 4)
    private String idcard;

    /**
     * 性别 M-男；F-女；Q-其他
     */
    @ApiModelProperty(value = "性别 M-男；F-女；Q-其他", required = true, position = 5)
    private String sex;

    /**
     * 民族，字典
     */
    @ApiModelProperty(value = "民族，字典，默认汉族，字典编码：ETHNIC_GROUP", required = true, position = 6)
    private String nation;

    /**
     * 学历，字典
     */
    @ApiModelProperty(value = "学历，字典,字典编码：EDUCATIONAL_TYPE", required = true, position = 7)
    private String education;

    /**
     * 政治面貌，字典
     */
    @ApiModelProperty(value = "政治面貌，字典,字典编码：POLITICAL_STAYUS", required = true, position = 8)
    private String politicalOutlook;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式", required = true, position = 9)
    private String phone;

    /**
     * 用工类别:1.编制；2.外聘；3.合同制
     */
    @ApiModelProperty(value = "用工类别:1.编制；2.外聘；3.合同制", required = true, position = 10)
    private Integer employmentType;

    /**
     * 专业人才类型：1.职工思想教育；2.业务培训；3、文艺；4、体育
     */
    @ApiModelProperty(value = "专业人才类型：1.职工思想教育；2.业务培训；3、文艺；4、体育;5、其他", required = true, position = 11)
    private Integer type;

    /**
     * 个人履历
     */
    @ApiModelProperty(value = "个人履历", required = true, position = 12)
    private String introduction;

    /**
     * 是否在编:1.是；0.否
     */
    @ApiModelProperty(value = "是否在编:1.是；0.否", required = true, position = 13)
    private Integer isStaffing;

    /**
     * 1.有效;0.无效
     */
    @ApiModelProperty(value = "状态:1.有效;0.无效", hidden = true)
    private Integer state;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 修改时间
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(Integer employmentType) {
        this.employmentType = employmentType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getIsStaffing() {
        return isStaffing;
    }

    public void setIsStaffing(Integer isStaffing) {
        this.isStaffing = isStaffing;
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
        return "CPProfessionalTalentEntity{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", education='" + education + '\'' +
                ", politicalOutlook='" + politicalOutlook + '\'' +
                ", phone='" + phone + '\'' +
                ", employmentType=" + employmentType +
                ", type=" + type +
                ", introduction='" + introduction + '\'' +
                ", isStaffing=" + isStaffing +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
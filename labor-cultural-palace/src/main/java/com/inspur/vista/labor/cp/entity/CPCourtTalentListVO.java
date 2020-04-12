package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPCourtTalentListVO
 * @Description: 场地专业人才关系ListVO
 * @authur: wangxueying01
 * @CreatDate: 2020/4/2 17:32
 */
@ApiModel(value = "场地专业人才关系列表信息", description = "场地专业人才关系列表信息")
public class CPCourtTalentListVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", position = 2)
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", position = 3)
    private String idcard;

    /**
     * 性别 M-男；F-女；Q-其他
     */
    @ApiModelProperty(value = "性别 M-男；F-女；Q-其他", position = 4)
    private String sex;

    /**
     * 民族
     */
    @ApiModelProperty(value = "民族", position = 5)
    private String nation;

    /**
     * 学历
     */
    @ApiModelProperty(value = "学历", position = 6)
    private String education;

    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌", position = 7)
    private String politicalOutlook;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式", position = 8)
    private String phone;

    /**
     * 用工类别：1.编制、2.外聘、3.合同制
     */
    @ApiModelProperty(value = "用工类别：1.编制、2.外聘、3.合同制", position = 9)
    private Integer employmentType;

    /**
     * 专业人才类型：1.宣传教育；2.工会业务；3、文艺；4、体育；5、后勤管理；6、其他
     */
    @ApiModelProperty(value = "专业人才类型：1.宣传教育；2.工会业务；3、文艺；4、体育；5、后勤管理；6、其他", position = 10)
    private Integer type;

    /**
     * 是否在编:1.是；0.否
     */
    @ApiModelProperty(value = "是否在编:1.是；0.否", position = 11)
    private Integer isStaffing;

    /**
     * 个人简介
     */
    @ApiModelProperty(value = "个人简介", position = 12)
    private String introduction;

    /**
     * 是否已关联:1.是 0.否
     */
    @ApiModelProperty(value = "是否已关联:1.是 0.否", position = 13)
    private Integer isRelated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public Integer getIsStaffing() {
        return isStaffing;
    }

    public void setIsStaffing(Integer isStaffing) {
        this.isStaffing = isStaffing;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getIsRelated() {
        return isRelated;
    }

    public void setIsRelated(Integer isRelated) {
        this.isRelated = isRelated;
    }

    @Override
    public String toString() {
        return "CPCourtTalentListVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", education='" + education + '\'' +
                ", politicalOutlook='" + politicalOutlook + '\'' +
                ", phone='" + phone + '\'' +
                ", employmentType=" + employmentType +
                ", type=" + type +
                ", isStaffing=" + isStaffing +
                ", introduction='" + introduction + '\'' +
                ", isRelated=" + isRelated +
                '}';
    }
}

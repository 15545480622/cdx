package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: CPStaffInfoListVO
 * @Description: 文化宫人员信息VO
 * @authur: wangxueying01
 * @CreatDate: 2019/12/17 9:27
 */
@ApiModel(value = "文化宫人员列表信息", description = "文化宫人员列表信息")
public class CPStaffInfoListVO implements Serializable {

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
     * 社工证书号码
     */
    @ApiModelProperty(value = "社工证书号码", position = 11)
    private String socialWorkerNumber;

    /**
     * 是否在岗：1.是；0.否
     */
    @ApiModelProperty(value = "是否在岗：1.是；0.否", required = true, position = 10)
    private Integer isOnjob;

    /**
     * 缺岗原因
     */
    @ApiModelProperty(value = "缺岗原因", position = 12)
    private String notOnjobReason;

    /**
     * 是否专业人才：1.是；0.否
     */
    @ApiModelProperty(value = "是否专业人才：1.是；0.否", required = true, position = 10)
    private Integer isProfessional;

    /**
     * 专业人才类型：1.宣传教育；2.工会业务；3、文艺；4、体育；5、后勤管理；6、其他
     */
    @ApiModelProperty(value = "专业人才类型：1.宣传教育；2.工会业务；3、文艺；4、体育；5、后勤管理；6、其他", required = true, position = 10)
    private Integer professionalType;

    /**
     * 专业人才是否在编:1.是；0.否
     */
    @ApiModelProperty(value = "专业人才是否在编:1.是；0.否", required = true, position = 10)
    private Integer isStaffing;

    /**
     * 专业人才简介
     */
    @ApiModelProperty(value = "专业人才简介", position = 13)
    private String introduction;

    /**
     * 管理员账号
     */
    @ApiModelProperty(value = "管理员账号", position = 13)
    private String managerUsername;

    /**
     * 管理员昵称
     */
    @ApiModelProperty(value = "管理员昵称", position = 14)
    private String managerNickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", required = true, position = 15)
    private String headAvatar;

    /**
     * 其他附件
     */
    @ApiModelProperty(value = "其他附件", required = true, position = 16)
    private List<String> otherFile;

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

    public String getSocialWorkerNumber() {
        return socialWorkerNumber;
    }

    public void setSocialWorkerNumber(String socialWorkerNumber) {
        this.socialWorkerNumber = socialWorkerNumber;
    }

    public Integer getIsOnjob() {
        return isOnjob;
    }

    public void setIsOnjob(Integer isOnjob) {
        this.isOnjob = isOnjob;
    }

    public String getNotOnjobReason() {
        return notOnjobReason;
    }

    public void setNotOnjobReason(String notOnjobReason) {
        this.notOnjobReason = notOnjobReason;
    }

    public Integer getIsProfessional() {
        return isProfessional;
    }

    public void setIsProfessional(Integer isProfessional) {
        this.isProfessional = isProfessional;
    }

    public Integer getProfessionalType() {
        return professionalType;
    }

    public void setProfessionalType(Integer professionalType) {
        this.professionalType = professionalType;
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

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerNickname() {
        return managerNickname;
    }

    public void setManagerNickname(String managerNickname) {
        this.managerNickname = managerNickname;
    }

    public String getHeadAvatar() {
        return headAvatar;
    }

    public void setHeadAvatar(String headAvatar) {
        this.headAvatar = headAvatar;
    }

    public List<String> getOtherFile() {
        return otherFile;
    }

    public void setOtherFile(List<String> otherFile) {
        this.otherFile = otherFile;
    }

    @Override
    public String toString() {
        return "CPStaffInfoListVO{" +
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
                ", socialWorkerNumber='" + socialWorkerNumber + '\'' +
                ", isOnjob=" + isOnjob +
                ", notOnjobReason='" + notOnjobReason + '\'' +
                ", isProfessional=" + isProfessional +
                ", professionalType=" + professionalType +
                ", isStaffing=" + isStaffing +
                ", introduction='" + introduction + '\'' +
                ", managerUsername='" + managerUsername + '\'' +
                ", managerNickname='" + managerNickname + '\'' +
                ", headAvatar='" + headAvatar + '\'' +
                ", otherFile=" + otherFile +
                '}';
    }
}

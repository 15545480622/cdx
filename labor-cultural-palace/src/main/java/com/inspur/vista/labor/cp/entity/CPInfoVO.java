package com.inspur.vista.labor.cp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPInfoEntity
 * @Description: 文化宫基本信息VO
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 16:15
 * @Version: 1.0
 */
@ApiModel(value = "文化宫VO信息", description = "文化宫VO信息")
public class CPInfoVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫名称
     */
    @ApiModelProperty(value = "名称", required = true, example = "测试文化宫", position = 2)
    private String name;

    /**
     * 是否工会资产，1.是；0.否
     */
    @ApiModelProperty(value = "是否工会资产1.是，0.否", required = true, example = "1", position = 3)
    private Integer isUnionAssets;

    /**
     * 是否独立核算，1.是；0.否
     */
    @ApiModelProperty(value = "是否独立核算,1.是，0.否", required = true, example = "1", position = 4)
    private Integer isAloneAccount;

    /**
     * 独立核算账号
     */
    @ApiModelProperty(value = "独立核算账号,是否独立核算选择是，此项必填", required = true, example = "3123213123213", position = 5)
    private String aloneAccount;

    /**
     * 主管工会级别，1.省级；2.市级；3.区县级
     */
    @ApiModelProperty(value = "主管工会级别，1.省级；2.市级；3.区县级", required = true, example = "3", position = 6)
    private Integer unionLevel;

    /**
     * 统一社会信用代码
     */
    @ApiModelProperty(value = "统一社会信用代码", required = true, example = "9137000016953055XK", position = 7)
    private String socialCreditCode;

    /**
     * 单位性质，1.公益一类 2.公益二类 3.公益三类
     */
    @ApiModelProperty(value = "单位性质，1.公益一类 2.公益二类 3.公益三类", required = true, example = "1", position = 8)
    private Integer unitNature;

    /**
     * 工人文化宫等级：1.一类，2.二类，3.三类
     */
    @ApiModelProperty(value = "工人文化宫等级：1.一类，2.二类，3.三类", required = true, example = "1", position = 9)
    private Integer level;

    /**
     * 服务设施，逗号分隔
     */
    @ApiModelProperty(value = "服务设施，逗号分隔", example = "", position = 10)
    private String serviceFacilities;

    /**
     * 负责人姓名
     */
    @ApiModelProperty(value = "负责人姓名", required = true, example = "张三", position = 11)
    private String officialsName;

    /**
     * 负责人联系方式
     */
    @ApiModelProperty(value = "负责人联系方式", required = true, example = "18211111111", position = 12)
    private String officialsPhone;

    /**
     * 成立时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "成立时间", required = true, example = "2010-04-01", position = 13)
    private Date establishDate;

    /**
     * 建成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "建成时间", required = true, example = "2010-04-01", position = 14)
    private Date buildDate;

    /**
     * 经营方式：1.自主经营，2.合作经营（合作项目）
     */
    @ApiModelProperty(value = "经营方式：1.自主经营，2.合作经营", example = "", position = 15)
    private Integer businessType;

    /**
     * 合作项目，如果经营方式为合作经营时填写
     */
    @ApiModelProperty(value = "合作项目，如果经营方式为合作经营时填写", example = "", position = 16)
    private String cooProject;

    /**
     * 是否全天
     */
    @ApiModelProperty(value = "是否全天", required = true, example = "1", position = 17)
    private Integer isAllDay;

    /**
     * 营业时间
     */
    @ApiModelProperty(value = "营业时间", required = true, example = "8:30-11:30,13:00-17:00;如果为全天则只需传一个时间段,如8:30-17:30", position = 18)
    private String businessTime;

    /**
     * 营业星期，逗号分隔
     */
    @ApiModelProperty(value = "营业星期，逗号分隔", required = true, example = "1,2", position = 19)
    private String businessWeek;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人", required = true, example = "王五", position = 20)
    private String contactName;

    /**
     * 联系人联系方式
     */
    @ApiModelProperty(value = "联系人的联系方式", required = true, example = "15622222222", position = 21)
    private String contactPhone;

    /**
     * 编制人员人数
     */
    @ApiModelProperty(value = "编制人员人数", required = true, example = "21", position = 22)
    private int staffingPeople;

    /**
     * 实有数
     */
    @ApiModelProperty(value = "实有数", required = true, example = "21", position = 23)
    private int realPeople;

    /**
     * 在岗在职人数
     */
    @ApiModelProperty(value = "在岗在职人数", required = true, example = "21", position = 24)
    private int onjobPeople;

    /**
     * 外聘人员人数
     */
    @ApiModelProperty(value = "外聘人员人数", required = true, example = "10", position = 25)
    private int externalPeople;

    /**
     * 离退休人员人数
     */
    @ApiModelProperty(value = "离退休人员人数", required = true, example = "5", position = 26)
    private int retirePeople;

    /**
     * 停车场车位数
     */
    @ApiModelProperty(value = "停车场车位数", required = true, example = "30", position = 27)
    private int parkNum;

    /**
     * 业务范围
     */
    @ApiModelProperty(value = "业务范围", required = true, example = "1、文艺演出组织；2、美术书法摄影展览；3、影视录像放映；4、文体比赛组织", position = 28)
    private String businessRange;

    /**
     * 所在区划编码
     */
    @ApiModelProperty(value = "所在区划编码", required = true, example = "370102", position = 29)
    private String district;

    /**
     * 所在地址
     */
    @ApiModelProperty(value = "所在地址", required = true, example = "浪潮科技园", position = 30)
    private String address;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", required = true, example = "湖州市工人文化宫是市总工会下属公益性事业单位，是全市职工文化活动的重要场所。市工人文化宫新大楼设有中影·工人文化宫影城、职工图书馆（多媒体电子阅览室）、乒乓球馆、职工大讲堂、职工大舞台、培训室、排练厅、展览厅、职工文体协会活动室等。是我市职工工会会员参与文化、娱乐、体育、休闲活动的好去处和场所。", position = 31)
    private String introduction;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", required = true, example = "117.024967", position = 32)
    private Double lng;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", required = true, example = "36.682785", position = 33)
    private Double lat;

    /**
     * 占地面积(平方米)
     */
    @ApiModelProperty(value = "占地面积(平方米)", required = true, example = "1500", position = 34)
    private Double area;

    /**
     * 建筑面积(平方米)
     */
    @ApiModelProperty(value = "建筑面积(平方米)", required = true, example = "1200", position = 35)
    private Double constructionArea;

    /**
     * 对外出租承包建筑面积(平方米)
     */
    @ApiModelProperty(value = "对外出租承包建筑面积(平方米)", required = true, example = "500", position = 36)
    private Double leasedConstructionArea;

    /**
     * 可容纳人数
     */
    @ApiModelProperty(value = "可容纳人数", required = true, example = "1000", position = 37)
    private int capacity;

    /**
     * 所属文化宫组织id
     */
    @ApiModelProperty(value = "", hidden = true)
    private String organId;

    /**
     * 文化宫组织编码
     */
    @ApiModelProperty(value = "", hidden = true)
    private String organCode;

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

    public Integer getIsUnionAssets() {
        return isUnionAssets;
    }

    public void setIsUnionAssets(Integer isUnionAssets) {
        this.isUnionAssets = isUnionAssets;
    }

    public Integer getIsAloneAccount() {
        return isAloneAccount;
    }

    public void setIsAloneAccount(Integer isAloneAccount) {
        this.isAloneAccount = isAloneAccount;
    }

    public String getAloneAccount() {
        return aloneAccount;
    }

    public void setAloneAccount(String aloneAccount) {
        this.aloneAccount = aloneAccount;
    }

    public Integer getUnionLevel() {
        return unionLevel;
    }

    public void setUnionLevel(Integer unionLevel) {
        this.unionLevel = unionLevel;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public Integer getUnitNature() {
        return unitNature;
    }

    public void setUnitNature(Integer unitNature) {
        this.unitNature = unitNature;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getServiceFacilities() {
        return serviceFacilities;
    }

    public void setServiceFacilities(String serviceFacilities) {
        this.serviceFacilities = serviceFacilities;
    }

    public String getOfficialsName() {
        return officialsName;
    }

    public void setOfficialsName(String officialsName) {
        this.officialsName = officialsName;
    }

    public String getOfficialsPhone() {
        return officialsPhone;
    }

    public void setOfficialsPhone(String officialsPhone) {
        this.officialsPhone = officialsPhone;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getCooProject() {
        return cooProject;
    }

    public void setCooProject(String cooProject) {
        this.cooProject = cooProject;
    }

    public Integer getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(Integer isAllDay) {
        this.isAllDay = isAllDay;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getBusinessWeek() {
        return businessWeek;
    }

    public void setBusinessWeek(String businessWeek) {
        this.businessWeek = businessWeek;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getStaffingPeople() {
        return staffingPeople;
    }

    public void setStaffingPeople(int staffingPeople) {
        this.staffingPeople = staffingPeople;
    }

    public int getRealPeople() {
        return realPeople;
    }

    public void setRealPeople(int realPeople) {
        this.realPeople = realPeople;
    }

    public int getOnjobPeople() {
        return onjobPeople;
    }

    public void setOnjobPeople(int onjobPeople) {
        this.onjobPeople = onjobPeople;
    }

    public int getExternalPeople() {
        return externalPeople;
    }

    public void setExternalPeople(int externalPeople) {
        this.externalPeople = externalPeople;
    }

    public int getRetirePeople() {
        return retirePeople;
    }

    public void setRetirePeople(int retirePeople) {
        this.retirePeople = retirePeople;
    }

    public int getParkNum() {
        return parkNum;
    }

    public void setParkNum(int parkNum) {
        this.parkNum = parkNum;
    }

    public String getBusinessRange() {
        return businessRange;
    }

    public void setBusinessRange(String businessRange) {
        this.businessRange = businessRange;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(Double constructionArea) {
        this.constructionArea = constructionArea;
    }

    public Double getLeasedConstructionArea() {
        return leasedConstructionArea;
    }

    public void setLeasedConstructionArea(Double leasedConstructionArea) {
        this.leasedConstructionArea = leasedConstructionArea;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    @Override
    public String toString() {
        return "CPInfoVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isUnionAssets=" + isUnionAssets +
                ", isAloneAccount=" + isAloneAccount +
                ", aloneAccount='" + aloneAccount + '\'' +
                ", unionLevel=" + unionLevel +
                ", socialCreditCode='" + socialCreditCode + '\'' +
                ", unitNature=" + unitNature +
                ", level=" + level +
                ", serviceFacilities='" + serviceFacilities + '\'' +
                ", officialsName='" + officialsName + '\'' +
                ", officialsPhone='" + officialsPhone + '\'' +
                ", establishDate=" + establishDate +
                ", buildDate=" + buildDate +
                ", businessType=" + businessType +
                ", cooProject='" + cooProject + '\'' +
                ", isAllDay=" + isAllDay +
                ", businessTime='" + businessTime + '\'' +
                ", businessWeek='" + businessWeek + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", staffingPeople=" + staffingPeople +
                ", realPeople=" + realPeople +
                ", onjobPeople=" + onjobPeople +
                ", externalPeople=" + externalPeople +
                ", retirePeople=" + retirePeople +
                ", parkNum=" + parkNum +
                ", businessRange='" + businessRange + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", introduction='" + introduction + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", area=" + area +
                ", constructionArea=" + constructionArea +
                ", leasedConstructionArea=" + leasedConstructionArea +
                ", capacity=" + capacity +
                ", organId='" + organId + '\'' +
                ", organCode='" + organCode + '\'' +
                '}';
    }
}

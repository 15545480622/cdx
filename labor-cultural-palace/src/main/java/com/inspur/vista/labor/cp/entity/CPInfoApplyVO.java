package com.inspur.vista.labor.cp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: CPInfoEntity
 * @Description: 文化宫基本信息VO
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 16:15
 * @Version: 1.0
 */
@ApiModel(value = "文化宫VO信息", description = "文化宫VO信息")
public class CPInfoApplyVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * cpId
     */
    @ApiModelProperty(value = "文化宫id", position = 2)
    private String cpId;

    /**
     * 文化宫名称
     */
    @ApiModelProperty(value = "名称", position = 3)
    private String name;

    /**
     * 是否工会资产，1.是；0.否
     */
    @ApiModelProperty(value = "是否工会资产1.是，0.否", position = 4)
    private Integer isUnionAssets;

    /**
     * 是否独立核算，1.是；0.否
     */
    @ApiModelProperty(value = "是否独立核算,1.是，0.否", position = 5)
    private Integer isAloneAccount;

    /**
     * 独立核算账号
     */
    @ApiModelProperty(value = "独立核算账号,是否独立核算选择是，此项必填", position = 6)
    private String aloneAccount;

    /**
     * 主管工会级别，1.省级；2.市级；3.区县级
     */
    @ApiModelProperty(value = "主管工会级别，1.省级；2.市级；3.区县级", position = 7)
    private Integer unionLevel;

    /**
     * 统一社会信用代码
     */
    @ApiModelProperty(value = "统一社会信用代码", position = 8)
    private String socialCreditCode;

    /**
     * 公益类型，1.公益一类 2.公益二类 3.公益三类
     */
    @ApiModelProperty(value = "公益类型，1.公益一类 2.公益二类 3.公益三类", position = 9)
    private Integer publicWelfareType;

    /**
     * 服务设施，逗号分隔
     */
    @ApiModelProperty(value = "服务设施，逗号分隔", position = 10)
    private String serviceFacilities;

    /**
     * 负责人姓名
     */
    @ApiModelProperty(value = "负责人姓名", position = 11)
    private String officialsName;

    /**
     * 负责人联系方式
     */
    @ApiModelProperty(value = "负责人联系方式", position = 12)
    private String officialsPhone;

    /**
     * 成立时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "成立时间", position = 13)
    private Date establishDate;

    /**
     * 营业时间
     */
    @ApiModelProperty(value = "营业时间", position = 14)
    private String businessTime;

    /**
     * 是否全天
     */
    @ApiModelProperty(value = "是否全天", position = 15)
    private Integer isAllDay;

    /**
     * 营业星期，逗号分隔
     */
    @ApiModelProperty(value = "营业星期，逗号分隔", position = 16)
    private String businessWeek;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人", position = 17)
    private String contactName;

    /**
     * 联系人联系方式
     */
    @ApiModelProperty(value = "联系人的联系方式", position = 18)
    private String contactPhone;

    /**
     * 编制人员人数
     */
    @ApiModelProperty(value = "编制人员人数", position = 19)
    private int staffingPeople;

    /**
     * 实有数
     */
    @ApiModelProperty(value = "实有数", position = 20)
    private int realPeople;

    /**
     * 在岗在职人数
     */
    @ApiModelProperty(value = "在岗在职人数", position = 21)
    private int onjobPeople;

    /**
     * 外聘人员人数
     */
    @ApiModelProperty(value = "外聘人员人数", position = 22)
    private int externalPeople;

    /**
     * 离退休人员人数
     */
    @ApiModelProperty(value = "离退休人员人数", position = 23)
    private int retirePeople;

    /**
     * 停车场车位数
     */
    @ApiModelProperty(value = "停车场车位数", position = 24)
    private int parkNum;

    /**
     * 业务范围
     */
    @ApiModelProperty(value = "业务范围", position = 24)
    private String businessRange;

    /**
     * 所在区划编码
     */
    @ApiModelProperty(value = "所在区划编码", position = 25)
    private String district;

    /**
     * 所在地址
     */
    @ApiModelProperty(value = "所在地址", position = 26)
    private String address;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", position = 27)
    private String introduction;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", position = 28)
    private Double lng;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", position = 29)
    private Double lat;

    /**
     * 占地面积(平方米)
     */
    @ApiModelProperty(value = "占地面积(平方米)", position = 30)
    private Double area;

    /**
     * 建筑面积(平方米)
     */
    @ApiModelProperty(value = "建筑面积(平方米)", position = 31)
    private Double constructionArea;

    /**
     * 对外出租承包建筑面积(平方米)
     */
    @ApiModelProperty(value = "对外出租承包建筑面积(平方米)", position = 32)
    private Double leasedConstructionArea;

    /**
     * 可容纳人数
     */
    @ApiModelProperty(value = "可容纳人数", position = 33)
    private int capacity;

    /**
     * 所在工会Id
     */
    @ApiModelProperty(value = "所在工会Id", position = 34)
    private String laborId;

    /**
     * 工会名称
     */
    @ApiModelProperty(value = "所属工会", position = 35)
    private String laborName;

    /**
     * 所在工会Id
     */
    @ApiModelProperty(value = "申请表的附件", position = 36)
    private List<CPApplyFileVO> appFileList;


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

    public Integer getPublicWelfareType() {
        return publicWelfareType;
    }

    public void setPublicWelfareType(Integer publicWelfareType) {
        this.publicWelfareType = publicWelfareType;
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

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public Integer getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(Integer isAllDay) {
        this.isAllDay = isAllDay;
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

    public List<CPApplyFileVO> getAppFileList() {
        return appFileList;
    }

    public void setAppFileList(List<CPApplyFileVO> appFileList) {
        this.appFileList = appFileList;
    }

    @Override
    public String toString() {
        return "CPInfoApplyVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", name='" + name + '\'' +
                ", isUnionAssets=" + isUnionAssets +
                ", isAloneAccount=" + isAloneAccount +
                ", aloneAccount='" + aloneAccount + '\'' +
                ", unionLevel=" + unionLevel +
                ", socialCreditCode='" + socialCreditCode + '\'' +
                ", publicWelfareType=" + publicWelfareType +
                ", serviceFacilities='" + serviceFacilities + '\'' +
                ", officialsName='" + officialsName + '\'' +
                ", officialsPhone='" + officialsPhone + '\'' +
                ", establishDate=" + establishDate +
                ", businessTime='" + businessTime + '\'' +
                ", isAllDay=" + isAllDay +
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
                ", laborId='" + laborId + '\'' +
                ", laborName='" + laborName + '\'' +
                ", appFileList=" + appFileList +
                '}';
    }
}

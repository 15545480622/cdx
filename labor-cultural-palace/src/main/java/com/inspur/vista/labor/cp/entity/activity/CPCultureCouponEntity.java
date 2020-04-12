package com.inspur.vista.labor.cp.entity.activity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inspur.vista.labor.cp.config.DateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: CPCultureCouponEntity
 * @Description: 文化宫优惠券信息
 * @Author: ljs
 * @CreateDate: 2020/03/25 10:47
 * @Version: 1.0
 */
//@ApiModel(value = "文化宫优惠券信息", description = "文化宫优惠券信息")
@TableName("cp_activity_coupon")
public class CPCultureCouponEntity implements Serializable {

    //id
    //@ApiModelProperty(value = "id", example = "", position = 1)
    @TableId
    private Long id;

    //优惠券名称
    @TableField("name")
    private String name;

    //优惠券数量
    @TableField("count")
    private Integer count;

    //使用说明
    @TableField("instruction")
    private String instruction;

    //优惠券使用说明
    @TableField("brief")
    private String brief;

    //有效期开始时间
    @ApiModelProperty(value = "有效期开始时间", hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("start_time")
    private Date startTime;

    //有效期结束时间
    @ApiModelProperty(value = "有效期结束时间", hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("end_time")
    private Date endTime;

    //优惠券金额
    @TableField("amount")
    private BigDecimal amount;

    //券类型(1:商家活动券，2:工会活动券) 暂不明确
    @TableField("type")
    private String type;

    //文化宫Id
    @TableField("culture_id")
    private String cultureId;

    //文化宫名字
    @TableField("culture_name")
    private String cultureName;

    //文化宫logo
    @TableField("culture_logo")
    private String cultureLogo;

    //文化宫地址
    @TableField("culture_address")
    private String cultureAddress;

    //文化宫电话
    @TableField("culture_phone")
    private String culturePhone;

    //是否上架（0：未上架；1：上架）
    @TableField("status")
    private Integer status;

    //文化宫所属工会code
    @TableField("labor_code")
    private String laborCode;

    //文化宫所属工会名称
    @TableField("labor_name")
    private String laborName;

    //文化宫所属工会ID
    @TableField("labor_id")
    private String laborId;

    //操作者Id
    @TableField("operator_id")
    private String operatorId;

    //操作者姓名
    @TableField("operator_name")
    private String operatorName;

    //0:未删除；1：删除
    @TableLogic
    @TableField("deleted")
    private Integer deleted;

    //创建时间
    @ApiModelProperty(value = "创建时间", hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    //优惠券名
    @TableField("coupon_name")
    private String couponName;

    //商家券每天领取几次，0只能1次
    @TableField("receive_num")
    private Integer receiveNum;

    //0未开启，1开启，2关闭
    @TableField("turn")
    private Integer turn;

    //优惠券总量
    @TableField("total_count")
    private Integer totalCount;

    //是否过期 0未开始 1未过期 2已过期
    @TableField(exist = false)
    private Integer isPastDue;

    //活动名
    @TableField(exist = false)
    private String activityName;

    //活动ID
    @TableField(exist = false)
    private Integer activityId;

    //活动类型
    @TableField(exist = false)
    private String activityType;

    //卡券总数
    //private Integer couponCount;

    //已领取数量
    @TableField(exist = false)
    private Integer getCount;

    //已兑换数量
    @TableField(exist = false)
    private Integer conversionCount;

    //活动限制优惠券数量
    @TableField(exist = false)
    private Integer limitCouponCount;


    public Integer getLimitCouponCount() {
        return limitCouponCount;
    }

    public void setLimitCouponCount(Integer limitCouponCount) {
        this.limitCouponCount = limitCouponCount;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getGetCount() {
        return getCount;
    }

    public void setGetCount(Integer getCount) {
        this.getCount = getCount;
    }

    public Integer getConversionCount() {
        return conversionCount;
    }

    public void setConversionCount(Integer conversionCount) {
        this.conversionCount = conversionCount;
    }

    public Integer getIsPastDue() {
        return isPastDue;
    }

    public void setIsPastDue(Integer isPastDue) {
        this.isPastDue = isPastDue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getCultureId() {
        return cultureId;
    }

    public void setCultureId(String cultureId) {
        this.cultureId = cultureId;
    }

    public String getCultureName() {
        return cultureName;
    }

    public void setCultureName(String cultureName) {
        this.cultureName = cultureName;
    }

    public String getCultureLogo() {
        return cultureLogo;
    }

    public void setCultureLogo(String cultureLogo) {
        this.cultureLogo = cultureLogo;
    }

    public String getCultureAddress() {
        return cultureAddress;
    }

    public void setCultureAddress(String cultureAddress) {
        this.cultureAddress = cultureAddress;
    }

    public String getCulturePhone() {
        return culturePhone;
    }

    public void setCulturePhone(String culturePhone) {
        this.culturePhone = culturePhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLaborCode() {
        return laborCode;
    }

    public void setLaborCode(String laborCode) {
        this.laborCode = laborCode;
    }

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public String getLaborId() {
        return laborId;
    }

    public void setLaborId(String laborId) {
        this.laborId = laborId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(Integer receiveNum) {
        this.receiveNum = receiveNum;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}

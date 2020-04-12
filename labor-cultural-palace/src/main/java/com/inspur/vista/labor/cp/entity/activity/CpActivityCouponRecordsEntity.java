package com.inspur.vista.labor.cp.entity.activity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 券使用记录
 *
 * @author
 * @email
 * @date 2020-03-27 10:38:38
 */
@TableName("cp_activity_coupon_records")
public class CpActivityCouponRecordsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     *
     */
	@TableId
	private Integer id;
    /**
     * 券Id
     */
	private Integer couponId;
    /**
     * 用户Id
     */
	private String userId;
    /**
     * 用户名称
     */
	private String userName;
    /**
     * 用户所在工会Id
     */
	private String laborId;
    /**
     * 用户所在工会名称
     */
	private String laborName;
    /**
     * 状态 （0：未使用， 1：已使用， 2：过期）
     */
	private String status;
    /**
     * 使用时间
     */
	private Date useTime;
    /**
     * 领取时间
     */
	private Date createTime;
    /**
     * 用户身份证号(脱敏)
     */
	private String userCardNum;
    /**
     * 行政区划码
     */
	private String districtCode;
    /**
     * 行政区划名字
     */
	private String districtName;
    /**
     * 用户手机号
     */
	private String userPhone;
    /**
     * 活动Id
     */
	private Integer activityId;

	/**
	 * 扫码者id
	 */
	private String scanUserId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：券Id
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：券Id
	 */
	public Integer getCouponId() {
		return couponId;
	}

	/**
	 * 设置：用户Id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户Id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名称
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：用户所在工会Id
	 */
	public void setLaborId(String laborId) {
		this.laborId = laborId;
	}
	/**
	 * 获取：用户所在工会Id
	 */
	public String getLaborId() {
		return laborId;
	}
	/**
	 * 设置：用户所在工会名称
	 */
	public void setLaborName(String laborName) {
		this.laborName = laborName;
	}
	/**
	 * 获取：用户所在工会名称
	 */
	public String getLaborName() {
		return laborName;
	}
	/**
	 * 设置：状态 （0：未使用， 1：已使用， 2：过期）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态 （0：未使用， 1：已使用， 2：过期）
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：使用时间
	 */
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	/**
	 * 获取：使用时间
	 */
	public Date getUseTime() {
		return useTime;
	}
	/**
	 * 设置：领取时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：领取时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：用户身份证号(脱敏)
	 */
	public void setUserCardNum(String userCardNum) {
		this.userCardNum = userCardNum;
	}
	/**
	 * 获取：用户身份证号(脱敏)
	 */
	public String getUserCardNum() {
		return userCardNum;
	}
	/**
	 * 设置：行政区划码
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * 获取：行政区划码
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * 设置：行政区划名字
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * 获取：行政区划名字
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * 设置：用户手机号
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	/**
	 * 获取：用户手机号
	 */
	public String getUserPhone() {
		return userPhone;
	}
	/**
	 * 设置：活动Id
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动Id
	 */
	public Integer getActivityId() {
		return activityId;
	}

	public String getScanUserId() {
		return scanUserId;
	}

	public void setScanUserId(String scanUserId) {
		this.scanUserId = scanUserId;
	}
}

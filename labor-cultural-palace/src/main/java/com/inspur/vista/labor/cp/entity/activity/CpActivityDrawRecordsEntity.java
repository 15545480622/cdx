package com.inspur.vista.labor.cp.entity.activity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;



/**
 * 抽奖记录表
 * 
 * @author 
 * @email 
 * @date 2020-04-01 10:06:47
 */
@TableName("cp_activity_draw_records")
public class CpActivityDrawRecordsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 
     */
	@TableId
	private Integer id;
    /**
     * 用户Id
     */
	private String userId;
    /**
     * 用户真实名
     */
	private String userName;
    /**
     * 用户手机号
     */
	private String userPhone;
    /**
     * 用户所在工会id
     */
	private String laborId;
    /**
     * 用户所在工会名称
     */
	private String laborName;
    /**
     * 奖品Id
     */
	private Integer goodsId;
    /**
     * 活动Id
     */
	private Integer activityId;
    /**
     * 行政区划编码
     */
	private String districtCode;
    /**
     * 行政区划名称
     */
	private String districtName;
    /**
     * 
     */
	private Date createTime;

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
	 * 设置：用户真实名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户真实名
	 */
	public String getUserName() {
		return userName;
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
	 * 设置：用户所在工会id
	 */
	public void setLaborId(String laborId) {
		this.laborId = laborId;
	}
	/**
	 * 获取：用户所在工会id
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
	 * 设置：奖品Id
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：奖品Id
	 */
	public Integer getGoodsId() {
		return goodsId;
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
	/**
	 * 设置：行政区划编码
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * 获取：行政区划编码
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * 设置：行政区划名称
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * 获取：行政区划名称
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}

package com.inspur.vista.labor.cp.entity.activity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;



/**
 * 报名参与记录表
 * 
 * @author 
 * @email 
 * @date 2020-03-18 14:28:24
 */
@TableName("cp_activity_join_records")
@ApiModel
public class CpActivityJoinRecordsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 
     */
	@TableId
	private Integer id;
    /**
     * 活动Id
     */
    @ApiModelProperty(value = "活动id")
	private Integer activityId;
    /**
     * 用户id
     */
	@ApiModelProperty(value = "会员id")
	private String userId;
    /**
     * 用户名
     */
	@ApiModelProperty(value = "用户真实姓名")
	private String userName;
    /**
     * 用户手机号
     */
	@ApiModelProperty(value = "用户手机号")
	private String userPhone;
    /**
     * 工会id
     */
	@ApiModelProperty(value = "用户工会id")
	private String laborId;
    /**
     * 用户所在工会名称
     */
	@ApiModelProperty(value = "用户所在工会名称")
	private String laborName;
    /**
     * 行政区划码
     */
	@ApiModelProperty(value = "行政区划码")
	private String districtCode;
    /**
     * 行政区划
     */
	@ApiModelProperty(value = "行政区划")
	private String districtName;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 是否中奖 0:未中奖；1:中奖
     */
	private Integer status;

	/**
	 * 是否使用
	 * 0未使用，1已使用
	 */
	private Integer isUse;

	/**
	 * 用户答题表单
	 */
	private String recordJson;

	/**
	 * 团体报名操作者id
	 */
	private String operatorId;

	/**
	 * 扫码用户id
	 */
	private String scanUserId;

	/**
	 * 扫码时间
	 */
	private Date scanTime;

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
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
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
	 * 设置：工会id
	 */
	public void setLaborId(String laborId) {
		this.laborId = laborId;
	}
	/**
	 * 获取：工会id
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
	 * 设置：行政区划
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * 获取：行政区划
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：是否中奖 0:未中奖；1:中奖
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：是否中奖 0:未中奖；1:中奖
	 */
	public Integer getStatus() {
		return status;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getRecordJson() {
		return recordJson;
	}

	public void setRecordJson(String recordJson) {
		this.recordJson = recordJson;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Date getScanTime() {
		return scanTime;
	}

	public void setScanTime(Date scanTime) {
		this.scanTime = scanTime;
	}

	public String getScanUserId() {
		return scanUserId;
	}

	public void setScanUserId(String scanUserId) {
		this.scanUserId = scanUserId;
	}
}

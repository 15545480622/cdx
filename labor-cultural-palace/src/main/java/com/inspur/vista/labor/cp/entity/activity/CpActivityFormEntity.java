package com.inspur.vista.labor.cp.entity.activity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;



/**
 * 报名活动表单
 * 
 * @author 
 * @email 
 * @date 2020-03-18 14:28:24
 */
@TableName("cp_activity_form")
public class CpActivityFormEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 
     */
	@TableId
	private Integer id;
    /**
     * form表单内容
     */
	private String contentJson;
    /**
     * 创建者id
     */
	private String userId;
    /**
     * 创建者工会id
     */
	private String laborId;
    /**
     * 工会code
     */
	private String laborCode;
    /**
     * 工会name
     */
	private String laborName;
    /**
     * 活动id
     */
	private Integer activityId;
    /**
     * 创建时间
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
	 * 设置：form表单内容
	 */
	public void setContentJson(String contentJson) {
		this.contentJson = contentJson;
	}
	/**
	 * 获取：form表单内容
	 */
	public String getContentJson() {
		return contentJson;
	}
	/**
	 * 设置：创建者id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：创建者id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：创建者工会id
	 */
	public void setLaborId(String laborId) {
		this.laborId = laborId;
	}
	/**
	 * 获取：创建者工会id
	 */
	public String getLaborId() {
		return laborId;
	}
	/**
	 * 设置：工会code
	 */
	public void setLaborCode(String laborCode) {
		this.laborCode = laborCode;
	}
	/**
	 * 获取：工会code
	 */
	public String getLaborCode() {
		return laborCode;
	}
	/**
	 * 设置：工会name
	 */
	public void setLaborName(String laborName) {
		this.laborName = laborName;
	}
	/**
	 * 获取：工会name
	 */
	public String getLaborName() {
		return laborName;
	}
	/**
	 * 设置：活动id
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动id
	 */
	public Integer getActivityId() {
		return activityId;
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
}

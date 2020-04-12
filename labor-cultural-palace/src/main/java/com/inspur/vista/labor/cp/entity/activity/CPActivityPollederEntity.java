package com.inspur.vista.labor.cp.entity.activity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPCultureCouponEntity
 * @Description: 文化宫活动被投票对象信息
 * @Author: ljs
 * @CreateDate: 2020-03-31 10:39:43
 * @Version: 1.0
 */
@TableName("cp_activity_polleder")
public class CPActivityPollederEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 编号
	 */
	private String no;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 简介
	 */
	private String brief;
	/**
	 * 头像
	 */
	private String headImg;
	/**
	 * 获取投票数
	 */
	private Integer votes;
	/**
	 * 所属活动Id
	 */
	private Integer activityId;
	/**
	 * 是否删除
	 */
	@TableLogic
	private Integer deleted;
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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}
	/**
	 * 获取：简介
	 */
	public String getBrief() {
		return brief;
	}


	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	/**
	 * 设置：获取投票数
	 */
	public void setVotes(Integer votes) {
		this.votes = votes;
	}
	/**
	 * 获取：获取投票数
	 */
	public Integer getVotes() {
		return votes;
	}
	/**
	 * 设置：所属活动Id
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：所属活动Id
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getDeleted() {
		return deleted;
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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}

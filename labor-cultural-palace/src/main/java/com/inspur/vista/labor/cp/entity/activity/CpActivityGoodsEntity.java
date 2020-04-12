package com.inspur.vista.labor.cp.entity.activity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * 活动奖品管理
 * 
 * @author 
 * @email 
 * @date 2020-04-01 10:06:47
 */
@TableName("cp_activity_goods")
@ApiModel
public class CpActivityGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
	@TableId
	private Integer id;

	/**
	 * 活动id
	 */
	@ApiModelProperty("活动id")
	private Integer activityId;
    /**
     * 商品名称
     */
    @ApiModelProperty("奖品名称")
	private String name;
    /**
     * 商品数量
     */
	@ApiModelProperty("奖品数量")
	private Integer num;
    /**
     * 余量
     */
	private Integer margin;
    /**
     * 工会Id
     */
	private String laborId;
    /**
     * 工会name
     */
	private String laborName;
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
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：商品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：商品数量（不填默认为无数量限制）
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：商品数量（不填默认为无数量限制）
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：余量
	 */
	public void setMargin(Integer margin) {
		this.margin = margin;
	}
	/**
	 * 获取：余量
	 */
	public Integer getMargin() {
		return margin;
	}
	/**
	 * 设置：工会Id
	 */
	public void setLaborId(String laborId) {
		this.laborId = laborId;
	}
	/**
	 * 获取：工会Id
	 */
	public String getLaborId() {
		return laborId;
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

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
}

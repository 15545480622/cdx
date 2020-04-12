package com.inspur.vista.labor.cp.entity.activity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CPCultureCouponEntity
 * @Description: 文化宫活动投票人对象信息
 * @Author: ljs
 * @CreateDate: 2020-03-31 13:38:43
 * @Version: 1.0
 */
@TableName("cp_activity_vote_records")
public class CPActivityVoteRecordsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 被投人Id
	 */
	private Integer pollederId;
	/**
	 * 投票人code
	 */
	private String voteId;
	/**
	 * 投票人姓名
	 */
	private String voteName;
	/**
	 * 投票人手机号
	 */
	private String votePhone;
	/**
	 * 投票人工会名称
	 */
	private String voteLaborName;
    /**
     * 投票人工会Id
     */
    private String voteLaborId;
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
	 * 设置：被投人Id
	 */
	public void setPollederId(Integer pollederId) {
		this.pollederId = pollederId;
	}
	/**
	 * 获取：被投人Id
	 */
	public Integer getPollederId() {
		return pollederId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
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

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public String getVoteLaborName() {
		return voteLaborName;
	}

	public void setVoteLaborName(String voteLaborName) {
		this.voteLaborName = voteLaborName;
	}

    public String getVoteLaborId() {
        return voteLaborId;
    }

    public void setVoteLaborId(String voteLaborId) {
        this.voteLaborId = voteLaborId;
    }

	public String getVotePhone() {
		return votePhone;
	}

	public void setVotePhone(String votePhone) {
		this.votePhone = votePhone;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
}

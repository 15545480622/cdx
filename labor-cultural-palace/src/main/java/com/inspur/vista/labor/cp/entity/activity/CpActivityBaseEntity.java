package com.inspur.vista.labor.cp.entity.activity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 投票报名活动
 * 
 * @author 
 * @email 
 * @date 2020-03-18 14:28:24
 */
@ApiModel
@TableName("cp_activity_base")
public class CpActivityBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
	@TableId
	private Integer id;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
	private String title;
    /**
     * 内容
     */
	@ApiModelProperty("内容")
	private String content;
    /**
     * 活动类型（投票:vote，抽奖:draw，报名join，领券tickets）
     */
	@ApiModelProperty("活动类型（投票:vote，抽奖:draw，报名join，领券tickets）")
	private String type;
	/**
	 * 是否团体报名
	 */
	@ApiModelProperty("是否团体报名")
	private Integer isGroup;

	/**
	 * 文化宫id
	 */
	@ApiModelProperty("文化宫id")
	private String cpId;
    /**
     * 封面图
     */
	@ApiModelProperty("封面图")
	private String coverImg;
    /**
     * 是否置顶
     */
	private Integer isTop;
    /**
     * 是否启用 1:开启；0:关闭，2创建完成
     */
	private Integer status;
    /**
     * 活动发布状态  1:审核成功；2:审核中/待审核；3:审核失败；4：内部审核成功；5:创建
     */
	private Integer releaseStatus;
    /**
     * 使用范围  行政区划code码
     */
	private String useRange;
    /**
     * 活动级别（全省：21；市级：22；区县：24）
     */
	@ApiModelProperty("活动级别（全省：21；市级：22；区县：24）")
	private String level;
    /**
     * 活动开始时间
     */
	@ApiModelProperty("活动开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
    /**
     * 活动结束时间
     */
	@ApiModelProperty("活动结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
    /**
     * 人数限制
     */
	@ApiModelProperty("人数限制(不传或0表示不限制)")
	private Integer countLimit;
    /**
     * 操作者Id
     */
	private String operatorId;
    /**
     * 操作人
     */
	private String operatorName;
    /**
     * 活动链接地址
     */
	private String link;
    /**
     * 工会Code
     */
	private String laborCode;
    /**
     * 工会Id
     */
	private String laborId;
    /**
     * 工会名称
     */
	private String laborName;
    /**
     * 创建者的工会级别(和level 判读是否跨级活动）
     */
	private String laborType;
    /**
     * 场所id
     */
	@ApiModelProperty("场所id")
	private String placeId;
	/**
	 * 场所名称
	 */
	@TableField(exist = false)
	private String placeName;
    /**
     * 浏览量
     */
	private Integer visitation;
    /**
     * 评论量
     */
	private Integer commonQuantity;
    /**
     * 点赞量
     */
	private Integer praisePoints;
    /**
     * 是否删除
     */
    @TableLogic
	private Integer deleted;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
    /**
     * 活动的顶级工会path
     */
	private String path;
	/**
	 * 活动限制，onlyOne只能一次，everyDay每天次数限制
	 */
	@ApiModelProperty("活动限制，onlyOne只能一次，everyDay每天次数限制")
	private String condition;

	/**
	 * 每天参与次数
	 */
	@ApiModelProperty("每天参与次数")
	private Integer takeCount;

	/**
	 * 优惠券id
	 */
	@ApiModelProperty("优惠券id")
	private Integer couponId;

	/**
	 * 优惠券数量
	 */
	@ApiModelProperty("优惠券数量")
	private Integer couponCount;

	/**
	 * 中将概率
	 */
	@ApiModelProperty("中将概率")
	private BigDecimal winningRate;

	/**
	 * 抽奖展示形式 转盘: wheel， 砸金蛋: eggs
	 */
	@ApiModelProperty("抽奖展示形式 转盘:wheel,砸金蛋:eggs")
	private String displayType;

	/**
	 * 指定工会list
	 */
	@TableField(exist = false)
	private List<CpActivitySpecifyOrganEntity> specifyList;

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
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：活动类型（投票:vote，抽奖:draw，报名join，领券tickets）
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：活动类型（投票:vote，抽奖:draw，报名join，领券tickets）
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：封面图
	 */
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	/**
	 * 获取：封面图
	 */
	public String getCoverImg() {
		return coverImg;
	}
	/**
	 * 设置：是否置顶
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	/**
	 * 获取：是否置顶
	 */
	public Integer getIsTop() {
		return isTop;
	}
	/**
	 * 设置：是否启用 1:开启；0:关闭，2创建完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：是否启用 1:开启；0:关闭，2创建完成
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：活动发布状态  1:审核成功；2:审核中/待审核；3:审核失败；4：内部审核成功；5:创建
	 */
	public void setReleaseStatus(Integer releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	/**
	 * 获取：活动发布状态  1:审核成功；2:审核中/待审核；3:审核失败；4：内部审核成功；5:创建
	 */
	public Integer getReleaseStatus() {
		return releaseStatus;
	}
	/**
	 * 设置：使用范围  行政区划code码
	 */
	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}
	/**
	 * 获取：使用范围  行政区划code码
	 */
	public String getUseRange() {
		return useRange;
	}
	/**
	 * 设置：活动级别（全省：21；市级：22；区县：24）
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：活动级别（全省：21；市级：22；区县：24）
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置：活动开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：活动开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：活动结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：活动结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：人数限制
	 */
	public void setCountLimit(Integer countLimit) {
		this.countLimit = countLimit;
	}
	/**
	 * 获取：人数限制
	 */
	public Integer getCountLimit() {
		return countLimit;
	}
	/**
	 * 设置：操作者Id
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * 获取：操作者Id
	 */
	public String getOperatorId() {
		return operatorId;
	}
	/**
	 * 设置：操作人
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	/**
	 * 获取：操作人
	 */
	public String getOperatorName() {
		return operatorName;
	}
	/**
	 * 设置：活动链接地址
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * 获取：活动链接地址
	 */
	public String getLink() {
		return link;
	}
	/**
	 * 设置：工会Code
	 */
	public void setLaborCode(String laborCode) {
		this.laborCode = laborCode;
	}
	/**
	 * 获取：工会Code
	 */
	public String getLaborCode() {
		return laborCode;
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
	 * 设置：工会名称
	 */
	public void setLaborName(String laborName) {
		this.laborName = laborName;
	}
	/**
	 * 获取：工会名称
	 */
	public String getLaborName() {
		return laborName;
	}
	/**
	 * 设置：创建者的工会级别(和level 判读是否跨级活动）
	 */
	public void setLaborType(String laborType) {
		this.laborType = laborType;
	}
	/**
	 * 获取：创建者的工会级别(和level 判读是否跨级活动）
	 */
	public String getLaborType() {
		return laborType;
	}
	/**
	 * 设置：场所id
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	/**
	 * 获取：场所id
	 */
	public String getPlaceId() {
		return placeId;
	}
	/**
	 * 设置：浏览量
	 */
	public void setVisitation(Integer visitation) {
		this.visitation = visitation;
	}
	/**
	 * 获取：浏览量
	 */
	public Integer getVisitation() {
		return visitation;
	}
	/**
	 * 设置：评论量
	 */
	public void setCommonQuantity(Integer commonQuantity) {
		this.commonQuantity = commonQuantity;
	}
	/**
	 * 获取：评论量
	 */
	public Integer getCommonQuantity() {
		return commonQuantity;
	}
	/**
	 * 设置：点赞量
	 */
	public void setPraisePoints(Integer praisePoints) {
		this.praisePoints = praisePoints;
	}
	/**
	 * 获取：点赞量
	 */
	public Integer getPraisePoints() {
		return praisePoints;
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
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
	 * 设置：活动的顶级工会path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：活动的顶级工会path
	 */
	public String getPath() {
		return path;
	}

	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public List<CpActivitySpecifyOrganEntity> getSpecifyList() {
		return specifyList;
	}

	public void setSpecifyList(List<CpActivitySpecifyOrganEntity> specifyList) {
		this.specifyList = specifyList;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getTakeCount() {
		return takeCount;
	}

	public void setTakeCount(Integer takeCount) {
		this.takeCount = takeCount;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public BigDecimal getWinningRate() {
		return winningRate;
	}

	public void setWinningRate(BigDecimal winningRate) {
		this.winningRate = winningRate;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}

package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPHonorEntity
 * @Description: 文化宫荣誉信息实体类
 * @authur: wangxueying01
 * @CreatDate: 2019/12/23 8:50
 */
@ApiModel(value = "荣誉信息", description = "荣誉信息")
public class CPHonorEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", required = true, example = "", position = 2)
    private String cpId;

    /**
     * 荣誉获得时间
     */
    @ApiModelProperty(value = "荣誉获得时间", required = true, example = "2020-04-02", position = 3)
    private String honorTime;

    /**
     * 荣誉级别,1.全国级;2.省级;3.市级;4.区县级
     */
    @ApiModelProperty(value = "荣誉级别,1.全国级;2.省级;3.市级;4.区县级", required = true, example = "1", position = 4)
    private Integer honorLevel;

    /**
     * 荣誉名称
     */
    @ApiModelProperty(value = "荣誉名称", required = true, example = "", position = 5)
    private String honorName;

    /**
     * 荣誉说明
     */
    @ApiModelProperty(value = "荣誉说明", example = "", position = 6)
    private String honorInstruction;

    /**
     * 状态:1.有效;0.无效
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer state;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", hidden = true)
    private String remark;

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

    public String getHonorTime() {
        return honorTime;
    }

    public void setHonorTime(String honorTime) {
        this.honorTime = honorTime;
    }

    public Integer getHonorLevel() {
        return honorLevel;
    }

    public void setHonorLevel(Integer honorLevel) {
        this.honorLevel = honorLevel;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getHonorInstruction() {
        return honorInstruction;
    }

    public void setHonorInstruction(String honorInstruction) {
        this.honorInstruction = honorInstruction;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CPHonorEntity{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", honorTime='" + honorTime + '\'' +
                ", honorLevel=" + honorLevel +
                ", honorName='" + honorName + '\'' +
                ", honorInstruction='" + honorInstruction + '\'' +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

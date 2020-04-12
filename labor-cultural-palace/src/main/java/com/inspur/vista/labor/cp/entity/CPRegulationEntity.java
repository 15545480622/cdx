package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPRegulationEntity
 * @Description: 文化宫制度管理实体类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 10:20
 */
@ApiModel(value = "文化宫制度管理", description = "文化宫制度管理")
public class CPRegulationEntity implements Serializable {

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
     * 制度名称
     */
    @ApiModelProperty(value = "制度名称", required = true, example = "", position = 3)
    private String name;

    /**
     * 制度类型：1. 规章制度；2.安全制度
     */
    @ApiModelProperty(value = "制度类型：1. 规章制度；2.安全制度", required = true, example = "", position = 4)
    private Integer regulationType;

    /**
     * 制度内容
     */
    @ApiModelProperty(value = "制度内容", required = true, example = "", position = 5)
    private String regulationContent;

    /**
     * 是否有效，1.有效；0.无效
     */
    @ApiModelProperty(value = "是否有效，1.有效；0.无效", hidden = true)
    private Integer state;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifier;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 修改时间
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegulationType() {
        return regulationType;
    }

    public void setRegulationType(Integer regulationType) {
        this.regulationType = regulationType;
    }

    public String getRegulationContent() {
        return regulationContent;
    }

    public void setRegulationContent(String regulationContent) {
        this.regulationContent = regulationContent;
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
        return "CPRegulationEntity{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", name='" + name + '\'' +
                ", regulationType=" + regulationType +
                ", regulationContent='" + regulationContent + '\'' +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

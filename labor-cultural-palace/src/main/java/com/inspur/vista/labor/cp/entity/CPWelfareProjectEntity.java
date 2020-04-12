package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPWelfareProjectEntity
 * @Description: 公益性服务项目清单
 * @authur: wangxueying01
 * @CreatDate: 2020/4/8 11:00
 */
@ApiModel(value = "公益性服务项目清单", description = "公益性服务项目清单")
public class CPWelfareProjectEntity implements Serializable {

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
     * 类型：1.月度；2.年度
     */
    @ApiModelProperty(value = "类型：1.月度；2.年度", required = true, example = "", position = 3)
    private Integer type;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true, example = "", position = 4)
    private String name;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容", required = true, example = "", position = 5)
    private String content;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "CPWelfareProjectEntity{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

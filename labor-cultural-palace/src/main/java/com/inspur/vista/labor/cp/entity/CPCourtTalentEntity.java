package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CPCourtTalentEntity
 * @Description: 场地专业人才关系实体类
 * @authur: wangxueying01
 * @CreatDate: 2020/4/2 20:28
 */
@ApiModel(value = "场地专业人才关系", description = "场地专业人才关系")
public class CPCourtTalentEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 场地id
     */
    @ApiModelProperty(value = "场地id", required = true, example = "", position = 2)
    private String courtId;

    /**
     * 专业人才id
     */
    @ApiModelProperty(value = "专业人才id", required = true, example = "", position = 3)
    private String talentId;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getTalentId() {
        return talentId;
    }

    public void setTalentId(String talentId) {
        this.talentId = talentId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CPCountTalentEntity{" +
                "id='" + id + '\'' +
                ", courtId='" + courtId + '\'' +
                ", talentId='" + talentId + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

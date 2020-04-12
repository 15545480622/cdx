package com.inspur.vista.labor.cp.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inspur.vista.labor.cp.config.DateDeserializer;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
/**
 * @Title: CPVisitorsFlowrateEntity
 * @Description: 访问记录
 * @Author: liuzq
 * @CreateDate: 2019/12/14 16:14
 * @Version: 1.0
 */
public class CPVisitorsFlowrateEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 文化宫id
     */
    @NotNull(message = "文化宫id不能为空")
    private Long cpId;

    /**
     * 1.视频;2.闸机;3.扫码;4.其他
     */
    @NotNull(message = "1.视频;2.闸机;3.扫码;4.其他不能为空")
    private Integer type;

    /**
     * 采集时间
     */
    @NotNull(message = "采集时间不能为空")
    private Timestamp collectionTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = DateDeserializer.class)
    private Date createTime;

    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Timestamp collectionTime) {
        this.collectionTime = collectionTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CPVisitorsFlowrateEntity{" +
                ", id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", type='" + type + '\'' +
                ", collectionTime='" + collectionTime + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
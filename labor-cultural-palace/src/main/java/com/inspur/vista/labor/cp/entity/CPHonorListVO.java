package com.inspur.vista.labor.cp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Date;


/**
 * @ClassName: CPHonorListVO
 * @Description: 文化宫荣誉信息
 * @authur: wangxueying01
 * @CreatDate: 2019/12/23 9:04
 */
@ApiModel(value = "荣誉列表信息", description = "荣誉列表信息")
public class CPHonorListVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id",  position = 2)
    private String cpId;

    /**
     * 荣誉获得时间
     */
    @ApiModelProperty(value = "荣誉获得时间", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date honorTime;

    /**
     * 荣誉级别,1.全国级;2.省级;3.市级;4.区县级
     */
    @ApiModelProperty(value = "荣誉级别,1.全国级;2.省级;3.市级;4.区县级", position = 4)
    private Integer honorLevel;

    /**
     * 荣誉名称
     */
    @ApiModelProperty(value = "荣誉名称",  position = 5)
    private String honorName;

    /**
     * 荣誉说明
     */
    @ApiModelProperty(value = "荣誉说明", position = 6)
    private String honorInstruction;

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

    public Date getHonorTime() {
        return honorTime;
    }

    public void setHonorTime(Date honorTime) {
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

    @Override
    public String toString() {
        return "CPHonorListVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", honorTime=" + honorTime +
                ", honorLevel=" + honorLevel +
                ", honorName='" + honorName + '\'' +
                ", honorInstruction='" + honorInstruction + '\'' +
                '}';
    }
}

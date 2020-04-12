package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPRegulationDetailVO
 * @Description: 文化宫制度管理VO
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 10:21
 */
@ApiModel(value = "文化宫制度管理详细信息", description = "文化宫制度管理详细信息")
public class CPRegulationInfoVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", position = 2)
    private String cpId;

    /**
     * 制度名称
     */
    @ApiModelProperty(value = "制度名称", position = 3)
    private String name;

    /**
     * 制度类型：1. 规章制度；2.安全制度
     */
    @ApiModelProperty(value = "制度类型：1. 规章制度；2.安全制度", position = 4)
    private Integer regulationType;

    /**
     * 制度内容
     */
    @ApiModelProperty(value = "制度内容", position = 5)
    private String regulationContent;

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

    @Override
    public String toString() {
        return "CPRegulationInfoVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", name='" + name + '\'' +
                ", regulationType=" + regulationType +
                ", regulationContent='" + regulationContent + '\'' +
                '}';
    }
}

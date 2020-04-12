package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: CPAiequipmentBindDetailVO
 * @Description: 智能设备绑定信息
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:54
 * @Version: 1.0
 */
@ApiModel(value = "智能设备绑定详细信息", description = "智能设备绑定详细信息")
public class CPAiequipmentBindVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", example = "", position = 1)
    private String id;

    /**
     * 智能设备id
     */
    @ApiModelProperty(value = "智能设备id", example = "", position = 2)
    private String aiequipmentId;

    /**
     * 场所id
     */
    @ApiModelProperty(value = "场所id", example = "", position = 3)
    private String placeId;

    /**
     * 位置
     */
    @ApiModelProperty(value = "位置", example = "", position = 4)
    private String location;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAiequipmentId() {
        return aiequipmentId;
    }

    public void setAiequipmentId(String aiequipmentId) {
        this.aiequipmentId = aiequipmentId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "CPAiequipmentBindDetailVO{" +
                "id='" + id + '\'' +
                ", aiequipmentId='" + aiequipmentId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
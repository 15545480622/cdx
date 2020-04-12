package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: CPAiequipmentInfoListVO
 * @Description: 智能设备信息
 * @Author: liuzq
 * @CreateDate: 2020/03/07 14:37
 * @Version: 1.0
 */
@ApiModel(value = "智能设备列表信息", description = "智能设备列表信息")
public class CPAiequipmentInfoListVO implements Serializable {

    /**
     * uuid
     */
    @ApiModelProperty(value = "id", example = "", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", example = "", position = 2)
    private String cpId;

    /**
     * 设备厂商
     */
    @ApiModelProperty(value = "设备厂商", example = "", position = 3)
    private Long firm;

    /**
     * 设备序列号
     */
    @ApiModelProperty(value = "设备序列号", example = "", position = 4)
    private String serialNumber;

    /**
     * 设备编码
     */
    @ApiModelProperty(value = "设备编码", example = "", position = 5)
    private String code;

    /**
     * 设备型号
     */
    @ApiModelProperty(value = "设备型号", example = "", position = 6)
    private String version;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", example = "", position = 7)
    private String name;

    /**
     * 设备类型 1：智能摄像头、2：智能道闸、3：智能门禁、4：其它
     */
    @ApiModelProperty(value = "设备类型 1：智能摄像头、2：智能道闸、3：智能门禁、4：其它", example = "", position = 8)
    private Integer type;

    /**
     * 设备位置
     */
    @ApiModelProperty(value = "设备位置", example = "", position = 9)
    private String location;

    /**
     * 设备说明
     */
    @ApiModelProperty(value = "设备说明", example = "", position = 10)
    private String note;



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

    public Long getFirm() {
    	return firm;
    }
    public void setFirm(Long firm) {
    	this.firm = firm;
    }

    public String getSerialNumber() {
    	return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
    	this.serialNumber = serialNumber;
    }

    public String getCode() {
    	return code;
    }
    public void setCode(String code) {
    	this.code = code;
    }

    public String getVersion() {
    	return version;
    }
    public void setVersion(String version) {
    	this.version = version;
    }

    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }

    public Integer getType() {
    	return type;
    }
    public void setType(Integer type) {
    	this.type = type;
    }

    public String getLocation() {
    	return location;
    }
    public void setLocation(String location) {
    	this.location = location;
    }

    public String getNote() {
    	return note;
    }
    public void setNote(String note) {
    	this.note = note;
    }

    @Override
    public String toString() {
        return "CPAiequipmentInfoListVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", firm=" + firm +
                ", serialNumber='" + serialNumber + '\'' +
                ", code='" + code + '\'' +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", location='" + location + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
package com.inspur.vista.labor.cp.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Title: CPAiequipmentFirmInfoListVO
 * @Description: 智能设备厂商信息
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:05
 * @Version: 1.0
 */
@ApiModel(value = "智能设备厂商列表信息", description = "智能设备厂商列表信息")
public class CPAiequipmentFirmInfoListVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", example = "", position = 1)
    private String id;

    /**
     * 文化宫id
     */
    @ApiModelProperty(value = "文化宫id", example = "", position = 2)
    private String cpId;

    /**
     * 厂商名称
     */
    @ApiModelProperty(value = "厂商名称", example = "", position = 3)
    private String name;

    /**
     * 平台地址
     */
    @ApiModelProperty(value = "平台地址", example = "", position = 4)
    private String url;

    /**
     * appid
     */
    @ApiModelProperty(value = "appid", example = "", position = 5)
    private String appid;

    /**
     * 证书
     */
    @ApiModelProperty(value = "证书", example = "", position = 6)
    private String certificate;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明", example = "", position = 7)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "CPAiequipmentFirmInfoListVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", appid='" + appid + '\'' +
                ", certificate='" + certificate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
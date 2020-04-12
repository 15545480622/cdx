package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: CPWelfareProjectVO
 * @Description: 公益性服务项目清单VO
 * @authur: wangxueying01
 * @CreatDate: 2020/4/8 11:01
 */
@ApiModel(value = "公益性服务项目清单详细信息", description = "公益性服务项目清单详细信息")
public class CPWelfareProjectVO implements Serializable {

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
     * 类型：1.月度；2.年度
     */
    @ApiModelProperty(value = "类型：1.月度；2.年度", position = 3)
    private Integer type;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", position = 4)
    private String name;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容", position = 5)
    private String content;

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

    @Override
    public String toString() {
        return "CPWelfareProjectVO{" +
                "id='" + id + '\'' +
                ", cpId='" + cpId + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

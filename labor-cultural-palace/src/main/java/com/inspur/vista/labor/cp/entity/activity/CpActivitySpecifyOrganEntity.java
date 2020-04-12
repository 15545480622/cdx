package com.inspur.vista.labor.cp.entity.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @version 1.0
 * @description 活动指定机构
 * @date 2020/03/26 10/04
 **/
@ApiModel
public class CpActivitySpecifyOrganEntity {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 主键
     */
    private Integer activityId;
    /**
     * 指定工会id
     */
    @ApiModelProperty("指定工会id")
    private String specifyLaborId;
    /**
     * 指定工会名称
     */
    @ApiModelProperty("指定工会名称")
    private String specifyLaborName;
    /**
     * 指定工会code
     */
    @ApiModelProperty("指定工会code")
    private String specifyLaborCode;

    /**
     * 指定工会path
     */
    private String specifyLaborPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getSpecifyLaborId() {
        return specifyLaborId;
    }

    public void setSpecifyLaborId(String specifyLaborId) {
        this.specifyLaborId = specifyLaborId;
    }

    public String getSpecifyLaborName() {
        return specifyLaborName;
    }

    public void setSpecifyLaborName(String specifyLaborName) {
        this.specifyLaborName = specifyLaborName;
    }

    public String getSpecifyLaborCode() {
        return specifyLaborCode;
    }

    public void setSpecifyLaborCode(String specifyLaborCode) {
        this.specifyLaborCode = specifyLaborCode;
    }

    public String getSpecifyLaborPath() {
        return specifyLaborPath;
    }

    public void setSpecifyLaborPath(String specifyLaborPath) {
        this.specifyLaborPath = specifyLaborPath;
    }
}

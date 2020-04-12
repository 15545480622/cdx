package com.inspur.vista.labor.cp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Title: CPCheck
 * @Description: 审核
 * @Author: gengpeng
 * @CreateDate: 2020/4/1 11:39
 * @Version: 1.0
 */
@ApiModel(value = "审核信息", description = "审核信息")
public class CPApplyCheck {

    /**
     * 申请表id
     */
    @ApiModelProperty(value = "申请表id", required = true, position = 1)
    private String applyId;

    /**
     * 申请表类型
     */
    @ApiModelProperty(value = "申请表类型", required = true, position = 2)
    private String applyType;

    /**
     * 处理结果,1.同意；0.不同意
     */
    @ApiModelProperty(value = "处理结果,1.同意；0.不同意", required = true, position = 3)
    private int handleResult;

    /**
     * 处理意见
     */
    @ApiModelProperty(value = "处理意见", position = 4)
    private String handleOpinions;

    /**
     * 处理人
     */
    @ApiModelProperty(value = "处理人", hidden = true)
    private String handler;

    /**
     * 处理人所在工会
     */
    @ApiModelProperty(value = "处理人所在工会", hidden = true)
    private String handleLaborId;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public int getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(int handleResult) {
        this.handleResult = handleResult;
    }

    public String getHandleOpinions() {
        return handleOpinions;
    }

    public void setHandleOpinions(String handleOpinions) {
        this.handleOpinions = handleOpinions;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandleLaborId() {
        return handleLaborId;
    }

    public void setHandleLaborId(String handleLaborId) {
        this.handleLaborId = handleLaborId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    @Override
    public String toString() {
        return "CPApplyCheck{" +
                "applyId='" + applyId + '\'' +
                ", applyType='" + applyType + '\'' +
                ", handleResult=" + handleResult +
                ", handleOpinions='" + handleOpinions + '\'' +
                ", handler='" + handler + '\'' +
                ", handleLaborId='" + handleLaborId + '\'' +
                '}';
    }
}

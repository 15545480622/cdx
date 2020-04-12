package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: PubFilesUploadVO
 * @Description: 附件批量上传实体类
 * @Author: gengpeng
 * @CreateDate: 2019/12/13 11:07
 * @Version: 1.0
 */
public class PubFilesUploadVO implements Serializable {

    /**
     * 业务id
     */
    private Long bsnId;

    /**
     * 附件类型
     */
    private int type;

    /**
     * oss存储的url
     */
    private List<String> ossImageUrl;

    /**
     * 创建人
     */
    private String creator;

    public Long getBsnId() {
        return bsnId;
    }

    public void setBsnId(Long bsnId) {
        this.bsnId = bsnId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getOssImageUrl() {
        return ossImageUrl;
    }

    public void setOssImageUrl(List<String> ossImageUrl) {
        this.ossImageUrl = ossImageUrl;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "PubFilesUploadVO{" +
                "bsnId=" + bsnId +
                ", type=" + type +
                ", ossImageUrl=" + ossImageUrl +
                ", creator='" + creator + '\'' +
                '}';
    }
}

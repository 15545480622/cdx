package com.inspur.vista.labor.cp.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * @Title: PubFilesEntity
 * @Description: 附件
 * @Author: liuzq
 * @CreateDate: 2019/12/11 15:04
 * @Version: 1.0
 */
public class PubFilesEntity implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 业务id
     */
    private String bsnId;

    /**
     * 类型
     */
    private String type;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 扩展名
     */
    private String fileExt;

    /**
     * 大小
     */
    private Long fileSize;

    /**
     * 存储文件相对地址
     */
    private String filePath;

    /**
     * 状态,1.有效；0.无效
     */
    private Integer state;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 最后更新人
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBsnId() {
        return bsnId;
    }

    public void setBsnId(String bsnId) {
        this.bsnId = bsnId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "PubFilesEntity{" +
                "id='" + id + '\'' +
                ", bsnId='" + bsnId + '\'' +
                ", type='" + type + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileExt='" + fileExt + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
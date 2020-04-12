package com.inspur.vista.labor.cp.entity;


import java.io.Serializable;

/**
 * @Title: PubFilesVO
 * @Description: 附件
 * @Author: liuzq
 * @CreateDate: 2019/12/11 15:04
 * @Version: 1.0
 */
public class PubFilesVO implements Serializable {

    /**
     * 附件id
     */
    private String id;

    /**
     * 业务id
     */
    private String bsnId;

    /**
     * 附件类型
     */
    private String type;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件扩展
     */
    private String fileExt;

    /**
     * 文件大小，单位：字节
     */
    private Long fileSize;

    /**
     * 存储文件相对地址
     */
    private String filePath;

    /**
     * 下载地址
     */
    private String downloadUrl;

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

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "PubFilesVO{" +
                "id='" + id + '\'' +
                ", bsnId='" + bsnId + '\'' +
                ", type='" + type + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileExt='" + fileExt + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
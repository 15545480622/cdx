package com.inspur.vista.labor.cp.entity;

/**
 * @Title: CPApplyFileEntiry
 * @Description: 申请表附带的文件
 * @Author: gengpeng
 * @CreateDate: 2020/3/31 16:04
 * @Version: 1.0
 */
public class CPApplyFileVO {

    /**
     * id
     */
    private String id;

    /**
     * 申请表id
     */
    private String applyId;

    /**
     * 附件类型
     */
    private String type;

    /**
     * 序号
     */
    private Integer sn;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "CPApplyFileVO{" +
                "id='" + id + '\'' +
                ", applyId='" + applyId + '\'' +
                ", type='" + type + '\'' +
                ", sn=" + sn +
                ", fileName='" + fileName + '\'' +
                ", fileExt='" + fileExt + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

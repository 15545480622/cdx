package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @ClassName: CPVisitorsFlowrateListVO
 * @Description: 访问记录VO
 * @authur: wangxueying01
 * @CreatDate: 2019/12/17 10:01
 */
public class CPVisitorsFlowrateListVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 1.视频;2.闸机;3.扫码;4.其他
     */
    private Integer type;

    /**
     * 采集时间
     */
    private Timestamp collectionTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Timestamp collectionTime) {
        this.collectionTime = collectionTime;
    }

    @Override
    public String toString() {
        return "CPVisitorsFlowrateDetailVO{" +
                "id=" + id +
                ", type=" + type +
                ", collectionTime=" + collectionTime +
                '}';
    }
}

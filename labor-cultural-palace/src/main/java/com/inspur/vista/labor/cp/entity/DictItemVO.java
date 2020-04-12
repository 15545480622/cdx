package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;

/**
 * @Title: DictItemEntity
 * @Description: 字典项实体类
 * @Author: gengpeng
 * @CreateDate: 2019/12/6 11:03
 * @Version: 1.0
 */
public class DictItemVO implements Serializable {

    /**
     * 字典项编码
     */
    private String itemCode;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典项名称
     */
    private String itemValue;

    /**
     * 序号
     */
    private int xh;

    /**
     * 上级字典项编码
     */
    private String parentCode;


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }


    @Override
    public String toString() {
        return "DictItemVO{" +
                "itemCode='" + itemCode + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", itemValue='" + itemValue + '\'' +
                ", xh=" + xh +
                ", parentCode='" + parentCode + '\'' +
                '}';
    }
}

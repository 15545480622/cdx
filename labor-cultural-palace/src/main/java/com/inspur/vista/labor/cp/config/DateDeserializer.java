package com.inspur.vista.labor.cp.config;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Date;

/**
 * @Title: DateDeserializer
 * @Description: 日期格式序列化，支持 yyyy-MM-dd 转 yyyy-MM-dd HH:mm:ss
 * @Author: gengpeng
 * @CreateDate: 2019/9/14 11:44
 * @Version: 1.0
 */
public class DateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return DateUtil.parse(p.getText()).toSqlDate();
    }
}

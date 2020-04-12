package com.inspur.vista.labor.cp.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Title: DefaultRedisProperties
 * @Description: 默认redis配置
 * @Author: gengpeng
 * @CreateDate: 2019/6/7 16:20
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.default")
public class DefaultRedisProperties {

    /**
     * 默认的redis节点
     */
    private String nodes;

    /**
     * redis节点密码
     */
    private String password;

    /**
     * redis最大重定向数
     */
    private Integer maxRedirects;

    /**
     * redis命令超时时间
     */
    private Duration timeout;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(Integer maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }
}

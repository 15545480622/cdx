package com.inspur.vista.labor.cp.config.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title: RedisConfiguration
 * @Description: redis公共配置类
 * @Author: gengpeng
 * @CreateDate: 2019/6/6 14:03
 * @Version: 1.0
 */
@Configuration
public class BaseRedisConfiguration {

    /**
     * 创建redis连接工厂
     *
     * @param clusterNodes 集群节点
     * @param password 密码
     * @param maxRedirects 最大重定向数
     * @param timeout 命令超时时间
     * @return LettuceConnectionFactory
     */
    public LettuceConnectionFactory createConnectionFactory(String clusterNodes, String password, Integer maxRedirects, Duration timeout) {
        String[] serverArray = clusterNodes.split(",");
        Set<RedisNode> nodes = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }

        LettuceClientConfiguration.LettuceClientConfigurationBuilder lettuceClientConfigurationBuilder = LettuceClientConfiguration.builder();
        lettuceClientConfigurationBuilder.commandTimeout(timeout);

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setPassword(password);
        redisClusterConfiguration.setMaxRedirects(maxRedirects);
        redisClusterConfiguration.setClusterNodes(nodes);

        return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfigurationBuilder.build());
    }
}

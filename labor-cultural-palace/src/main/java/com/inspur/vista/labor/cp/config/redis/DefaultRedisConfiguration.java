package com.inspur.vista.labor.cp.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Title: DefaultRedisConfiguration
 * @Description: 默认redis配置类，存储token、uac配置等信息
 * @Author: gengpeng
 * @CreateDate: 2019/6/6 14:44
 * @Version: 1.0
 */
@Configuration
public class DefaultRedisConfiguration extends BaseRedisConfiguration {

    /**
     * 默认的redis配置
     */
    private final DefaultRedisProperties properties;

    @Autowired
    public DefaultRedisConfiguration(DefaultRedisProperties properties) {
        this.properties = properties;
    }


    /**
     * 配置redis连接工厂
     *
     * @return RedisConnectionFactory
     */
    @Primary
    @Bean
    public RedisConnectionFactory defaultRedisConnectionFactory() {
        return createConnectionFactory(properties.getNodes(), properties.getPassword(), properties.getMaxRedirects(), properties.getTimeout());
    }

    /**
     * 配置默认redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return RedisTemplate
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(defaultRedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.afterPropertiesSet();
        return template;
    }
}

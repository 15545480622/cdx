package com.inspur.vista.labor.cp.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: CacheRedisConfiguration
 * @Description: 缓存redis集群配置类，存储用户信息等
 * @Author: gengpeng
 * @CreateDate: 2019/6/6 14:57
 * @Version: 1.0
 */
@Configuration
@EnableCaching
public class CacheRedisConfiguration extends BaseRedisConfiguration {

    /**
     * 作为缓存用途的redis配置
     */
    private final CacheRedisProperties properties;

    @Autowired
    public CacheRedisConfiguration(CacheRedisProperties properties) {
        this.properties = properties;
    }


    /**
     * 配置redis连接工厂
     *
     * @return RedisConnectionFactory
     */
    @Bean
    public RedisConnectionFactory cacheRedisConnectionFactory() {
        return createConnectionFactory(properties.getNodes(), properties.getPassword(), properties.getMaxRedirects(), properties.getTimeout());
    }

    /**
     * 获取默认的Redis的缓存配置
     *
     * @return RedisCacheConfiguration
     */
    private RedisCacheConfiguration getDefaultRedisCacheConfiguration() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getJackson2JsonRedisSerializer();

        // 改为7天
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .computePrefixWith(cacheName -> "uac:cache:" + cacheName + ":")
                .entryTtl(Duration.ofDays(7));
    }

    /**
     * 配置CacheManager
     *
     * @param redisConnectionFactory redis连接工厂
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager cacheManager(@Qualifier("cacheRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultConfiguration = getDefaultRedisCacheConfiguration();
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                .fromCacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(defaultConfiguration).withInitialCacheConfigurations(getRedisCacheConfigurationMap());
        return builder.build();
    }

    /**
     * 根据key配置缓存时间
     * @return
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("member_id", this.getRedisCacheConfigurationWithTtl(86400));//一天
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getJackson2JsonRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).computePrefixWith(cacheName -> "uac:cache:" + cacheName + ":").entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }

    /**
     * 获取JSON的序列化类
     *
     * @return Jackson2JsonRedisSerializer
     */
    private Jackson2JsonRedisSerializer getJackson2JsonRedisSerializer() {

        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        serializer.setObjectMapper(om);
        return serializer;
    }

    /**
     * 配置缓存redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return RedisTemplate
     */
    @Bean(name = "cacheRedisTemplate")
    public RedisTemplate cacheRedisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(cacheRedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.afterPropertiesSet();
        return template;
    }
}

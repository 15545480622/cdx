package com.inspur.vista.labor.cp.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: 标题
 * @Description: java类作用描述
 * @Author: gengpeng
 * @CreateDate: 2019/3/6 17:50
 * @Version: 1.0
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

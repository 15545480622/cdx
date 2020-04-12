package com.inspur.vista.labor.cp.config;

import com.inspur.vista.labor.cp.config.interceptor.AppAuthInterceptor;
import com.inspur.vista.labor.cp.config.interceptor.BackManageInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author zhangqixia
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public BackManageInterceptor getBackManageInterceptor() {
        return new BackManageInterceptor();
    }

    @Bean
    public AppAuthInterceptor getAppAuthInterceptor() {
        return new AppAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 后台拦截
        registry.addInterceptor(getBackManageInterceptor()).addPathPatterns("/api/cp/**");
        // app拦截
        registry.addInterceptor(getAppAuthInterceptor()).addPathPatterns("/api/app/cp/**")
                .excludePathPatterns("/api/app/cp/pay**", "/api/app/cp/activity/join/appScan/**");

    }

    /**
     * swagger修改地址前缀
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
        registry.addRedirectViewController("/documentation/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/documentation/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
    }

    /**
     * swagger修改地址前缀
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/documentation/**").addResourceLocations("classpath:/META-INF/resources/");
    }
}

package com.inspur.vista.labor.cp.config;

import org.loushang.bsp.security.AgentSessionIntegrationFilter;
import org.loushang.bsp.security.sp.service.SpFilterWrapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Filter配置---集成bsp单点登录
 *
 * @author administrator
 * @date 2018年10月18日
 */
@Configuration
public class FilterConfig {

    /**
     * 单点登陆过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean spDispacherFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SpFilterWrapper());
        registration.setEnabled(true);
        registration.addUrlPatterns("/SAML2", "/*");
        return registration;
    }

    /**
     * 客户端回话集成
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean sessionIntegrationFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AgentSessionIntegrationFilter());
        registration.setEnabled(true);
        registration.setOrder(Integer.MAX_VALUE);
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * 访问控制器
     * @return
     */
//    @Bean
//    public FilterRegistrationBean authorizationFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//
//        // 基于角色的授权
//        registration.setFilter(new AgentAuthzBaseUserTypeFilter());
//
//        registration.setEnabled(true);
//        registration.addUrlPatterns("/*");
//        return registration;
//    }
}

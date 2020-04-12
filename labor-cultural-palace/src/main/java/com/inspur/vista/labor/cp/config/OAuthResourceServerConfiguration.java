package com.inspur.vista.labor.cp.config;

import com.inspur.vista.labor.cp.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 进行oauth2的配置，进行两方面的配置：
 * <p>
 * 1. 进行资源服务器的配置
 *
 * @author zhangcanyong
 */
@Configuration
public class OAuthResourceServerConfiguration {

    private static final String UAC_RESOURCE_ID = "uac";

    /**
     * 保证这里的配置优先生效
     */
    @Configuration
    @EnableResourceServer
    @Order(3)
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private TokenStore tokenStore;
        @Autowired
        private CustomAccessDeniedHandler customAccessDeniedHandler;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(UAC_RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .accessDeniedHandler(customAccessDeniedHandler)
                    .authenticationEntryPoint(new AuthExceptionEntryPoint());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.headers().frameOptions().disable();
            //@formatter:off
            http.antMatcher("/**").authorizeRequests()
//					.antMatchers("/api/**").access("#oauth2.hasScope('stage_user')")
                    .antMatchers("/**").permitAll();
            //@formatter:on
        }

    }
}

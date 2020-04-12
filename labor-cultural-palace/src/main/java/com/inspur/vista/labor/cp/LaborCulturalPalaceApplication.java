package com.inspur.vista.labor.cp;

import com.inspur.vista.labor.cp.util.CalendarUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession(redisNamespace = "cp:spring:session")
@ComponentScan(basePackages = {"com.inspur.vista"})
@MapperScan("com.inspur.vista.**.dao")
public class LaborCulturalPalaceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LaborCulturalPalaceApplication.class, args);
        //初始化特殊日期到内存
        CalendarUtil.setApplicationContext(applicationContext);
        CalendarUtil.getInstance().initDateList();
        System.out.println("测试添加内容");
    }


    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(20000);
        factory.setConnectTimeout(20000);
        return factory;
    }
}

package com.bi.datasource.config;


import com.bi.servicebase.interceptor.LoginInterceptor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/21 13:16
 */

//@Data
//@Configuration
//@ConfigurationProperties(prefix = "intercept")
//public class DatasourceMVCConfig implements WebMvcConfigurer {
//    private List<String> urls;
//
//    @Bean
//    public LoginInterceptor getloginInterceptor(){
//        return new LoginInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getloginInterceptor()).addPathPatterns(urls);
//    }
//}

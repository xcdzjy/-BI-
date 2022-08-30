package com.bi.main.config;

import com.bi.servicebase.interceptor.LoginInterceptor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Panda
 * @date 2022/8/25 12:35
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "intercept")
public class MainMVCConfig implements WebMvcConfigurer {

    private List<String> urls;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public LoginInterceptor getloginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getloginInterceptor()).addPathPatterns(urls);
    }
}

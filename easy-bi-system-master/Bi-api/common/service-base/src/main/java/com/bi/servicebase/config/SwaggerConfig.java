package com.bi.servicebase.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @author Panda
 * @date 2022/6/16 9:23
 */

@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfig {
    @Bean
    public Docket getDocument(){
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(webApiInfo())
                .select()
                .paths(PathSelectors.regex("/admin/.*").negate())
                .paths(PathSelectors.regex("/error.*").negate())
                .build();
    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("后台接口文档")
                .description("本文档描述了服务接口定义")
                .version("1.0")
                .build();
    }

}

package com.bi.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author Panda
 * @date 2022/8/21 12:40
 */

@SpringBootApplication
@ComponentScan("com.bi")
@MapperScan(basePackages = {"com.bi.datasource.mapper"})
@EnableDiscoveryClient
public class DataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSourceApplication.class,args);
    }
}

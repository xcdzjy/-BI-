package com.bi.metadata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Panda
 * @date 2022/8/16 22:12
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.bi"})
@MapperScan(basePackages = {"com.bi.metadata.mapper"})
@EnableDiscoveryClient
public class MetadataApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetadataApplication.class,args);
    }
}

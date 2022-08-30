package com.bi.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Panda
 * @date 2022/8/21 13:01
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.bi"})
@MapperScan(basePackages = {"com.bi.system.mapper"})
@EnableDiscoveryClient
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }
}

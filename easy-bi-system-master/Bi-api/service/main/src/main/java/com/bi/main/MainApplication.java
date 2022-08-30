package com.bi.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Panda
 * @date 2022/8/25 12:31
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.bi")
@EnableFeignClients
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}

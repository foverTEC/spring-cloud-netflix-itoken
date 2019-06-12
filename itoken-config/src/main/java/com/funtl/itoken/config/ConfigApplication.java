package com.funtl.itoken.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/*
* 配置服务端
* */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient  //服务提供者  无论是消费者还是服务者都要注册到eurekaz中
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}

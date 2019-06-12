package com.funtl.itoken.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient
public class AdminApplication {
     public static void main(String[] args) {
         SpringApplication.run(AdminApplication.class,args);
         }
}

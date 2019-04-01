package com.feri.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *@Author feri
 *@Date Created in 2019/3/25 16:40
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication  {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
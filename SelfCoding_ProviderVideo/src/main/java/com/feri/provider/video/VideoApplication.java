package com.feri.provider.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *@Author feri
 *@Date Created in 2019/3/25 15:29
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.feri.dao.video")
public class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}

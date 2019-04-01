package com.feri.provider.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *@Author feri
 *@Date Created in 2019/3/25 14:47
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.feri.dao.course")
public class CourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class,args);
    }
}

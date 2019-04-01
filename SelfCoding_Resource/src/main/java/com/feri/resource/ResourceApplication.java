package com.feri.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@Author feri
 *@Date Created in 2019/3/22 09:34
 */
@SpringBootApplication
@EnableSwagger2
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class,args);
    }
}

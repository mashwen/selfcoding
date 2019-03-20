package com.feri.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@Author feri
 *@Date Created in 2019/3/20 13:20
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource(locations = "classpath*:spring-*.xml") //导入外部的spring的配置文件
@EnableSwagger2
public class SelfCodingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfCodingApplication.class, args);
    }
}

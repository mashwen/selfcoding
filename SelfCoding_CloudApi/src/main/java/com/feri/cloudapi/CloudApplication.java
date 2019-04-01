package com.feri.cloudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@Author feri
 *@Date Created in 2019/3/25 15:44
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableSwagger2
public class CloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class,args);
    }
}

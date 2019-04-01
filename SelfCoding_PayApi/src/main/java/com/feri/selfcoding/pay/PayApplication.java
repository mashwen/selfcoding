package com.feri.selfcoding.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.SpringServletContainerInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@Author feri
 *@Date Created in 2019/3/26 14:44
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableSwagger2
public class PayApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PayApplication.class);
    }
}

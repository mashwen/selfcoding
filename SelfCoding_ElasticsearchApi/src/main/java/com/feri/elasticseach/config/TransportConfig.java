package com.feri.elasticseach.config;

import com.feri.elasticseach.transport.TransportBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@Author feri
 *@Date Created in 2019/3/28 14:36
 */
@Configuration
public class TransportConfig {
    @Bean
    public TransportBean createTB(){
        return new TransportBean("qfjava","39.105.189.141",9300);
    }
}

package com.feri.resource.config;

import com.feri.resource.oss.OSSUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@Author feri
 *@Date Created in 2019/3/22 12:16
 */
@Configuration
public class OSSConfig {
    @Bean
    public OSSUtil createOSS(){
        return  new OSSUtil(SystemConfig.ENDPOINT,SystemConfig.KEYID,SystemConfig.KEYSECRET);
    }
}

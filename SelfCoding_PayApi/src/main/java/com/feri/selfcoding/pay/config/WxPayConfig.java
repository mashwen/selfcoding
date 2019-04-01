package com.feri.selfcoding.pay.config;

import com.feri.selfcoding.pay.util.WXPayConstants;
import com.feri.selfcoding.pay.util.WxChatPayUtil;
import com.feri.selfcoding.pay.wechatpay.WXConfig;
import com.feri.selfcoding.pay.wechatpay.WXPay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@Author feri
 *@Date Created in 2019/3/28 10:30
 */
@Configuration
public class WxPayConfig {
//    @Bean
//    public WXPay wxPay() throws Exception {
//        return new WXPay(new WXConfig());
//    }
    @Bean
    public WxChatPayUtil createPayUtil(){
        return new WxChatPayUtil(WXPayConstants.APP_ID,WXPayConstants.MCH_ID,WXPayConstants.API_KEY,WXPayConstants.HMACSHA256);
    }
}

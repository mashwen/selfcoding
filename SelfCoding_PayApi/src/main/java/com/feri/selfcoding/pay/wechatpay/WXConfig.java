package com.feri.selfcoding.pay.wechatpay;

import java.io.InputStream;

/**
 *@Author feri
 *@Date Created in 2019/3/28 10:15
 */
public class WXConfig extends WXPayConfig {
    @Override
    public String getAppID() {
        return WXPayConstants.APP_ID;
    }

    @Override
    public String getMchID() {
        return WXPayConstants.MCH_ID;
    }

    @Override
    public String getKey() {
        return WXPayConstants.API_KEY;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }
}

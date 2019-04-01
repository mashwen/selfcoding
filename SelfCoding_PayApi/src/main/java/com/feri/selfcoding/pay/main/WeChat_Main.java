package com.feri.selfcoding.pay.main;

import com.feri.common.util.HttpUtil;
import com.feri.selfcoding.pay.wechatpay.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *@Author feri
 *@Date Created in 2019/3/28 10:14
 */
public class WeChat_Main {
    public static void main(String[] args) throws Exception {
        //创建微信支付对象
        WXPay wxPay=new WXPay(new WXConfig());
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("body","渴了，来杯");
        hashMap.put("out_trade_no",UUID.randomUUID().toString().replace("-",""));
        hashMap.put("total_fee","1");
        hashMap.put("spbill_create_ip","10.8.156.20");
        hashMap.put("notify_url","10.8.156.20");
        hashMap.put("trade_type","NATIVE");

        Map<String,String>param=wxPay.fillRequestData(hashMap);
        String xml=WXPayUtil.mapToXml(param);
        System.out.println(xml);
        String response=HttpUtil.httpRequest(xml,"https://api.mch.weixin.qq.com/pay/unifiedorder",WXPayConstants.CONNECT_TO,WXPayConstants.USER_AGENT);
        System.out.println(response);

    }
}

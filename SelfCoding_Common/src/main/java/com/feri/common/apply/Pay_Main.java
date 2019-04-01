package com.feri.common.apply;



import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;

import java.util.UUID;

/**
 *@Author feri
 *@Date Created in 2019/3/26 12:06
 */
public class Pay_Main {
    public static void main(String[] args) throws AlipayApiException {
        //1、创建客户端对象
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                "2017091608770636","MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCh1qI8uo1qhrcePsa5JUAoYUX8HfPuBt7kc90aCP1M/v61/uzaU/lyGQeChKV3jdDTn2Lcq6kT5JBl3TLiaYHmO6cId1nQAIUxiT9zhB9crc4wAx8CVabMbcqUefs7Xsp+YhhUgU5X6GOS3emkUeL7RegBnL8vayfEBeUDgBxsk/K/VygBA8sapsEhnoOrB6bhMY4GaJrxb0kg9Ej8x4kpExLcxkT+UgcOiJvh6vpBZo5CJsiPQkFvSsNsWY2uSDudSL/KqpMxz+yPfVvZDt4fOfyi+CfYR43Jlo4tsT7joqH2JT06BH+KdJyc1D3Lqw7w/WdmZtmoLghH0kRZawrLAgMBAAECggEAYYtpm+rhQ7zQ8HTr+DogknYW5Z/0H5qai93d/Uw/yEHFqlJt1iZZKlE1upBS311l6beesdzxeuD/u7X4bokjV27K/YpaYsl9fl74FJslAApuRXgMH68aawsd2CIxsBYxPL3JZl3Np6SVJ7eDlJwakFMRRK+CeIVAoaDf6R01hKctkYnnE0wT+ffQNKWsISoEyiKVT3g5fur7iPOuDlDXsfi6Mm+e75wCXTmRRHmb8lPBAMLV+Kj5DFxg8dwNz81Fs4ZM2Aq0lBaTfy1H1zSlM1m42wcsMYDcgdEH9aq+OgqK+cny6umgs7/Alg7IgV/9b7AhKdvAqLy2ERUJtooj2QKBgQDeIoDW3HuTq7sBaBnu63f7icT2RM3fApfOiGM4UDtxPvc5dS5S//o3E8p+rbp21FfBeyLOJFd9dg/eu+ETA+63QMPw4Kq4AH/EA5AFohaOQ0IKFDjYyxfyD8ajA4USDwdiaW2/vmMeAtGSv+W5zWb9/t49LOTwzEW904+yOGcmhQKBgQC6guDZ0Ob4o9nx5XwZXEe2di4MupARHceGzmolyDvs3Qi/w+8QntrDvfqIJoqoxOG5NVi3jtjkqtJtMaPyxqNWTabWOOTLbrsqlvPUmeCl0j3FVFKAGcV7/b9XkLvh1DtnIe6rhhZCVB4e4bL/katpOTgulhmSMaWIaztGU0F1DwKBgQCTeobdn/6vuSlsMqhdFppPN1W8R0wDjt4o8iYlwibk9e//hswdsPN307zyQ/dzY2FsBIvEHx6zHkpFD6nMDSVVJzuv1gmiJjqtccwR4V5mT0MuG+TuElCwlkbD/ddAeRfm/6Ys0oNN7oMjkiI8LKH/alI0fXT2Zji7YhWaNpZNXQKBgEU6q0duWS1VdGJrcgLf0+aQO0uSPEN+MD+Dgrb/ee7TpJm5mpUqwb0CWWoMFE/MtJRQjtujdDJ8jZrmYBqPTLWOIS1G9PXl5idK3Lq/Wzlxrmf+gpj19+2sJEfWe0a5xkrjt3mHTd/U5VFFKXHfmiZ2jLoOEPPI5c6bLudNo/BVAoGBAMvwRxLO4xb11Ip4rnEHkw3Qn8lrddoC3/m7haHYZ5DyGe8wdCdEi6wyk5MvlNQdqdVg5bqV0AiotIBcd5Pemabun2WaB11h/6SSb6wKY4Fnz+H155zaEww4no9BTG9llqQV7H8AS77dN1bxhcpE/MGFoB9JFU0D+BwXAnth4z1u",
                "json","UTF-8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx7jJT+PSEM6ZiimTW0SGUfg4cJU04H/mQqkL2mk7KaHXFQqMh4US6xYkDlaEXzOOfxevuBqWOaB4/8TleO1CHZHXWHu9Xc+iYtJPNJGrxoGLM+6Cg9IafJTygRoaqdH0SoVMpxFdOpUftNdXHO+G0ZpS/7c1zpn8G64zN5J17IFrLcUlsEnSgOrJxsS2Q50b44er0KQlj76pehB2sTveHS2vdhqXzrv+oq99XtUKEY1a3nwDjXneI7YYKLHD9KU53pti/ibLDkOEjO4+DRowd+wfSwkmWGVL3X320mvCfrg/aMN71B/cyyhW0mQ4cxqh2UcnpxLm0v/+uC7dSCyAJwIDAQAB",
                "RSA2");
        //2、创建请求对象
        //AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //3、请求下单接口
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//        model.setBody("我是测试数据");
//        model.setSubject("App支付测试Java");
//        String oid=UUID.randomUUID().toString();
//        model.setOutTradeNo(oid);
//        model.setTimeoutExpress("30m");
//        model.setTotalAmount("0.01");
//        model.setProductCode("QUICK_MSECURITY_PAY");
//        request.setBizModel(model);
//        request.setNotifyUrl("商户外网可以访问的异步地址");
        AlipayTradePrecreateRequest request=new AlipayTradePrecreateRequest();
        request.setBizContent("{\"out_trade_no\":\"20190210016121301\",\"total_amount\":0.01,\"subject\":\"老邢在测试支付宝支付\"}");
//        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
//        request.setBizContent("{" +
//                "\"out_trade_no\":\"2019033320010101001\"," +
//                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
//                "\"total_amount\":0.01," +
//                "\"subject\":\"Iphone6 16G\"," +
//                "\"body\":\"Iphone6 16G\"," +
//                "\"time_expire\":\"2016-12-31 10:05:01\"," +
//                "      \"goods_detail\":[{" +
//                "        \"goods_id\":\"apple-01\"," +
//                "\"alipay_goods_id\":\"20010001\"," +
//                "\"goods_name\":\"ipad\"," +
//                "\"quantity\":1," +
//                "\"price\":2000," +
//                "\"goods_category\":\"34543238\"," +
//                "\"categories_tree\":\"124868003|126232002|126252004\"," +
//                "\"body\":\"特价手机\"," +
//                "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
//                "        }]," +
//                "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//                "\"extend_params\":{" +
//                "\"sys_service_provider_id\":\"2088511833207846\"," +
//                "\"hb_fq_num\":\"3\"," +
//                "\"hb_fq_seller_percent\":\"100\"," +
//                "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
//                "\"card_type\":\"S0JP0000\"" +
//                "    }," +
//                "\"goods_type\":\"0\"," +
//                "\"timeout_express\":\"90m\"," +
//                "\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
//                "\"royalty_info\":{" +
//                "\"royalty_type\":\"ROYALTY\"," +
//                "        \"royalty_detail_infos\":[{" +
//                "          \"serial_no\":1," +
//                "\"trans_in_type\":\"userId\"," +
//                "\"batch_no\":\"123\"," +
//                "\"out_relation_id\":\"20131124001\"," +
//                "\"trans_out_type\":\"userId\"," +
//                "\"trans_out\":\"2088101126765726\"," +
//                "\"trans_in\":\"2088101126708402\"," +
//                "\"amount\":0.1," +
//                "\"desc\":\"分账测试1\"," +
//                "\"amount_percentage\":\"100\"" +
//                "          }]" +
//                "    }," +
//                "\"sub_merchant\":{" +
//                "\"merchant_id\":\"19023454\"," +
//                "\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
//                "    }," +
//                "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//                "\"store_id\":\"NJ_001\"," +
//                "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//                "\"qr_pay_mode\":\"4\"," +
//                "\"qrcode_width\":300," +
//                "\"invoice_info\":{" +
//                "\"key_info\":{" +
//                "\"is_support_invoice\":true," +
//                "\"invoice_merchant_name\":\"ABC|003\"," +
//                "\"tax_num\":\"1464888883494\"" +
//                "      }," +
//                "\"details\":\"[{\\\"code\\\":\\\"100294400\\\",\\\"name\\\":\\\"服饰\\\",\\\"num\\\":\\\"2\\\",\\\"sumPrice\\\":\\\"200.00\\\",\\\"taxRate\\\":\\\"6%\\\"}]\"" +
//                "    }," +
//                "\"agreement_sign_params\":{" +
//                "\"personal_product_code\":\"GENERAL_WITHHOLDING_P\"," +
//                "\"sign_scene\":\"INDUSTRY|CARRENTAL\"," +
//                "\"external_agreement_no\":\"test\"," +
//                "\"external_logon_id\":\"13852852877\"," +
//                "\"sign_validity_period\":\"2m\"," +
//                "\"third_party_type\":\"PARTNER\"," +
//                "\"buckle_app_id\":\"1001164\"," +
//                "\"buckle_merchant_id\":\"268820000000414397785\"," +
//                "\"promo_params\":\"{\\\"key\\\",\\\"value\\\"}\"" +
//                "    }," +
//                "\"integration_type\":\"PCWEB\"," +
//                "\"request_from_url\":\"https://\"," +
//                "\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"," +
//                "\"ext_user_info\":{" +
//                "\"name\":\"李明\"," +
//                "\"mobile\":\"16587658765\"," +
//                "\"cert_type\":\"IDENTITY_CARD\"," +
//                "\"cert_no\":\"362334768769238881\"," +
//                "\"min_age\":\"18\"," +
//                "\"fix_buyer\":\"F\"," +
//                "\"need_check_info\":\"F\"" +
//                "    }" +
//                "  }");
//        try {
//            //这里和普通的接口调用不同，使用的是sdkExecute
////            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            AlipayTradePrecreateResponse response1 = alipayClient.execute(request);
            System.out.println(response1.isSuccess());
            System.out.println(response1.getQrCode());//就是orderString 可以直接给客户端请求，无需再做处理。
//            ZxingUtil.encode(response1.getQrCode(),"png",300,300,"zfb.png");
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
    }
}

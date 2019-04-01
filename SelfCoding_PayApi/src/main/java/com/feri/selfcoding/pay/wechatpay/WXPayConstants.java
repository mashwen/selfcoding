package com.feri.selfcoding.pay.wechatpay;

import org.apache.http.client.HttpClient;

/**
 * 常量
 */
public class WXPayConstants {

    public enum SignType {
        MD5, HMACSHA256
    }

    public static final String EWMURL="http://localhost:16888";
    public static final int READ_TO=8000;
    public static final int CONNECT_TO=8000;
    public static String APP_ID = "wx632c8f211f8122c6";//appid
    public static String MCH_ID = "1497984412";//商户
    public static String API_KEY = "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";//秘钥


    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";

    public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
    public static final String USER_AGENT = WXPAYSDK_VERSION +
            " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") +
            ") Java/" + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();
}


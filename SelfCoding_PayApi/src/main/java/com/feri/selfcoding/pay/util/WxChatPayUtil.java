package com.feri.selfcoding.pay.util;

import com.feri.common.util.HttpUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 *@Author feri
 *@Date Created in 2019/3/28 11:18
 * 封装微信支付工具类
 */
public class WxChatPayUtil {
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();
    private String appid;
    private String mch_id;
    private String appkey;
    private String nonce_str;
    private String sign_type;
    private String trade_type;

   public WxChatPayUtil(String appid,String mch_id,String appkey,String sign_type){
       this.appid=appid;
       this.mch_id=mch_id;
       this.appkey=appkey;
       this.sign_type=sign_type;
       this.trade_type="NATIVE";
   }
   //统一下单接口
    public Map<String,String> createOrder(Map<String,String> map){
       map.put("appid",appid);
       map.put("mch_id",mch_id);
       map.put("sign_type",sign_type);
       map.put("trade_type",trade_type);
       this.nonce_str=generateNonceStr();
       map.put("nonce_str",nonce_str);
       try{
           String sign=generateSignature(map,appkey,WXPayConstants.SignType.HMACSHA256);
           map.put("sign",sign);
           String responsexml=HttpUtil.httpRequest(mapToXml(map),"https://api.mch.weixin.qq.com/pay/unifiedorder",8000,WXPayConstants.USER_AGENT);
           return xmlToMap(responsexml);
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;

    }
    // 查询下单接口
    public Map<String,String> searchOrder(String orderid){
        Map<String,String> map=new HashMap<>();
        map.put("appid",appid);
        map.put("mch_id",mch_id);
        map.put("out_trade_no",orderid);
        this.nonce_str=generateNonceStr();
        map.put("nonce_str",nonce_str);
        try{
            String sign=generateSignature(map,appkey,WXPayConstants.SignType.HMACSHA256);
            map.put("sign",sign);
            String responsexml=HttpUtil.httpRequest(mapToXml(map),"https://api.mch.weixin.qq.com/pay/orderquery",8000,WXPayConstants.USER_AGENT);
            return xmlToMap(responsexml);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    //关闭订单
    public Map<String,String> closeOrder(String orderid){
        Map<String,String> map=new HashMap<>();
        map.put("appid",appid);
        map.put("mch_id",mch_id);
        map.put("out_trade_no",orderid);
        this.nonce_str=generateNonceStr();
        map.put("nonce_str",nonce_str);
        try{
            String sign=generateSignature(map,appkey,WXPayConstants.SignType.HMACSHA256);
            map.put("sign",sign);
            String responsexml=HttpUtil.httpRequest(mapToXml(map),"https://api.mch.weixin.qq.com/pay/closeorder",8000,WXPayConstants.USER_AGENT);
            return xmlToMap(responsexml);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    //下载对账单
    public String downbill(String bill_date){
        Map<String,String> map=new HashMap<>();
        map.put("appid",appid);
        map.put("mch_id",mch_id);
        map.put("bill_date",bill_date);
        this.nonce_str=generateNonceStr();
        map.put("nonce_str",nonce_str);
        try{
            String sign=generateSignature(map,appkey,WXPayConstants.SignType.HMACSHA256);
            map.put("sign",sign);
            String responsexml=HttpUtil.httpRequest(mapToXml(map),"https://api.mch.weixin.qq.com/pay/downloadbill",8000,WXPayConstants.USER_AGENT);
            return responsexml;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }



    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    private static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名
     */
    private static String generateSignature(final Map<String, String> data, String key, WXPayConstants.SignType signType) {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
            {
                sb.append(k).append("=").append(data.get(k).trim()).append("&");

            }
        }
        sb.append("key=").append(key);
        if (WXPayConstants.SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        }
        else if (WXPayConstants.SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        }
        else {
            System.out.println( String.format("Invalid sign_type: %s", signType));
            return null;
        }
    }
    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    private static String MD5(String data)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    private static String HMACSHA256(String data, String key)  {
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    private static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilder documentBuilder = WXPayXmlUtil.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            throw ex;
        }

    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    private static String mapToXml(Map<String, String> data) throws Exception {
        org.w3c.dom.Document document = WXPayXmlUtil.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key: data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return output;
    }




}

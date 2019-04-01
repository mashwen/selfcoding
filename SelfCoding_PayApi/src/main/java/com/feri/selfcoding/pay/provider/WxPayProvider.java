package com.feri.selfcoding.pay.provider;

import com.feri.common.util.Base64Util;
import com.feri.common.util.HttpUtil;
import com.feri.common.util.ResultUtil;
import com.feri.common.vo.ResultVo;
import com.feri.selfcoding.pay.service.WxPayService;
import com.feri.selfcoding.pay.util.WxChatPayUtil;
import com.feri.selfcoding.pay.wechatpay.WXPay;
import com.feri.selfcoding.pay.wechatpay.WXPayConstants;
import com.feri.selfcoding.pay.wechatpay.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 *@Author feri
 *@Date Created in 2019/3/28 10:48
 */
@Service
public class WxPayProvider implements WxPayService {

    @Autowired
    private WxChatPayUtil payUtil;
    //老的写法
    //    @Autowired
//    private WXPay wxPay;
//    @Override
//    public ResultVo createPre(Map<String, String> map) {
//        try {
////            String fee=map.get("total_fee");
////            if(fee!=null && fee.length()>0){
////                fee=(Double.parseDouble(fee)*100)+"";
////            }else {
////                fee="1";
////            }
//            map.put("total_fee","1");
//            //按照微信支付的要求排序对应的参数并且生成签名信息
//            Map<String,String> request=wxPay.fillRequestData(map);
//            //将Map转换为xml的字符串
//            String requestxml=WXPayUtil.mapToXml(request);
//            //发起请求生成预支付结果
//            String responseXML=HttpUtil.httpRequest(requestxml,"https://api.mch.weixin.qq.com/pay/unifiedorder",6000,WXPayConstants.USER_AGENT);
//            //解析xml
//            Map<String,String> responseMap=WXPayUtil.xmlToMap(responseXML);
//            //验证是否成功
//            if(Objects.equals(responseMap.get("return_code"),"SUCCESS")){
//                //
//                if(Objects.equals("SUCCESS",responseMap.get("result_code"))){
//                    //请求成功 获取预支付的链接地址
//                    String msg=responseMap.get("code_url");
//                    //将值转码为Base64格式
//                    String passmsg=Base64Util.base64Enc(msg.getBytes());
//                    //返回结果 携带的有二维码的路径
//                    return ResultUtil.exec(true,"OK","http://localhost:16888/qrcodepay.do/"+passmsg);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResultUtil.exec(false,"微信支付异常",null);
//    }
    @Override
    public ResultVo createPre(Map<String, String> map) {
        try {
            Map<String,String> responseMap=payUtil.createOrder(map);
            //验证是否成功
            if(responseMap.containsKey("code_url")){
                //请求成功 获取预支付的链接地址
                String msg=responseMap.get("code_url");
                //将值转码为Base64格式
                String passmsg=Base64Util.base64Enc(msg.getBytes());
                //返回结果 携带的有二维码的路径
                return ResultUtil.exec(true,"OK","http://localhost:16888/qrcodepay.do/"+passmsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"微信支付异常",null);
    }

    @Override
    public ResultVo searchOrder(String orderid) {
        Map<String,String> map=payUtil.searchOrder(orderid);

        if(map.containsKey("trade_state")){
            String res="";
            switch (map.get("trade_state")){
                case "SUCCESS":res="订单支付成功";break;
                case "REFUND":res="订单转入退款";break;
                case "NOTPAY":res="订单未支付";break;
                case "CLOSED":res="订单关闭";break;
                case "USERPAYING":res="订单支付中……";break;
                case "PAYERROR":res="订单支付失败,原因："+map.get("trade_state_desc");break;
            }
            return ResultUtil.exec(true,"OK",res);
        }
        return ResultUtil.exec(true,"网络异常",null);
    }

    @Override
    public ResultVo secrhBill(String billdate) {
        String res=payUtil.downbill(billdate);
        return ResultUtil.exec(true,"OK",res);
    }
}

package com.feri.selfcoding.pay.controller;

import com.feri.common.vo.ResultVo;
import com.feri.selfcoding.pay.service.WxPayService;
import com.feri.selfcoding.pay.wechatpay.WXPayConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *@Author feri
 *@Date Created in 2019/3/28 11:00
 */
@Api(value = "微信支付")
@RestController
public class WxPayController {
    @Autowired
    private WxPayService wxPayService;

    //生成预支付信息
    @ApiOperation(value = "创建预支付信息，生成二维码")
    @PostMapping("pay/wxpayorder.do")
    public ResultVo createOrder(String orderid,double money,String body, HttpServletRequest request) {
        Map<String,String> map=new HashMap<>();
        map.put("body",body);
        map.put("out_trade_no",orderid);
        map.put("total_fee",money+"");
        map.put("spbill_create_ip",request.getRemoteAddr());
        map.put("notify_url",WXPayConstants.EWMURL+"/pay/wxpaynotify.do");
        map.put("trade_type","NATIVE");
        return wxPayService.createPre(map);
    }
    //回调通知 只有支付宝才可以调用
    @GetMapping("pay/wxpaynotify.do")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json=IOUtils.toString(request.getInputStream(),"UTF-8");
        System.out.println("支付回调："+json);
        response.getWriter().print("success");
    }
    //查询订单
    @GetMapping("pay/wxpayquery.do")
    public ResultVo searchOrder(String orderid){
        return wxPayService.searchOrder(orderid);
    }
    //下载对账单
    @GetMapping("pay/wxpaybill.do")
    public ResultVo searchBill(String billDate){
        return wxPayService.secrhBill(billDate);
    }

}

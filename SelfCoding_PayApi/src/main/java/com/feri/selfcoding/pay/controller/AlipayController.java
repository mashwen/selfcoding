package com.feri.selfcoding.pay.controller;

import com.feri.common.vo.ResultVo;
import com.feri.selfcoding.pay.entity.AlipayOrder;
import com.feri.selfcoding.pay.service.AlipayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@Author feri
 *@Date Created in 2019/3/26 14:50
 * 实现支付宝支付
 */
@Api(value = "支付宝支付")
@RestController
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    //下单 生成预支付信息
    @ApiOperation(value = "创建支付二维码")
    @PostMapping("pay/alipayorder.do")
    public ResultVo save(AlipayOrder order){
        return alipayService.savePay(order);
    }
    //回调通知 只有支付宝才可以调用
    @GetMapping("pay/alipaynotify.do")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json=IOUtils.toString(request.getInputStream(),"UTF-8");
        System.out.println("支付回调："+json);
        response.getWriter().print("success");
    }
    //查询订单交易状态
    @ApiOperation(value = "查询订单状态")
    @GetMapping("pay/alipayquery.do")
    public ResultVo query(String tradeno){
        return alipayService.queryPay(tradeno);
    }
    //下载对账单  按照天
    @ApiOperation(value = "查询对账单，按天查询")
    @GetMapping("pay/alipaybillday")
    public ResultVo down(String date){
        return alipayService.queryBill(date);
    }
    //下载对账单 按照月份
    @ApiOperation(value = "查询对账单，按月查询")
    @GetMapping("pay/alipaybillmonth")
    public ResultVo downmonth(String month){
        return alipayService.queryBill(month);
    }
}
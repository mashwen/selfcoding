package com.feri.selfcoding.pay.service;

import com.feri.common.vo.ResultVo;
import com.feri.selfcoding.pay.entity.AlipayOrder;

/**
 *@Author feri
 *@Date Created in 2019/3/26 14:51
 */
public interface AlipayService {
    //生成支付信息  下单
    ResultVo savePay(AlipayOrder order);
    //查询支付结果
    ResultVo queryPay(String trandon);
    //下载对账单
    ResultVo queryBill(String date);

}

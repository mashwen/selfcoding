package com.feri.selfcoding.pay.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *@Author feri
 *@Date Created in 2019/3/26 14:56
 */
public class AlipayOrder {
    private String out_trade_no;
    //private String product_code;//FAST_INSTANT_TRADE_PAY
    private double total_amount;//单位元  支持2位小数
    private String subject;
    private String body;
    private String time_expire;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }


    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public AlipayOrder() {
        this.total_amount = 0.01;
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,2);
        this.time_expire = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    public AlipayOrder(String out_trade_no, double total_amount, String subject, String body) {
        this.out_trade_no = out_trade_no;
        this.total_amount = 0.01;
        this.subject = subject;
        this.body = body;
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,2);
        this.time_expire = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }
}

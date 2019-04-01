package com.feri.common.main;

import com.feri.common.util.HttpUtil;

/**
 *@Author feri
 *@Date Created in 2019/3/28 15:13
 */
public class Http_Main {
    public static void main(String[] args) {
        //System.out.println(HttpUtil.getDataStr("http://www.baidu.com","GET",null));
        System.out.println(HttpUtil.getDataStr("http://localhost:17888/essingle.do?id=100034&indeName=cxy&type=student","GET",null));
        System.out.println(HttpUtil.getDataStr("http://localhost:17888/essingle.do?id=100034&indeName=cxy&type=student","GET",null));
    }
}

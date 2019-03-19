package com.feri.common.util;

import java.util.Random;

/**
 *@Author feri
 *@Date Created in 2019/3/19 14:26
 */
public class TokenUtil {
    public  static String createToken(int id,String msg){
     StringBuffer buffer=new StringBuffer();
     buffer.append(id+",");
     buffer.append(msg+",");
     buffer.append(System.currentTimeMillis()+",");
     buffer.append(new Random().nextInt());
     //base64编码格式
     return PassUtil.base64Enc(buffer.toString(),"UTF-8");
    }
}

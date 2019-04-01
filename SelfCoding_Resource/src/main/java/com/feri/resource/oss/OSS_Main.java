package com.feri.resource.oss;

import com.feri.resource.config.SystemConfig;

/**
 *@Author feri
 *@Date Created in 2019/3/22 12:09
 */
public class OSS_Main {
    public static void main(String[] args) {
        OSSUtil ossUtil=new OSSUtil(SystemConfig.ENDPOINT,SystemConfig.KEYID,SystemConfig.KEYSECRET);
        //创建存储空间
        //System.out.println(ossUtil.createBucket("xph2323232"));
        System.out.println(ossUtil.upload("饿了，要吃饭".getBytes(),"test1"));
    }
}

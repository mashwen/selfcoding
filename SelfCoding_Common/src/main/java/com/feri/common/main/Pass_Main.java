package com.feri.common.main;

import com.feri.common.util.EncryptionUtil;

import java.util.Map;

/**
 *@Author feri
 *@Date Created in 2019/3/26 10:19
 */
public class Pass_Main {
    public static void main(String[] args) {
//        System.out.println(EncryptionUtil.md5Enc("123456"));
//        System.out.println(EncryptionUtil.md5Enc("123456","lx",1024));
//        System.out.println(EncryptionUtil.SHAEnc(EncryptionUtil.SHA256,"123456"));
        //System.out.println(EncryptionUtil.md5Enc("123456","lx"));
        String key=EncryptionUtil.createAESKEY();
        System.out.println("秘钥："+key);
        String res="admin";
        String mw=EncryptionUtil.AESEnc(key,res);
        System.out.println("AES加密："+mw);
        System.out.println("AES解密："+EncryptionUtil.AESDec(key,mw));

        Map<String,String>  keymap=EncryptionUtil.createRSAKey();
        String pub=keymap.get(EncryptionUtil.PUBLICKEY);
        String pri=keymap.get(EncryptionUtil.PRIVATEKEY);
        String resmw=EncryptionUtil.RSAEnc(pri,res);
        System.out.println("RSA加密："+resmw);
        System.out.println("RSA解密："+EncryptionUtil.RSADec(pub,resmw));

    }
}

package com.feri.selfcoding.pay.controller;

import com.feri.common.util.Base64Util;
import com.feri.common.util.ZxingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *@Author feri
 *@Date Created in 2019/3/26 15:14
 */
@Controller
public class QrCodeController {
    //生成二维码图片
    @GetMapping("qrcodepay.do/{msg}")
    public void create(@PathVariable String msg, HttpServletResponse response) throws IOException {
        String m=new String(Base64Util.base64Dec(msg),"UTF-8");
       BufferedImage bufferedImage= ZxingUtil.createImage(m,300,300);
        ImageIO.write(bufferedImage,"JPEG",response.getOutputStream());
    }
    //生成二维码图片
    @GetMapping("qrcodecreate.do/{msg}")
    public void createQrcode(@PathVariable String msg, HttpServletResponse response) throws IOException {
        BufferedImage bufferedImage= ZxingUtil.createImage(msg,300,300);
        ImageIO.write(bufferedImage,"JPEG",response.getOutputStream());
    }
}

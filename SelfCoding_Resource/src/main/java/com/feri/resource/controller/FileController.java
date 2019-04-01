package com.feri.resource.controller;

import com.feri.resource.oss.OSSUtil;
import com.feri.resource.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 *@Author feri
 *@Date Created in 2019/3/22 09:35
 */
@Api(value = "资源存储操作")
@RestController
public class FileController {

    @Autowired
    private OSSUtil ossUtil;

    public static final String domain="http://localhost:9761/";
    //文件上传-图片
    @ApiOperation(value = "实现图片上传")
    @PostMapping("resource/imagesupload.do")
    public ResultVo saveImg(MultipartFile multipartFile, HttpServletRequest request){
        String msg="请选择上传图片";
        if(!multipartFile.isEmpty()){
            //文件保存
            //创建存储文件的根路径
            File dec=new File(new File(request.getServletContext().getRealPath("/")),"scresource/images");
            if(!dec.exists()){
                dec.mkdirs();
            }
            //重命名文件名称
            String fn=multipartFile.getOriginalFilename();
            //保证原始长度小于50
            if(fn.length()>50){
                fn=fn.substring(fn.length()-50);
            }
            //防止重复 加上唯一标记
            fn=System.currentTimeMillis()+"_"+UUID.randomUUID().toString().replace("-","")+"_"+fn;

            try {
                File file=new File(dec,fn);
                multipartFile.transferTo(file);
                msg=domain+"scresource/images/"+fn;
                System.out.println(file.getAbsolutePath());
                return ResultVo.exec(true,"OK",msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
       return ResultVo.exec(false,"上传失败",msg);
    }
    //文件下载
    @ApiOperation(value = "实现资源下载")
    @GetMapping("resource/filedown.do")
    public void down(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File dec=new File(new File(request.getServletContext().getRealPath("/")),"scresource/images");
        File file=new File(dec,name);
        System.out.println(file.getAbsolutePath());
        if(file.exists()){
            //设置消息头，告知浏览器 启动下载器
            response.setHeader("Content-Disposition","attachment;filename="+file.getName());
            //文件下载就是读取内容，写出到流中
            try {
                //以前这么写
                BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
                byte[] data=new byte[1024];
                int len;
                while ((len=bis.read(data))!=-1){
                    response.getOutputStream().write(data,0,len);
                }
                bis.close();
                //适合文件比较小
                //response.getOutputStream().write(IOUtils.resourceToByteArray(file.getAbsolutePath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getOutputStream().print("NO");
        }
    }
    //文件上传-视频
    @ApiOperation(value = "实现图片上传")
    @PostMapping("resource/ossupload.do")
    public ResultVo fileUpload(MultipartFile multipartFile) throws IOException {
        if(!multipartFile.isEmpty()){
            //重命名文件名称
            String fn=multipartFile.getOriginalFilename();
            //保证原始长度小于50
            if(fn.length()>50){
                fn=fn.substring(fn.length()-50);
            }
            //防止重复 加上唯一标记
            fn=System.currentTimeMillis()+"_"+UUID.randomUUID().toString().replace("-","")+"_"+fn;

           String url=ossUtil.upload(multipartFile.getInputStream(),fn);
           return ResultVo.exec(true,"OK",url);

        }else {
            return ResultVo.exec(false,"请选择上传的文件",null);
        }
    }

}

package com.feri.api.controller;

import com.feri.common.util.ResultUtil;
import com.feri.common.vo.ResultVo;
import com.feri.entity.user.User;
import com.feri.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *@Author feri
 *@Date Created in 2019/3/20 16:53
 */
@RestController
public class UserApi {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("user/usersave.do")
    @ApiOperation(value = "新增用户",notes = "注册新用户")
    public ResultVo register(User user, HttpServletRequest request){
        return userService.save(user,request.getRemoteAddr());
    }
    @ApiOperation(value = "校验手机号是否重复",notes = "检查手机号是否重复")
    @GetMapping("user/usercheck.do")
    public ResultVo check(String phone){
        return userService.checkPhone(phone);
    }
    //签到
    @GetMapping("user/usersign.do")
    @ApiOperation(value = "实现用户的每日签到",notes = "实现用户的每日签到")
    public ResultVo sign(HttpServletRequest request){
        Cookie[] cks=request.getCookies();
        String token="";
        for(Cookie c:cks){
            if(c.getName().equals("sctoken")){
                token=c.getValue();
                break;
            }
        }
        if(token!=null && token.length()>0){
            return userService.saveSign(token);
        }else {
            return ResultUtil.exec(false,"请先登录，再签到",null);
        }
    }
    //查询签到信息
    @GetMapping("user/userquerysign.do")
    @ApiOperation(value = "实现用户的每日签到",notes = "实现用户的每日签到")
    public ResultVo querysign(HttpServletRequest request){
        Cookie[] cks=request.getCookies();
        String token="";
        for(Cookie c:cks){
            if(c.getName().equals("sctoken")){
                token=c.getValue();
                break;
            }
        }
        if(token!=null && token.length()>0){
            return userService.querysign(token,1);
        }else {
            return ResultUtil.exec(false,"请先登录，再签到",null);
        }
    }

}

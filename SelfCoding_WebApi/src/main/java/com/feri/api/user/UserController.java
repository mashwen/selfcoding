package com.feri.api.user;

import com.feri.common.util.ResultUtil;
import com.feri.common.vo.ResultVo;
import com.feri.entity.user.User;
import com.feri.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *@Author feri
 *@Date Created in 2019/3/18 11:44
 */
@RestController
@Api(value = "用户操作",tags = "操作用户相关方法")
public class UserController {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("user/usersave.do")
    @ApiOperation(value = "新增用户",notes = "注册新用户")
    public ResultVo register(User user, HttpServletRequest request){
        return userService.save(user,request.getRemoteAddr());
    }
    //签到
    @GetMapping("user/usersign.do")
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


}
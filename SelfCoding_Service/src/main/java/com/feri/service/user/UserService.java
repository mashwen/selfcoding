package com.feri.service.user;

import com.feri.common.vo.ResultVo;
import com.feri.entity.user.User;

/**
 *@Author feri
 *@Date Created in 2019/3/18 11:20
 */
public interface UserService {
    //新增用户
    ResultVo save(User user,String ip);
    //每日签到
    ResultVo saveSign(String token);
    //检查手机号是否重复
    ResultVo checkPhone(String phone);
    //查询用户的签到数据 当月的签到数据 历史签到数据  type=1 本月数据 type=2 本年度数据 type=3 全部数据
    ResultVo querysign(String token,int type);

}


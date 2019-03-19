package com.feri.service.user;

import com.feri.common.vo.ResultVo;
import com.feri.entity.user.User;

/**
 *@Author feri
 *@Date Created in 2019/3/18 11:20
 */
public interface UserService {
    ResultVo save(User user,String ip);



}


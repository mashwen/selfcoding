package com.feri.provider.user;

import com.feri.common.util.ResultUtil;
import com.feri.common.vo.ResultVo;
import com.feri.dao.user.UserMapper;
import com.feri.dao.user.UserlogMapper;
import com.feri.entity.user.User;
import com.feri.entity.user.Userlog;
import com.feri.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 *@Author feri
 *@Date Created in 2019/3/18 11:25
 */
@Service
public class UserProvider implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserlogMapper userlogMapper;

    @Override
    public ResultVo save(User user,String ip) {
        userMapper.insert(user);
        Userlog userlog=new Userlog();
        userlog.setContent("注册用户");
        userlog.setCreatetime(new Date());
        userlog.setUid(user.getId());
        userlog.setIp(ip);
        userlogMapper.insert(userlog);
        return ResultUtil.exec(true,"OK",null);
    }
}

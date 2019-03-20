package com.feri.provider.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feri.common.sysconst.SystemCon;
import com.feri.common.util.ResultUtil;
import com.feri.common.util.TokenUtil;
import com.feri.common.vo.ResultVo;
import com.feri.dao.user.*;
import com.feri.entity.user.*;
import com.feri.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *@Author feri
 *@Date Created in 2019/3/18 11:25
 */
@Service("userprovider")
public class UserProvider implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserlogMapper userlogMapper;
    @Autowired
    private UsersignMapper usersignMapper;
    @Autowired
    private UserwalletMapper userwalletMapper;
    @Autowired
    private UserstreamMapper userstreamMapper;
    @Autowired
    private AwardlogMapper awardlogMapper;

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

    @Override
    public ResultVo saveSign(String token) {
        //1、解析Token
        int uid=TokenUtil.parseToken(token);
        //2、查询最后一次的签到时间
        Usersign usersign=usersignMapper.queryLastSign(uid);
        int days=1;
        Random random=new Random();
        //随机生成一个1-5的数字
        int cions=random.nextInt(5)+1;
        if(usersign!=null){
            //3、获取日期差
            int d=getDays(System.currentTimeMillis())-getDays(usersign.getCreatetime().getTime());
            if(d==0){
                //已经签到过
                cions=-1;
            }else if(d==1){
                //昨天签过到  连续签到
                days=usersign.getDays()+1;
                if(days%365==0){
                    //10倍
                    cions=cions*10;

                }else if(days%30==0){
                    //3-5倍
                    cions=cions*(random.nextInt(3)+3);
                }else if(days%5==0){
                    //1-3倍
                    cions=cions*(random.nextInt(3)+1);
                }
            }else{
                //断签

            }
        }
        if(cions>0) {
            //生成签到数据
            Usersign us = new Usersign();
            us.setCoins(cions);
            us.setDays(days);
            us.setCreatetime(Calendar.getInstance().getTime());
            us.setUid(uid);
            usersignMapper.insert(us);

            //更新钱包数据
            userwalletMapper.updateByCions(cions,uid);
            //生成奖励金流水数据
            Awardlog awardlog=new Awardlog();
            awardlog.setCoins(cions);
            awardlog.setCreatetime(Calendar.getInstance().getTime());
            awardlog.setType(SystemCon.JJLQD);
            awardlog.setUwid(uid);
            awardlog.setValidity(-1);
            awardlog.setStartdate(Calendar.getInstance().getTime());
            awardlog.setEnddate(Calendar.getInstance().getTime());
            awardlogMapper.insert(awardlog);
            //记录操作日志
            Userstream userstream=new Userstream();
            userstream.setContent(uid+":用户连续签到："+days+":本次获取奖励金："+cions);
            userstream.setCreatetime(Calendar.getInstance().getTime());
            userstream.setType(SystemCon.STREAMLOGSIGN);
            userstream.setUid(uid);
            userstreamMapper.insert(userstream);
            return ResultUtil.exec(true,"签到成功，连续签到"+days+"天，本次获取"+cions+"文",null);
        }else{
            return ResultUtil.exec(false,"今日已签到",null);
        }
    }

    @Override
    public ResultVo checkPhone(String phone) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        User user=userMapper.selectOne(queryWrapper);
        return ResultUtil.exec(user!=null,"CHECK",null);
    }

    @Override
    public ResultVo querysign(String token, int type) {

        return null;
    }

    private int getDays(long times){
        return  (int)(times/1000/60/60/24);
    }
}

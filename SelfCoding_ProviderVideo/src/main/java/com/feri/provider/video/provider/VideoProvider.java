package com.feri.provider.video.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feri.common.util.ResultUtil;
import com.feri.common.vo.ResultVo;
import com.feri.dao.video.VideoMapper;
import com.feri.entity.video.Video;
import com.feri.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *@Author feri
 *@Date Created in 2019/3/25 15:31
 */
@Service
public class VideoProvider implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Override
    public ResultVo save(Video video) {
        video.setCreatetime(new Date());
        video.setFlag(1);
        return ResultUtil.exec(true,"OK",null);
    }

    @Override
    public ResultVo queryById(int id) {
        return ResultUtil.exec(true,"OK",videoMapper.selectById(id));
    }

    @Override
    public ResultVo queryList(int cid) {
        return ResultUtil.exec(true,"OK",
                videoMapper.selectList(new QueryWrapper<Video>().eq("cid",cid)));
    }
}

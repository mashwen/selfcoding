package com.feri.cloudapi.controller;

import com.feri.cloudapi.provider.VideoProvider;
import com.feri.common.vo.PageVo;
import com.feri.common.vo.ResultVo;
import com.feri.entity.course.Course;
import com.feri.entity.video.Video;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/3/25 15:46
 */
@Api(value = "视频相关操作",tags = "视频操作")
@RestController
public class VideoController {
    @Autowired
    private VideoProvider videoProvider;

    @ApiOperation(value = "新增视频")
    @PostMapping("video/videosave.do")
    public ResultVo save(Video video){
        return videoProvider.save(video);
    }
    @ApiOperation(value = "播放视频")
    @GetMapping("video/videodetail.do")
    public ResultVo getDetail(int id){
        return videoProvider.detail(id);
    }
    @ApiOperation(value = "获取课程下面的视频详情")
    @GetMapping("video/videolist.do")
    public ResultVo page(int cid){
        return videoProvider.list(cid);
    }
}

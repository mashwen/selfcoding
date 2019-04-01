package com.feri.provider.video.controller;

import com.feri.common.vo.ResultVo;
import com.feri.entity.video.Video;
import com.feri.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/3/25 15:34
 */
@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    //新增
    @GetMapping("video/videosave.do")
    public ResultVo list(@RequestBody Video video){
        return videoService.save(video);
    }
    //详情
    @GetMapping(value ="video/videodetail.do",params = {"id"})
    public ResultVo detail(int id){
        return videoService.queryById(id);
    }
    //课程对应的视频
    @GetMapping(value = "video/videocid.do",params = {"cid"})
    public ResultVo list(int cid){
        return videoService.queryList(cid);
    }
}

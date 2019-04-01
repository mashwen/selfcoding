package com.feri.cloudapi.provider;

import com.feri.common.vo.ResultVo;
import com.feri.entity.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *@Author feri
 *@Date Created in 2019/3/25 15:48
 */
@Service
public class VideoProvider {
    @Autowired
    private RestTemplate restTemplate;

    public ResultVo save(Video video){
        return restTemplate.postForObject("http://lxvideoprovider/video/videosave.do",video,ResultVo.class);
    }
    public ResultVo detail(int id){
        return restTemplate.getForObject("http://lxvideoprovider/video/videodetail.do?id="+id,ResultVo.class);
    }
    public ResultVo list(int cid){
        return restTemplate.getForObject("http://lxvideoprovider/video/videocid.do?cid="+cid,ResultVo.class);
    }
}

package com.feri.service.video;

import com.feri.common.vo.ResultVo;
import com.feri.entity.video.Video;

/**
 *@Author feri
 *@Date Created in 2019/3/25 14:38
 */
public interface VideoService {
    ResultVo save(Video video);
    ResultVo queryById(int id);
    ResultVo queryList(int cid);

}

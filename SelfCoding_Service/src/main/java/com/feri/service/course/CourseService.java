package com.feri.service.course;

import com.feri.common.vo.PageVo;
import com.feri.common.vo.ResultVo;
import com.feri.entity.course.Course;

/**
 *@Author feri
 *@Date Created in 2019/3/25 14:38
 */
public interface CourseService {
    ResultVo save(Course course);
    ResultVo queryById(int id);
    PageVo<Course> queryPage(int page,int limit);


}

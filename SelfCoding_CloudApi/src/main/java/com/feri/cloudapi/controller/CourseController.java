package com.feri.cloudapi.controller;

import com.feri.cloudapi.provider.CourseProvider;
import com.feri.common.vo.PageVo;
import com.feri.common.vo.ResultVo;
import com.feri.entity.course.Course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/3/25 16:04
 */
@Api(value = "课程相关操作")
@RestController
public class CourseController {
    @Autowired
    private CourseProvider provider;
    @ApiOperation(value = "新增课程")
    @PostMapping("course/coursesave.do")
    public ResultVo save(Course course){
        return provider.save(course);
    }
    @ApiOperation(value = "课程详情")
    @GetMapping("course/coursedetail.do")
    public ResultVo getDetail(int id){
        return provider.getDetail(id);
    }
    @ApiOperation(value = "课程分页查询")
    @GetMapping("course/coursepage.do")
    public PageVo<Course> page(int page,int limit){
        return provider.getPage(page,limit);
    }
}

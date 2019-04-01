package com.feri.provider.course.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feri.common.vo.PageVo;
import com.feri.common.vo.ResultVo;
import com.feri.entity.course.Course;
import com.feri.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/3/25 15:03
 */
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    //新增
    @PostMapping("course/courseadd.do")
    public ResultVo save(@RequestBody Course course){
        System.out.println("传递："+course);
        return courseService.save(course);
    }
    //查询详情
    @GetMapping("course/coursedetail.do")
    public ResultVo detail(int id) {
        return courseService.queryById(id);
    }
    //分页查询
    @GetMapping("course/coursepage.do")
    public PageVo<Course> page(int page, int limit){
        return courseService.queryPage(page,limit);
    }
}

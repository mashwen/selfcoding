package com.feri.provider.course.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feri.common.util.ResultUtil;
import com.feri.common.vo.PageVo;
import com.feri.common.vo.ResultVo;
import com.feri.dao.course.CourseMapper;
import com.feri.entity.course.Course;
import com.feri.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 *@Author feri
 *@Date Created in 2019/3/25 14:51
 */
@Service
public class CourseProvider implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public ResultVo save(Course course) {
        course.setCreatetime(new Date());
        course.setFlag(1);
        courseMapper.insert(course);

        return ResultUtil.exec(true,"OK","新增课程成功");
    }

    @Override
    public ResultVo queryById(int id) {
//        new QueryWrapper<>();
        return ResultUtil.exec(true,"OK",courseMapper.selectById(id));
    }

    @Override
    public PageVo<Course> queryPage(int page, int limit) {
        Page<Course> page1=new Page<>(page,limit);
        return ResultUtil.exec(page,limit,page1.getTotal(),courseMapper.selectPage(page1,null).getRecords());
    }
}

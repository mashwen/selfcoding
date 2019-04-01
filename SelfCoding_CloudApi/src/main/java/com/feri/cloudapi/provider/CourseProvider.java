package com.feri.cloudapi.provider;

import com.feri.common.vo.PageVo;
import com.feri.common.vo.ResultVo;
import com.feri.entity.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author feri
 *@Date Created in 2019/3/25 15:49
 */
@Service
public class CourseProvider {
    @Autowired
    private RestTemplate restTemplate;

    public ResultVo save(Course course){
        //restTemplate.postForEntity()
        //restTemplate.exchange()
//        LinkedMultiValueMap<String,Object> multiValueMap=new LinkedMultiValueMap<>();
////        multiValueMap.put("course",course);
////
//        Map<String,Object> param=new HashMap<>();
//        param.put("name",course.getName());
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<Map<String,Object>> httpEntity=new HttpEntity<Map<String,Object>>(param,httpHeaders);


        return restTemplate.postForObject("http://lxcourseprovider/course/courseadd.do",course,ResultVo.class);
    }
    public ResultVo getDetail(int id){
        return restTemplate.getForObject("http://lxcourseprovider/course/coursedetail.do?id="+id,
                ResultVo.class);
    }
    public PageVo<Course> getPage(int page, int limit){
        return restTemplate.getForObject("http://lxcourseprovider/course/coursepage.do?page="+page+"&limit="+limit,
                PageVo.class);
    }
}

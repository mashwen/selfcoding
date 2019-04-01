package com.feri.elasticseach.controller;

import com.feri.common.vo.ResultVo;
import com.feri.elasticseach.service.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/3/28 13:33
 */
@Api(value = "实现Elasticsearch的相关操作")
@RestController
public class ElasticsearchController {
    @Autowired
    private EsService esService;
    @ApiOperation("新增数据")
    @PostMapping("essave.do")
    public ResultVo save(String indeName,String type,String id,String json){
        return esService.save(indeName, type, id, json);
    }

    @ApiOperation("查询单个数据")
    @GetMapping("essingle.do")
    public ResultVo single(String indeName,String type,String id){
        return esService.querySingle(indeName, type, id);
    }
    @ApiOperation("模糊查询")
    @PostMapping("eslike.do")
    public ResultVo like(String indeName,String type,String field,String value,int page,int limit){
        return esService.queryLike(indeName, type, field, value, page, limit);
    }






}

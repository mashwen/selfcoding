package com.feri.elasticseach.service;

import com.feri.common.vo.ResultVo;

import java.util.List;
import java.util.Map;

/**
 *@Author feri
 *@Date Created in 2019/3/28 13:33
 */
public interface EsService {

    //新增
    public ResultVo save(String index,String type,String id,String data);
    //批量新增
    public ResultVo saveBatch(String index,String type,Map<String,String> data);
    //修改
    public ResultVo update(String index,String type,String id,String data);
    //删除
    public ResultVo delete(String index,String type,String id);
    //批量删除
    public ResultVo deleteBatch(String index,String type,List<String> id);
    //查询单个
    public ResultVo querySingle(String index,String type,String id);
    //模糊查询 分页
    public ResultVo queryLike(String index,String type,String field,String value,int page,int limit);

}

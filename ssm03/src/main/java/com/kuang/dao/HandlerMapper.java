package com.kuang.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HandlerMapper {
    //根据学生班级，学号查询学生 不 在场的活动的标题
    List<String> queryTitleByStudentIdNotPresent(Map<String,Object> map);

    //根据学生班级，学号查询学生在场的活动的标题
    List<String> queryTitleByStudentIdPresent(Map<String,Object> map);
}

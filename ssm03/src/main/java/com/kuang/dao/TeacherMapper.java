package com.kuang.dao;

import com.kuang.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TeacherMapper {
    Teacher queryTeacherByName(String name);
    Teacher queryTeacherByMess(Map<String,String> map);
}

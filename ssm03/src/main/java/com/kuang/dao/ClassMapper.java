package com.kuang.dao;

import com.kuang.pojo.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    Class queryClassById(int id);
    List<Class> queryClassByTeacherId(int teacherId);
}

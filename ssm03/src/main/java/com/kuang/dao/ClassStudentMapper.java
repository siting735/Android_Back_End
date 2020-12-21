package com.kuang.dao;

import com.kuang.pojo.ClassStudent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassStudentMapper {
    ClassStudent queryClassStudentByStudentId(int studentId);

}

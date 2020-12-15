package com.kuang.dao;

import com.kuang.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> queryAllStudent();
}

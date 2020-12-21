package com.kuang.dao;

import com.kuang.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> queryAllStudent();
    Student queryStudentByName(String name);
    Student queryStudentByStudentId(int studentId);
}

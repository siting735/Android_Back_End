package com.kuang.dao;

import com.kuang.pojo.ClassStudent;
import com.kuang.pojo.ClassTeacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassTeacherMapper {
    ClassTeacher queryClassTeacherByTeacherId(int id);
    int updateActivityCount(int classId);
}

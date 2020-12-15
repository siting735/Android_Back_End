package com.kuang.service;

import com.kuang.dao.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl {
    @Autowired
    private TeacherMapper teacherMapper;

    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }
}

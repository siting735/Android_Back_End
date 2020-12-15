package com.kuang.service;

import com.kuang.dao.ClassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassStudentServiceImpl {
    @Autowired
    private ClassStudentMapper classStudentMapper;

    public void setClassStudentMapper(ClassStudentMapper classStudentMapper) {
        this.classStudentMapper = classStudentMapper;
    }
}

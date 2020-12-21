package com.kuang.service;

import com.kuang.dao.ClassTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClassTeacherServiceImpl {
    @Autowired
    private ClassTeacherMapper classTeacherMapper;
}

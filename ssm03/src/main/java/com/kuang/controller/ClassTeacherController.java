package com.kuang.controller;

import com.kuang.service.ClassTeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classTeacher")
public class ClassTeacherController {
    @Autowired
    private ClassTeacherServiceImpl classTeacherServiceImpl;
}

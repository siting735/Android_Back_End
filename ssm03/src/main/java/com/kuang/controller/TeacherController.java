package com.kuang.controller;

import com.kuang.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;
}

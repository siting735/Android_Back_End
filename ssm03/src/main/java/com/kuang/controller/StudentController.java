package com.kuang.controller;

import com.kuang.pojo.Student;
import com.kuang.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @ResponseBody
    @RequestMapping("queryAllStudent")
    public List<Student> queryAllStudent(){
        return studentServiceImpl.queryAllStudent();
    }
}

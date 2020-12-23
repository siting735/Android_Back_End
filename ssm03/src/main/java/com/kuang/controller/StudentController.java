package com.kuang.controller;

import com.kuang.pojo.Student;
import com.kuang.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @ResponseBody
    @RequestMapping("studentSignMessage")
    public Map<String,Object> studentSignMessage(@RequestParam("studentId") int studentId){
        return studentServiceImpl.studentSignMessage(studentId);
    }

    @ResponseBody
    @RequestMapping("login")
    public Map<String,Object> login(@RequestParam("username") String name,@RequestParam("password") String password){
        Map<String, Object> stringObjectMap = studentServiceImpl.studentLoginHandle(name, password);
        return stringObjectMap;
    }

    @ResponseBody
    @RequestMapping("queryAllStudent")
    public List<Student> queryAllStudent(){
        return studentServiceImpl.queryAllStudent();
    }
}

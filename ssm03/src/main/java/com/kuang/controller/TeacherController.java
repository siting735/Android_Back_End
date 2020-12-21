package com.kuang.controller;

import com.kuang.pojo.Student;
import com.kuang.pojo.Teacher;
import com.kuang.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @ResponseBody
    @RequestMapping("login")
    public Map<String,Object> login(@RequestParam("name") String name, @RequestParam("password") String password){
        Map<String,Object> map = teacherServiceImpl.teacherLoginHandle(name,password);
        return map;
    }
}

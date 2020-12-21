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
    @RequestMapping("signRitoOfStudents")
    public Map<String,Object> signRitoOfStudents(@RequestParam("classId") int classId){
        return teacherServiceImpl.signRitoOfStudentsByClassId(classId);
    }

    @ResponseBody
    @RequestMapping("teacherClasses")
    public Map<String,Object> teacherClasses(@RequestParam("teacherId") int teacherId){
        return teacherServiceImpl.teacherClasses(teacherId);
    }

    @ResponseBody
    @RequestMapping("login")
    public Map<String,Object> login(@RequestParam("username") String name, @RequestParam("password") String password){
        Map<String,Object> map = teacherServiceImpl.teacherLoginHandle(name,password);
        return map;
    }
}

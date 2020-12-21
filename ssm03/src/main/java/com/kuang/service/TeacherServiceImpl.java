package com.kuang.service;

import com.kuang.dao.ClassMapper;
import com.kuang.dao.ClassStudentMapper;
import com.kuang.dao.ClassTeacherMapper;
import com.kuang.dao.TeacherMapper;
import com.kuang.pojo.Class;
import com.kuang.pojo.ClassTeacher;
import com.kuang.pojo.Teacher;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ClassTeacherMapper classTeacherMapper;
    @Autowired
    private ClassMapper classMapper;

    public Map<String,Object> teacherLoginHandle(String name,String password){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginState",null);
        map.put("teacherId",null);
        map.put("teacherName",null);
        Teacher teacher = teacherMapper.queryTeacherByName(name);
        if (teacher == null){
            map.put("loginState",2);
        }else if(teacher.getPassword().equals(password)){   //登陆成功，获取教师的信息
            map.put("loginState",1);
            map.put("teacherId",teacher.getTeacherId());
            map.put("teacherName",teacher.getName());
            List<Class> classes = classMapper.queryClassByTeacherId(teacher.getTeacherId());
            List<Map<String,Object>> maps= new LinkedList<Map<String, Object>>();
            for (int i=0;i<classes.size();i++){
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("classId",classes.get(i).getId());
                map1.put("className",classes.get(i).getClassName());
                maps.add(map1);
            }
            map.put("classesInfo",maps);
        }else{
            map.put("loginState",3);
        }
        return map;
    }
    public Teacher queryTeacherByMess(String name,String password){
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",name);
        map.put("password",password);
        return teacherMapper.queryTeacherByMess(map);
    }

}

package com.kuang.service;

import com.kuang.dao.*;
import com.kuang.pojo.*;
import com.kuang.pojo.Class;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class TeacherServiceImpl {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ClassTeacherMapper classTeacherMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    //获取一个班级所有学生的签到情况
    public Map<String,Object> signRitoOfStudentsByClassId(@RequestParam("classId") int classId) {
        Map<String,Object> resultMap =new HashMap<String, Object>();
        //1、先查出班级的所有学生
        List<Student> students = studentMapper.queryAllStudentByClassId(classId);
        //2、查出一个学生的签到情况
        //3、查出所有学生的签到情况
        List<Map<String,Object>> list = new LinkedList<Map<String, Object>>();
        for (Student student: students) {
            Map<String, Object> map = studentServiceImpl.studentSignMessage(student.getStudentId());
            System.out.println(map);
            Map<String,Object> map1 = new HashMap<String, Object>();
            map1.put("studentName",student.getName());
            map1.put("studentId",student.getStudentId());
            double signRito = Double.parseDouble(map.get("signRito").toString());
            map1.put("signRito",Math.round(signRito));
            list.add(map1);
        }
        resultMap.put("signRitoOfStudents",list);
        return resultMap;
    }
    public Map<String,Object> teacherClasses(int teacherId){
        List<Class> classes = classMapper.queryClassByTeacherId(teacherId);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<ClassId> classIdList = new ArrayList<ClassId>();
        for(Class c : classes){
            ClassId classId = new ClassId(c.getId(),c.getClassName());
            classIdList.add(classId);
        }
        resultMap.put("classInfos",classIdList);
        return resultMap;
    }
    public Map<String,Object> teacherLoginHandle(String name,String password){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginState",null);
        map.put("teacherId",null);
        map.put("teacherName",null);
        Teacher teacher = teacherMapper.queryTeacherByName(name);
        if (teacher == null){
            map.put("loginState",3);
        }else if(teacher.getPassword().equals(password)){   //登陆成功，获取教师的信息
            map.put("loginState",2);
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
            map.put("loginState",4);
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

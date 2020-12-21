package com.kuang.service;

import com.kuang.controller.ActivityController;
import com.kuang.dao.*;
import com.kuang.pojo.*;
import com.kuang.pojo.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private class ActivityInfo {
        private String activityTitle;
        private Integer signState;
    }
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassStudentMapper classStudentMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private HandlerMapper handlerMapper;
    @Autowired
    private ActivityMapper activityMapper;

    public Map<String,Object> studentSignMessage(int studentId){
        ClassStudent classStudent = classStudentMapper.queryClassStudentByStudentId(studentId);
        int classId = classStudent.getClassId();
        Double signRito = new Double(0);//学生签到率
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> resultMap = new HashMap<String, Object>();
        map.put("classId",classId);
        map.put("studentId",studentId);
        //学生在场的活动title
        List<String> present  = handlerMapper.queryTitleByStudentIdPresent(map);
        //学生不在场的活动
        List<String> notPresent = handlerMapper.queryTitleByStudentIdNotPresent(map);
        List<ActivityInfo> activityInfoList = new LinkedList<ActivityInfo>();
        for (String str:present) {
            ActivityInfo activityInfo = new ActivityInfo(str, 1);
            activityInfoList.add(activityInfo);
        }
        for (String str:notPresent) {
            ActivityInfo activityInfo = new ActivityInfo(str, 0);
            activityInfoList.add(activityInfo);
        }
        signRito = Double.parseDouble(String.valueOf(present.size()))/
                Double.parseDouble(String.valueOf(present.size()+notPresent.size()));
        resultMap.put("signRito",signRito);
        resultMap.put("activityInfo",activityInfoList);
        return resultMap;
    }

    //通过学生姓名和密码登录，成功获取相关信息
    public Map<String,Object> studentLoginHandle(String name,String password){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginState",null);
        map.put("studentId",null);
        map.put("studentName",null);
        map.put("classId",null);
        map.put("className",null);
        Student student = queryStudentByName(name);
        if(student == null){
            map.put("loginState",2);
        }else if(student.getPassword().equals(password)){
            map.put("loginState",1);
            //登陆成功获取信息
            ClassStudent classStudent = classStudentMapper.queryClassStudentByStudentId(student.getStudentId());
            Class aClass = classMapper.queryClassById(classStudent.getClassId());
            map.put("studentId",student.getStudentId());
            map.put("studentName",student.getName());
            map.put("classId",aClass.getId());
            map.put("className",aClass.getClassName());
        }else {
            map.put("loginState",3);
        }
        return map;
    }

    public List<Student> queryAllStudent(){
        return studentMapper.queryAllStudent();
    }
    public Student queryStudentByName(String name) {
        return studentMapper.queryStudentByName(name);
    }
}

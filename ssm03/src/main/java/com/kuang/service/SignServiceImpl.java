package com.kuang.service;

import com.kuang.dao.ActivityMapper;
import com.kuang.dao.ClassStudentMapper;
import com.kuang.dao.SignMapper;
import com.kuang.dao.StudentMapper;
import com.kuang.pojo.Activity;
import com.kuang.pojo.ClassStudent;
import com.kuang.pojo.Sign;
import com.kuang.pojo.Student;
import com.kuang.util.MapUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SignServiceImpl {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private class StudentInfo{
        private int studentId;
        private String studentName;
    }
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ClassStudentMapper classStudentMapper;
    @Autowired
    private ActivityServiceImpl activityServiceImpl;
    @Autowired
    private StudentMapper studentMapper;

    public Map<String,Object> changeSignToStop(int activityId,int classId){
        List<StudentInfo> list = new ArrayList<StudentInfo>();
        Map<String,Object> map = new HashMap<String, Object>();
        //1、将活动状态改为0
        int i = activityMapper.updateActivityStateToStopByActivityId(activityId);
        if(i>0){
            map.put("activityState",1);
        }else {
            map.put("activityState",0);
        }

        //2、该活动人数/班级的总人数获得班级签到率
        Activity activity = activityMapper.queryActivityByActivityId(activityId);
        int attendanceNumber = activity.getAttendanceNumber();
        int classStudentCount = classStudentMapper.queryStudentCountByClassId(classId);
//        System.out.println(attendanceNumber+"  "+classStudentCount);
        Double signRito = Double.parseDouble(String.valueOf(attendanceNumber))/Double.parseDouble(String.valueOf(classStudentCount))*100;
        map.put("signRito",Math.round(signRito));

        //3、查询没有签到同学的名单
        Map<String,Integer> map1 = new HashMap<String, Integer>();
        map1.put("classId",classId);
        map1.put("activityId",activityId);
        List<ClassStudent> classStudents = signMapper.queryStudentNotInActivity(map1);
        for (ClassStudent s: classStudents) {
            int studentId = s.getStudentId();
            Student student = studentMapper.queryStudentByStudentId(studentId);
            StudentInfo studentInfo = new StudentInfo(s.getClassId(), student.getName());
            list.add(studentInfo);
        }
        map.put("unsignedStudentList",list);
        return map;
    }
    public Map<String,Object> manualSign(int activityId,int studentId){
        Map<String,Object> result  = new HashMap<String, Object>();
        //1、sign表记录
        Sign sign = new Sign(activityId, studentId, null);
        int i = signMapper.insertSign(sign);
        //2、学生表+1
        int i1 = studentMapper.updateStudentAttendanceTime(studentId);
        //3、活动表+1
        int i2 = activityMapper.updateAttendanceNumberByActivityId(activityId);
        if(i>0 && i1>0 && i2>0){
            result.put("signState",1);
        }
        else {
            result.put("signState",0);
        }
        return result;
    }

    public Map<String,Object> studentSign(int studentId,String studentLongitude,String studentLatitude, String deviceId){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("signState",null);
        ClassStudent classStudent = classStudentMapper.queryClassStudentByStudentId(studentId);
        Map<String, Object> map = activityServiceImpl.activityInProgress(classStudent.getClassId());

        double v = MapUtils.GetDistance(Double.parseDouble(map.get("teacherLongitude").toString()),
                                        Double.parseDouble(map.get("teacherLatitude").toString()),
                                        Double.parseDouble(studentLongitude),
                                        Double.parseDouble(studentLatitude));
        if(map.get("activityTitle") ==null){
            resultMap.put("signState",0);
        }
        else {
            //1、先判断符不符合范围
            if(v>200){
                resultMap.put("signState",0);
                System.out.println("学生里老师位置超出限制");
            }
            //2、符合范围
            else{
                Sign sign = new Sign(Integer.parseInt(map.get("activityId").toString()), studentId, deviceId);
                //3、判断学生有没有二次签到
                Sign sign1 = signMapper.querySignBySign(sign);
                if(sign1!=null){
                    resultMap.put("signState",0);
                    System.out.println("学生已在正在进行的活动中签到过");
                }
                //4、没有二次签到
                //插入sign表格
                else{
                    int i = signMapper.insertSign(sign);
                    System.out.println("学生签到成功，在sign表格中添加数据");
                    resultMap.put("signState",1);
                    //5、在activity中attendance_number+1
                    activityMapper.updateAttendanceNumberByActivityId(Integer.parseInt(map.get("activityId").toString()));
                    System.out.println("学生签到，活动表人数+1");
                    //6、学生表的出席次数+1
                    studentMapper.updateStudentAttendanceTime(studentId);
                }
            }
        }
        return resultMap;
    }
}




















package com.kuang.service;

import com.kuang.dao.ClassStudentMapper;
import com.kuang.dao.SignMapper;
import com.kuang.pojo.ClassStudent;
import com.kuang.pojo.Sign;
import com.kuang.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Service
public class SignServiceImpl {
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private ClassStudentMapper classStudentMapper;
    @Autowired
    private ActivityServiceImpl activityServiceImpl;
    public Map<String,Object> studentSign(int studentId,String studentLongitude,String studentLatitude, String deviceId){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("signState",null);
        ClassStudent classStudent = classStudentMapper.queryClassStudentByStudentId(studentId);
        Map<String, Object> map = activityServiceImpl.activityInProgress(classStudent.getClassId());
        double v = MapUtils.GetDistance(Double.parseDouble(map.get("teacherLongitude").toString()),
                                        Double.parseDouble(map.get("teacherLatitude").toString()),
                                        Double.parseDouble(studentLongitude),
                                        Double.parseDouble(studentLatitude));
        //1、先判断符不符合范围
        if(v>100){
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
            }
        }
        return resultMap;
    }
}


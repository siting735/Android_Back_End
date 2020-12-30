package com.kuang.service;

import com.kuang.dao.ActivityMapper;
import com.kuang.dao.ClassTeacherMapper;
import com.kuang.pojo.Activity;
import com.sun.javafx.fxml.builder.JavaFXFontBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ClassTeacherMapper classTeacherMapper;

    public Map<String,Object> searchActivityInProcessByActivityId(Integer teacherId){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Activity activity = activityMapper.searchActivityInProcessByActivityId(teacherId);
        if(activity!=null){
            resultMap.put("activityId",activity.getActivityId());
            resultMap.put("activityTitle",activity.getTitle());
            resultMap.put("classId",activity.getClassId());
            return resultMap;
        }
        resultMap.put("activityId","");
        resultMap.put("activityTitle",null);
        resultMap.put("classId","");
        return resultMap;
    }

    public Map<String,Object> launchActivity(int classId,String activityTitle,
                                             String teacherLongitude, String teacherLatitude){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String location = ""+teacherLongitude+","+teacherLatitude;
        String substring = activityTitle.substring(0, 10)+" "+activityTitle.substring(10,activityTitle.length());
        System.out.println("time:"+substring);
        Activity activity = new Activity(substring, location, classId, 1, 0);
        int i = activityMapper.insertAndQueryActivity(activity);
        //1、发布失败
        if(i==0){
            resultMap.put("activityState",0);
            resultMap.put("activityId",0);
        }
        //2、发布成功
        else {
            resultMap.put("activityState",1);
            resultMap.put("activityId",activity.getActivityId());
            //3、更新班级教师表的活动数量
            classTeacherMapper.updateActivityCount(classId);
        }
        return resultMap;
    }
    public Map<String, Object> activityInProgress(int classId){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> resultMap = new HashMap<String, Object>();
        map.put("classId",classId);
        map.put("state",1);
        List<Activity> activityList = activityMapper.queryActivityByClassIdAndState(map);
        resultMap.put("activityId","");
        resultMap.put("activityTitle",null);
        resultMap.put("teacherLongitude","");
        resultMap.put("teacherLatitude","");
        if(activityList.size() >=1 ){
            Activity activity = activityList.get(0);
            resultMap.put("activityId",activity.getActivityId());
            resultMap.put("activityTitle",activity.getTitle());
            String[] split = activity.getLocation().split(",");
            resultMap.put("teacherLongitude",split[0]);
            resultMap.put("teacherLatitude",split[1]);
        }
        return resultMap;
    }

}

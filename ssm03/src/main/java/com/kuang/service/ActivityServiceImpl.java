package com.kuang.service;

import com.kuang.dao.ActivityMapper;
import com.kuang.pojo.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl {
    @Autowired
    private ActivityMapper activityMapper;
    public Map<String, Object> activityInProgress(int classId){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> resultMap = new HashMap<String, Object>();
        map.put("classId",classId);
        map.put("state",1);
        List<Activity> activityList = activityMapper.queryActivityByClassIdAndState(map);
        Activity activity = activityList.get(0);
        resultMap.put("activityId",null);
        resultMap.put("activityTitle",null);
        resultMap.put("teacherLongitude",null);
        resultMap.put("teacherLatitude",null);
        if(activity != null){
            resultMap.put("activityId",activity.getActivityId());
            resultMap.put("activityTitle",activity.getTitle());
            String[] split = activity.getLocation().split(",");
            resultMap.put("teacherLongitude",split[0]);
            resultMap.put("teacherLatitude",split[1]);
        }
        return resultMap;
    }

}

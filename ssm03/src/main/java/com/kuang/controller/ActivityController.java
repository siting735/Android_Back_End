package com.kuang.controller;

import com.kuang.pojo.Activity;
import com.kuang.service.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityServiceImpl activityServiceImpl;


    @ResponseBody
    @RequestMapping("launchActivity")
    public Map<String,Object> launchActivity(@RequestParam("classId") int classId,
                                             @RequestParam("activityTitle") String activityTitle,
                                             @RequestParam("teacherLongitude") String teacherLongitude,
                                             @RequestParam("teacherLatitude") String teacherLatitude){
        return activityServiceImpl.launchActivity(classId,activityTitle,teacherLongitude,teacherLatitude);
    }

    @RequestMapping("activityInProgress")
    @ResponseBody
    Map<String,Object> activityInProgress(@RequestParam("classId") int classId){
        return activityServiceImpl.activityInProgress(classId);
    }
}

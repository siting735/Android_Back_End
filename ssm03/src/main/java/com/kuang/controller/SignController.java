package com.kuang.controller;

import com.kuang.service.SignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private SignServiceImpl signServiceImpl;

    @ResponseBody
    @RequestMapping("changeSignToStop")
    public Map<String,Object> changeSignToStop(@RequestParam("activityId")int activityId,@RequestParam("classId") int classId){
        return signServiceImpl.changeSignToStop(activityId,classId);
    }

    @ResponseBody
    @RequestMapping("manualSign")
    public Map<String,Object> manualSign(@RequestParam("activityId") int activityId,@RequestParam("studentId") int studentId){
        return signServiceImpl.manualSign(activityId,studentId);
    }

    @ResponseBody
    @RequestMapping("studentSign")
    public Map<String,Object> studentSign(@RequestParam("studentId") int studentId,
                                          @RequestParam("studentLongitude") Double studentLongitude,
                                          @RequestParam("studentLatitude") Double studentLatitude,
                                          @RequestParam("deviceId") String deviceId){
        System.out.println(studentId+"  "+deviceId);
        System.out.println(studentLongitude + studentLatitude);
        return signServiceImpl.studentSign(studentId,studentLongitude.toString(),studentLatitude.toString(),deviceId);
    }
}

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
    @RequestMapping("studentSign")
    public Map<String,Object> studentSign(@RequestParam("studentId") int studentId,
                                          @RequestParam("studentLongitude") String studentLongitude,
                                          @RequestParam("studentLatitude") String studentLatitude,
                                          @RequestParam("deviceId") String deviceId){
        return signServiceImpl.studentSign(studentId,studentLongitude,studentLatitude,deviceId);
    }
}

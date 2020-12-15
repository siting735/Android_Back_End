package com.kuang.controller;

import com.kuang.service.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cctivity")
public class ActivityController {
    @Autowired
    private ActivityServiceImpl activityServiceImpl;
}

package com.kuang.controller;

import com.kuang.service.SignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private SignServiceImpl signServiceImpl;

}

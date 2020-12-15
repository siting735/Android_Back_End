package com.kuang.service;

import com.kuang.dao.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl {
    @Autowired
    private SignMapper signMapper;

    public void setSignMapper(SignMapper signMapper) {
        this.signMapper = signMapper;
    }
}


package com.kuang.service;

import com.kuang.dao.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl {
    @Autowired
    private ActivityMapper activityMapper;

    public void setActivityMapper(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }
}

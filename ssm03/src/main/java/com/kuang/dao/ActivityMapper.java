package com.kuang.dao;

import com.kuang.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActivityMapper {
    List<Activity> queryActivityByClassIdAndState(Map<String,Object> map);
    List<Activity> queryAllActivityByClassId(int classId);

}

package com.kuang.dao;

import com.kuang.pojo.ClassStudent;
import com.kuang.pojo.Sign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SignMapper {
    List<ClassStudent> queryStudentNotInActivity(Map<String,Integer> map);
    int insertSign(Sign sign);
    Sign querySignBySign(Sign sign);
}

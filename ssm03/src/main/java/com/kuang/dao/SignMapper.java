package com.kuang.dao;

import com.kuang.pojo.Sign;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {
    int insertSign(Sign sign);
    Sign querySignBySign(Sign sign);
}

package com.chen.boot.chenboot.mapper;

import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoubleColorBallMapper {

    void addDate(DoubleColorBallEntiry entiry);

}

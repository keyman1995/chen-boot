package com.chen.boot.chenboot.mapper;

import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoubleColorBallMapper {

    void addDate(DoubleColorBallEntiry entiry);

    void batchInsert(List<DoubleColorBallEntiry> entiryList);

}

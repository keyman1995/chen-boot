package com.chen.boot.chenboot.mapper;

import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import com.chen.boot.chenboot.entity.MonthResponse;
import com.chen.boot.chenboot.entity.request.OpenDayRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DoubleColorBallMapper {

    void addDate(DoubleColorBallEntiry entiry);

    void batchInsert(List<DoubleColorBallEntiry> entiryList);

    int getRecentDate();

    List<MonthResponse> getSumBallNumByDate(OpenDayRequest request);

}

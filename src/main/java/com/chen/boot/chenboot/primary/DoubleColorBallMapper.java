package com.chen.boot.chenboot.primary;

import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import com.chen.boot.chenboot.entity.MonthResponse;
import com.chen.boot.chenboot.entity.request.OpenDayRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoubleColorBallMapper {

    void addDate(DoubleColorBallEntiry entiry);

    void batchInsert(List<DoubleColorBallEntiry> entiryList);

    int getRecentDate();

    List<MonthResponse> getSumBallNumByDate(OpenDayRequest request);

}

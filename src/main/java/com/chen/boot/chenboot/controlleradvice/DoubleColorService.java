package com.chen.boot.chenboot.controlleradvice;

import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import com.chen.boot.chenboot.entity.MonthResponse;
import com.chen.boot.chenboot.entity.request.OpenDayRequest;
import com.chen.boot.chenboot.mapper.DoubleColorBallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoubleColorService {

    @Autowired
    private DoubleColorBallMapper mapper;


    public void batchAdd(List<DoubleColorBallEntiry> entiryList){
        mapper.batchInsert(entiryList);
    }

    public long getRecentDate(){
        return mapper.getRecentDate();
    }

    public List<MonthResponse>getSumBallNumByDate(OpenDayRequest request){
        return mapper.getSumBallNumByDate(request);
    }

}

package com.chen.boot.chenboot.controlleradvice;

import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import com.chen.boot.chenboot.entity.MonthResponse;
import com.chen.boot.chenboot.entity.request.OpenDayRequest;
import com.chen.boot.chenboot.follower.StudentMapper;
import com.chen.boot.chenboot.primary.DoubleColorBallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoubleColorService {

    @Autowired
    private DoubleColorBallMapper doubleColorBallMapper;

    @Autowired
    private StudentMapper studentMapper;


    public void batchAdd(List<DoubleColorBallEntiry> entiryList){
        doubleColorBallMapper.batchInsert(entiryList);
    }

    public long getRecentDate(){
        return doubleColorBallMapper.getRecentDate();
    }

    public List<MonthResponse>getSumBallNumByDate(OpenDayRequest request){
        studentMapper.getUserFollower(1);
        return doubleColorBallMapper.getSumBallNumByDate(request);
    }

}

package com.chen.boot.chenboot.controlleradvice;

import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import com.chen.boot.chenboot.mapper.DoubleColorBallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoubleColorService {

    @Autowired
    private DoubleColorBallMapper mapper;


    public void batchAdd(List<DoubleColorBallEntiry> entiryList){
        mapper.batchInsert(entiryList);
    }

}

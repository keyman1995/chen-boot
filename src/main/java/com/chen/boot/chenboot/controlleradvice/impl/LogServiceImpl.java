package com.chen.boot.chenboot.controlleradvice.impl;

import com.chen.boot.chenboot.controlleradvice.LogService;
import com.chen.boot.chenboot.entity.Log;
import com.chen.boot.chenboot.primary.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName LogServiceImpl
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Component
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addLog(Log log) {
        //  String name = null;
        logMapper.addLog(log);
        // name.equals("11111");
    }
}

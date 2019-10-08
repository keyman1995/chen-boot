package com.chen.boot.chenboot.controlleradvice.impl;

import com.chen.boot.chenboot.controlleradvice.LogService;
import com.chen.boot.chenboot.controlleradvice.StudentService;
import com.chen.boot.chenboot.entity.Log;
import com.chen.boot.chenboot.follower.StudentMapper;
import com.chen.boot.chenboot.primary.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @ClassName StudentServiceImpl
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/10/8
 * @Version V1.0
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private LogService logService;


    @Override
    @Transactional(value = "followerTransactionManager")
    public void updateStudentService() {
        studentMapper.updateUser(1, 1);
        //  logService.addLog(new Log(UUID.randomUUID().toString(), "updateStudent"));
        String name = null;
        name.equals("11111");
    }
}

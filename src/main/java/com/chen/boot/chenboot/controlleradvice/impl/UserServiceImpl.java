package com.chen.boot.chenboot.controlleradvice.impl;

import com.chen.boot.chenboot.controlleradvice.LogService;
import com.chen.boot.chenboot.controlleradvice.UserService;
import com.chen.boot.chenboot.entity.Log;
import com.chen.boot.chenboot.entity.User;
import com.chen.boot.chenboot.primary.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private LogService logService;

    @Override
    public User getById(int id) {
        return mapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateById() {
        User user = mapper.getById(1);
        user.setName("chenjie11");
        mapper.upDateById(user);
        logService.addLog(new Log(UUID.randomUUID().toString(), "updateUser"));
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}

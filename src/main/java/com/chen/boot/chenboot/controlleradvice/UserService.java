package com.chen.boot.chenboot.controlleradvice;

import com.chen.boot.chenboot.entity.User;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/23
 * @Version V1.0
 **/
public interface UserService {

    User getById(int id);

    void updateById();
}

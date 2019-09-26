package com.chen.boot.chenboot.mapper;

import com.chen.boot.chenboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Mapper
public interface UserMapper {

    User getById(int id);

    void upDateById(User user);

}

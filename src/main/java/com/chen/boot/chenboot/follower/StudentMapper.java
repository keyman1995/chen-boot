package com.chen.boot.chenboot.follower;

import com.chen.boot.chenboot.entity.UserFollower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName StudentMapper
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/10/8
 * @Version V1.0
 **/
@Mapper
public interface StudentMapper {

    UserFollower getUserFollower(Integer id);

   void updateUser(@Param("userId") Integer userId,@Param("id") Integer id);

}

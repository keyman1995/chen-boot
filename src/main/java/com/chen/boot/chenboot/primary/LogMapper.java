package com.chen.boot.chenboot.primary;

import com.chen.boot.chenboot.entity.Log;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName LogMapper
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Mapper
public interface LogMapper {

    void addLog(Log log);

}

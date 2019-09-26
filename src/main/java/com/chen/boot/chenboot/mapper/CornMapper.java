package com.chen.boot.chenboot.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CornMapper {

    /**
     * 根据id 获取定时任务
     * @param cornId
     * @return
     */
    String getCorn(int cornId);

}

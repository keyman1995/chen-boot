package com.chen.boot.chenboot.entity;

import java.io.Serializable;

/**
 * @ClassName MonthResponse
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/23
 * @Version V1.0
 **/
public class MonthResponse implements Serializable {

    private Integer monthCount;

    private Integer openMonth;

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public Integer getOpenMonth() {
        return openMonth;
    }

    public void setOpenMonth(Integer openMonth) {
        this.openMonth = openMonth;
    }
}

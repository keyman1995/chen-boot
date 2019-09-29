package com.chen.boot.chenboot.entity.request;

/**
 * @ClassName OpenDayRequest
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/29
 * @Version V1.0
 **/
public class OpenDayRequest {

    private Integer nextDate;

    private Integer previousDate;

    public Integer getNextDate() {
        return nextDate;
    }

    public void setNextDate(Integer nextDate) {
        this.nextDate = nextDate;
    }

    public Integer getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(Integer previousDate) {
        this.previousDate = previousDate;
    }
}

package com.chen.boot.chenboot.schedule;

import com.chen.boot.chenboot.controller.DoubleColorUtils;
import com.chen.boot.chenboot.primary.CornMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DoubleColorTask
 * @Description: 双色球定时任务采集器
 * @Author chenjie
 * @Date 2019/9/19
 * @Version V1.0
 **/
@Component
@Configuration
@EnableScheduling
public class DoubleColorTask implements SchedulingConfigurer {

    @Autowired
    private DoubleColorUtils doubleColorUtils;

    @Autowired
    private CornMapper cornMapper;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDD HH:mm:ss");

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("定时执行 ： "+simpleDateFormat.format(new Date()));
            }
        }, triggerContext -> {
            return new CronTrigger(cornMapper.getCorn(1)).nextExecutionTime(triggerContext);
        });
    }
}

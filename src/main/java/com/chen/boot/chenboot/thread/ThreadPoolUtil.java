package com.chen.boot.chenboot.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolUtil
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/18
 * @Version V1.0
 **/
@Configuration
public class ThreadPoolUtil {


    private static int NCPU = Runtime.getRuntime().availableProcessors();

    @Bean
    public ThreadPoolExecutor createThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(NCPU, NCPU + 4, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1024));
        return executor;
    }


}

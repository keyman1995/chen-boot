package com.chen.boot.chenboot;

import com.chen.boot.chenboot.controller.HelloController;
import com.chen.boot.chenboot.controlleradvice.UserService;
import com.chen.boot.chenboot.entity.DoubleColorBallEntiry;
import com.chen.boot.chenboot.mapper.DoubleColorBallMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChenBootApplicationTests {

    @Autowired
    private HelloController controller;

    @Autowired
    private DoubleColorBallMapper doubleColorBallMapper;

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        DoubleColorBallEntiry entiry = new DoubleColorBallEntiry();
        entiry.setFirstBall(1);
        entiry.setSecondBall(2);
        entiry.setThirdBall(3);
        entiry.setFourthBall(4);
        entiry.setFifthBall(5);
        entiry.setSixthBall(6);
        entiry.setBlueBall(8);
        entiry.setOpenDate(20190906);
        entiry.setOpenTerm(20190906);
        entiry.setSales(100000);
        entiry.setFirstPrize(1);
        entiry.setSecondPrize(2);
        doubleColorBallMapper.addDate(entiry);
    }

    @Test
    public void doUser(){
        userService.updateById();
    }

}

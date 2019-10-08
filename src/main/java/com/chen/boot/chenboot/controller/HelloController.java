package com.chen.boot.chenboot.controller;

import com.chen.boot.chenboot.controlleradvice.DoubleColorService;
import com.chen.boot.chenboot.controlleradvice.StudentService;
import com.chen.boot.chenboot.entity.MonthResponse;
import com.chen.boot.chenboot.entity.request.OpenDayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName HelloController
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/8/27
 * @Version V1.0
 **/
@Controller
public class HelloController {

    @Value("zhangsan")
    private String name;


    @Autowired
    private DoubleColorUtils doubleColorUtils;

    @Autowired
    private DoubleColorService doubleColorService;

    @Autowired
    private StudentService studentService;

    @ModelAttribute
    public String getContentPath() {
        return "/boot";
    }


    @RequestMapping("login")
    @ResponseBody
    @CrossOrigin
    public String doLogin() {
        return "SUCCESS";
    }

    @RequestMapping("getStatistical")
    @ResponseBody
    @CrossOrigin
    public List<MonthResponse> getStatistical( OpenDayRequest request) {

        studentService.updateStudentService();

        return doubleColorService.getSumBallNumByDate(request);
    }

    @RequestMapping("getCurrent")
    @ResponseBody
    public String getCurrent() {
        doubleColorUtils.getCurrentTerm();
        return "SUCCESS";
    }


    @RequestMapping("double")
    public String toDouble() {
        return "ball";
    }


}
